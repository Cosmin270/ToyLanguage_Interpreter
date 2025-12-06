package repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.exception.RepositoryException;
import model.list.MyList;
import model.state.ProgramState;

public class Repository implements IRepository {

    private MyList<ProgramState> programStates;
    private int index;
    private String logFilePath;
    private boolean firstTimeWriting = true;

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
//    @Override
//    public ProgramState getCrtProgram() {
//        return this.programStates.getElemAtIndex(this.index);
//    }
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
    public void logPrgStateExec(ProgramState programState) throws RepositoryException {
        PrintWriter logFile;
        try {
            if (this.firstTimeWriting) {
                logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, false)));
                this.firstTimeWriting = false;
            }
            else {
                logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            }
        }
        catch (IOException e) {
            throw new RepositoryException("The file cannot be opened/created/doesn't exist.");
        }
        logFile.println(programState.toString());
        logFile.flush();
        logFile.close();
    }

    @Override
    public MyList<ProgramState> getPrgList() {
        return this.programStates;
    }
    @Override
    public void setPrgList(MyList<ProgramState> prgList) {
        this.programStates = prgList;
    }
}
