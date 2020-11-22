package com.jojo.cache.node.impl;

public class LinkedNodeWithCount<K, V> extends BaseLinkedNode<K, V, LinkedNodeWithCount<K, V>> {
    private int count = 0;

    public LinkedNodeWithCount() {
    }

    public LinkedNodeWithCount(K key, V value) {
        super(key, value);
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        this.count++;
    }
}
