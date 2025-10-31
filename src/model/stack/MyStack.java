package model.stack;

import model.exception.ADTException;

import java.util.*;

public class MyStack<T> implements MyIStack<T>, Iterable<T>{
    private final Stack<T> stack = new Stack<T>();

    @Override
    public void push(T value) {
        stack.push(value);
    }
    @Override
    public T pop() {
        if(stack.isEmpty())
            throw new ADTException("Empty stack");
        return stack.pop();
    }
    @Override
    public boolean isEmpty() {return stack.isEmpty();}
    @Override
    public Iterator<T> iterator() {
        return stack.iterator();
    }
}
