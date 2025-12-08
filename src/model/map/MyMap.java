package model.map;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MyMap<K, V> implements MyIMap<K, V>, Iterable<Map.Entry<K, V>>, Cloneable {
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

    @Override
    public void setContent(Map<? extends K, ? extends V> newmap){
        this.clear();
        this.map.putAll(newmap);
    }
    
    @Override
@SuppressWarnings("unchecked")
public MyIMap<K, V> clone() {
    try {
        MyMap<K, V> copy = (MyMap<K, V>) super.clone();
        for (Map.Entry<K, V> entry : this.map.entrySet()) {
            V value = entry.getValue();

            V valueCopy;
            if (value instanceof Cloneable) {
                valueCopy = (V) value.getClass().getMethod("clone").invoke(value);
            } else {
                valueCopy = value; 
            }

            copy.map.put(entry.getKey(), valueCopy);
        }

        return copy;
    } catch (CloneNotSupportedException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
        throw new AssertionError("Clone failed", e);
    }
}
}