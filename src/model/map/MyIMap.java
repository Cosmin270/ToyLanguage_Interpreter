package model.map;

import java.util.Collection;
import java.util.Map;



public interface MyIMap<K, V> extends Map<K, V>{
//    void put(K key, V value);
//    V get(K key);
//    boolean containsKey(K key);
    void clear();
    void removeByKey(K key);
    Collection<V> values();
//    Set<Map.Entry<K, V>> entrySet();
    void setContent(Map<? extends  K, ? extends V> content);
}
