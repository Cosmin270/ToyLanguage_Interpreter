package model.state;

import model.stack.MyStack;
import model.statement.Statement;

import java.util.EmptyStackException;

public class StackExecutionStack implements ExecutionStack {

    private final MyStack<Statement> stack = new MyStack<>();

    @Override
    public void push(Statement statement){
        stack.push(statement);
    }

    @Override
    public Statement pop(){
        return stack.pop();
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Statement s : this.stack){
            sb.append(s.toString()).append("; ");
        }
        return sb.toString();
    }
}