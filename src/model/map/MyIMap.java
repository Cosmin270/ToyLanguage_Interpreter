package model.map;

public interface MyIMap<K, V> {
    void put(K key, V value);
    V get(K key);
    boolean containsKey(K key);
}
