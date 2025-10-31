package model.map;

import java.util.*;

public class MyMap<K, V> implements MyIMap<K, V>, Iterable<Map.Entry<K, V>>{
    private final Map<K, V> map = new HashMap<>();
    @Override
    public V get(K key) {
        return map.get(key);
    }
    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }
    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return map.entrySet().iterator();
    }


}
