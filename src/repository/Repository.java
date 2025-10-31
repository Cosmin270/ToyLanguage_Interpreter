package repository;

import model.list.MyList;
import model.state.ProgramState;

public class Repository implements IRepository {

    private final MyList<ProgramState> programStates;

    public Repository() {
        programStates = new MyList<>();
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
        return this.programStates.getFirst();
    }
}
