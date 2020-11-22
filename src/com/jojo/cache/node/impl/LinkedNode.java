package com.jojo.cache.node.impl;

public class LinkedNode<K, V> extends BaseLinkedNode<K, V, LinkedNode<K, V>> {
    public LinkedNode() {
    }

    public LinkedNode(K key, V value) {
        super(key, value);
    }
}
