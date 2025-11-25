package controller;

import model.exception.ControllerException;
import model.map.MyMap;
import model.state.IExecutionStack;
import model.state.ProgramState;
import model.statement.IStatement;
import model.value.IValue;
import model.value.RefValue;
import repository.IRepository;
import repository.Repository;
import view.Colors;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private  IRepository repository;
    private boolean displayFlag;

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

    public ProgramState oneStep(ProgramState state) throws ControllerException {
        IExecutionStack stack = state.getExecutionStack();
        if(stack.isEmpty()) {
            throw new ControllerException("Execution stack is empty");
        }
        IStatement statement = (IStatement) stack.pop();
        return statement.execute(state);

    }
    public String getFinalOutput(){
        return Colors.CYAN +"Final output: { " + Colors.RESET + Colors.BRIGHT_WHITE + this.getRepository().getCrtProgram().getOut().toString() +Colors.RESET + Colors.CYAN + "}" + Colors.RESET;
    }

    public List<String> allStep(){
        ProgramState programState = this.repository.getCrtProgram();
        this.repository.logPrgStateExec();
        List<String> list = new ArrayList<>();
        if(this.displayFlag)
            list.add(programState.toString());
        while (!programState.getExecutionStack().isEmpty()){
            oneStep(programState);
            this.repository.logPrgStateExec();
            MyMap<Integer, IValue> heapContent = programState.getHeapTable().getHeap();
            List<Integer> symAddress = this.getAddressesFromSymbolTable(programState.getSymbolTable().getContent().values());
            List<Integer> allAdr = this.addIndirectAddresses(symAddress, heapContent);
            programState.getHeapTable().setContent(this.garbageCollector(allAdr, heapContent));
            if(this.displayFlag)
                list.add(programState.toString());
        }
        //this.repository.deleteFirst(); //??
        //increment();
        return list;
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
