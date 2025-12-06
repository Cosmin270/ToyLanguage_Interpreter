package model.list;

import java.util.List;

public interface MyIList<T> extends List<T> {
    //void add(T value);
    T getFirst();
    String toString();
    T getElemAtIndex(int index);
    void clear();
    void deleteFirst();
}
