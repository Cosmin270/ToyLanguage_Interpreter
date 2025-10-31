package model.stack;

public interface MyIStack<T> {
    void push(T value);
    T pop();
    boolean isEmpty();
}
