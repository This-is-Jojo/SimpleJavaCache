package com.jojo.cache;

public interface SimpleCache <K, V> {
    V get(K key);
    void put(K key, V value);
}
