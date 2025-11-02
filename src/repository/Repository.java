package repository;

import model.list.MyList;
import model.state.ProgramState;

public class Repository implements IRepository {

    private final MyList<ProgramState> programStates;
    private int index;


    public Repository() {
        programStates = new MyList<>();
        index = 0;
    }

    @Override
    public void addProgram(ProgramState programState) {
        this.programStates.add(programState);
    }
    @Override
    public void deleteFirst(){
        this.programStates.deleteFirst();
    }
    @Override
    public ProgramState getCrtProgram() {
        return this.programStates.getElemAtIndex(this.index);
    }
    @Override
    public void increment(){
        this.index += 1;
    }
    @Override
    public void reset() {
        this.index -= 1;
        ProgramState prgState = this.programStates.getElemAtIndex(this.index);
        prgState.getExecutionStack().clear();
        prgState.getSymbolTable().clear();
        prgState.getOut().clear();
    }
}
