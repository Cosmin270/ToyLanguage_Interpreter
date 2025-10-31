package model.state;
import model.statement.*;

public interface ExecutionStack {
    void push(Statement  statement);
    Statement pop();
    boolean isEmpty();
    String toString();
}
