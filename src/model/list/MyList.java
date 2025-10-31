package model.list;

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

    public T getFirst() {return list.getFirst();}
    @Override
    public Iterator<T> iterator() {return list.iterator();}

    public void deleteFirst(){
        list.removeFirst();
    }
}
