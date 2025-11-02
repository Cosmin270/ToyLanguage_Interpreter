package model.state;

import model.stack.MyStack;
import model.statement.IStatement;

public class StackExecutionStack implements IExecutionStack {

    private final MyStack<IStatement> stack = new MyStack<>();

    @Override
    public void push(IStatement statement){
        stack.push(statement);
    }

    @Override
    public IStatement pop(){
        return stack.pop();
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(IStatement s : this.stack){
            sb.append(s.toString()).append("; ");
        }
        return sb.toString();
    }
    @Override
    public void clear(){
        stack.clear();
    }
}