package model.state;
import model.statement.*;

public interface IExecutionStack {
    void push(IStatement statement);
    IStatement pop();
    boolean isEmpty();
    String toString();
    void clear();
}
