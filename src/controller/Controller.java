package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import model.exception.ControllerException;
import model.exception.RepositoryException;
import model.list.MyList;
import model.map.MyMap;
import model.state.ProgramState;
import model.value.IValue;
import model.value.RefValue;
import repository.IRepository;
import repository.Repository;

public class Controller {
    private IRepository repository;
    private boolean displayFlag;
    private ExecutorService executor;

    public Controller(IRepository repository) {
        this.repository = repository;
        this.displayFlag = true;
    }

    public Controller() {
        this.repository = new Repository();
        this.displayFlag = true;
    }
    public IRepository getRepository() {return repository;}
    public void turnOnDisplayFlag(){
        this.displayFlag = true;
    }
    public void turnOffDisplayFlag(){
        this.displayFlag = false;
    }

    public boolean getDisplayFlag(){
        return this.displayFlag;
    }

    public void addProgram(ProgramState state) {
        this.repository.addProgram(state);
    }


    // public String getFinalOutput(){
    //     return Colors.CYAN +"Final output: { " + Colors.RESET + Colors.BRIGHT_WHITE + this.getRepository().getCrtProgram().getOut().toString() +Colors.RESET + Colors.CYAN + "}" + Colors.RESET;
    // }

    public MyList<ProgramState> removeCompletedPrg(MyList<ProgramState> prgList){
        return prgList.stream().filter(ProgramState::isNotComplete).collect(Collectors.toCollection(MyList::new));
    }

    public List<String> oneStepForAllPrg(MyList<ProgramState> prgList) throws ControllerException {
        
        List<String> states = new ArrayList<>();
        prgList.forEach(p -> {
            try{
                repository.logPrgStateExec(p);
                if(this.displayFlag){
                    states.add(p.toString());
                }
            }catch (RepositoryException e)
            {
                throw new ControllerException(e.getMessage());
            }
        });



        MyList<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(() -> { return p.oneStep(); }))
                .collect(Collectors.toCollection(MyList::new));

        MyList<ProgramState> newPrgList;
        try{
                newPrgList = this.executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new ControllerException(e.getMessage());
                    }
                })
                .filter(p -> p != null)
                .collect(Collectors.toCollection(MyList::new));
        }catch (InterruptedException e){
            throw new ControllerException(e.getMessage());
        }

        prgList.addAll(newPrgList);

        prgList.forEach(p -> {
            try{
                repository.logPrgStateExec(p);
                if(this.displayFlag){
                    states.add(p.toString());
                }
            }catch (RepositoryException e)
            {
                throw new ControllerException(e.getMessage());
            }
        });

        this.repository.setPrgList(prgList);

        return states;
    }

    public List<String> allStep() {
        this.executor = Executors.newFixedThreadPool(2);
        MyList<ProgramState> prgList = this.removeCompletedPrg(this.repository.getPrgList());
        
        List<String> programStates = new ArrayList<>();

        while(!prgList.isEmpty()){
            try{
                programStates.addAll(this.oneStepForAllPrg(prgList));
                prgList = this.removeCompletedPrg(this.repository.getPrgList());
            }
            catch (ControllerException e){
                throw new ControllerException(e.getMessage());
            }
        }
        this.executor.shutdownNow();
        this.repository.setPrgList(prgList);

        return programStates;
    }


    private List<Integer> getAddressesFromSymbolTable(Collection<IValue> values){
        return values.stream()
                .filter(r -> r instanceof RefValue)
                .map(r -> {RefValue refValue = (RefValue) r; return refValue.getAddress();})
                .toList();
    }

    private List<Integer> addIndirectAddresses(List<Integer> addressesFromSymbolTable, MyMap<Integer, IValue> heap){
        boolean change = true;
        List<Integer> newAddresses = new ArrayList<>(addressesFromSymbolTable);

        while(change){
            List<Integer> newList;
            change = false;

            newList = heap.entrySet().stream()
                    .filter(e -> e.getValue() instanceof RefValue)
                    .filter(e -> newAddresses.contains(e.getKey()))
                    .map(e -> ((RefValue) e.getValue()).getAddress())
                    .filter(e -> !newAddresses.contains(e))
                    .toList();

            if(!newList.isEmpty()){
                change = true;
                newAddresses.addAll(newList);
            }
        }
        return newAddresses;
    }

    public MyMap<Integer, IValue> garbageCollector(List<Integer> symTableAddr, MyMap<Integer, IValue> heap) {
        MyMap<Integer, IValue> newHeap = new MyMap<>();

        heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .forEach(e -> newHeap.put(e.getKey(), e.getValue()));

        return newHeap;
    }

    public void reset(){
        this.repository.reset();
    }
    public void increment(){
        this.repository.increment();
    }
}
