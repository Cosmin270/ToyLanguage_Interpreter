package model.list;

public interface MyIList<T> {
    void add(T value);
    T getFirst();
    String toString();
    T getElemAtIndex(int index);
    void clear();
}
