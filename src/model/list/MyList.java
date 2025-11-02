package model.list;

import model.exception.ADTException;

import java.util.*;

public class MyList<T> implements MyIList<T>, Iterable<T> {
    private final ArrayList<T> list = new ArrayList<>();

    @Override
    public void add(T value) {
        list.add(value);
    }

    @Override
    public String toString(){
        return list.toString();
    }

    @Override
    public T getFirst() {return list.getFirst();}

    @Override
    public Iterator<T> iterator() {return list.iterator();}

    public void deleteFirst(){
        list.removeFirst();
    }
    @Override
    public T getElemAtIndex(int index) throws ADTException {
        if(index<0 || index>=list.size()){
            throw new ADTException("Index out of bounds");
        }
        return list.get(index);
    };
    @Override
    public void clear() {
        list.clear();
    }
}
