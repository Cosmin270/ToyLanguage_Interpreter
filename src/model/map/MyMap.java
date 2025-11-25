package model.map;

import java.util.*;

public class MyMap<K, V> implements MyIMap<K, V>, Iterable<Map.Entry<K, V>> {
    private final Map<K, V> map = new HashMap<>();

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return map.entrySet().iterator();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void removeByKey(K key) {
        map.remove(key);
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    // These are required because Map<K,V> defines them
    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }
}