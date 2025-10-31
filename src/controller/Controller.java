package controller;

import model.exception.ControllerException;
import model.exception.MyException;
import model.stack.MyIStack;
import model.stack.MyStack;
import model.state.ExecutionStack;
import model.state.ProgramState;
import model.statement.Statement;
import repository.IRepository;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private  IRepository repository;
    private boolean displayFlag;


    public Controller() {
        this.repository = new Repository();
        this.displayFlag = true;
    }

    public void turnOnDisplayFlag(){
        this.displayFlag = true;
    }
    public void turnOffDisplayFlag(){
        this.displayFlag = false;
    }

    public void addProgram(ProgramState state) {
        this.repository.addProgram(state);
    }

    public ProgramState oneStep(ProgramState state) throws ControllerException {
        ExecutionStack stack = state.getExecutionStack();
        if(stack.isEmpty()) {
            throw new ControllerException("Execution stack is empty");
        }
        Statement statement = (Statement) stack.pop();
        return statement.execute(state);

    }

    public List<String> allStep(){
        ProgramState programState = this.repository.getCrtProgram();
        List<String> list = new ArrayList<>();
        list.add(programState.toString());
        while (!programState.getExecutionStack().isEmpty()){
            oneStep(programState);
            if(this.displayFlag)
                list.add(programState.toString());
        }
        this.repository.deleteFirst();
        return list;
    }

}
