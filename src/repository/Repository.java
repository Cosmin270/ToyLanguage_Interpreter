package repository;

import model.exception.RepositoryException;
import model.list.MyList;
import model.state.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository {

    private final MyList<ProgramState> programStates;
    private int index;
    private String logFilePath;

    public Repository() {
        programStates = new MyList<>();
        index = 0;
    }
    public Repository(ProgramState state, String logFilePath) {
        programStates = new MyList<>();
        programStates.add(state);
        this.logFilePath = logFilePath;
        index = 0;
    }


    public Repository(String logFilePath) {
        programStates = new MyList<>();
        index = 0;
        this.logFilePath = logFilePath;
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
    public void decrement(){this.index -= 1;}
    @Override
    public void reset() {
        ProgramState prgState = this.programStates.getElemAtIndex(this.index);
        prgState.getExecutionStack().clear();
        prgState.getSymbolTable().clear();
        prgState.getOut().clear();
    }
    @Override
    public void logPrgStateExec() throws RepositoryException {
        PrintWriter writer;
        try{
            writer = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            writer.println(this.getCrtProgram().toString());
            writer.flush();
            writer.close();
        }
        catch (IOException e){
            throw new RepositoryException("Error opening log file");
        }
    }

}
