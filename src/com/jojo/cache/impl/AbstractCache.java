package com.jojo.cache.impl;

import com.jojo.cache.SimpleCache;
import com.jojo.cache.node.Node;
import com.jojo.cache.node.impl.LinkedNodeWithCount;

import java.util.Map;

public abstract class AbstractCache <K, V, N extends Node<K, V, N>> implements SimpleCache<K, V> {

    protected N head;
    protected N tail;

    protected int cacheSize;

    protected Map<K, N> nodeMap;

    @Override
    public abstract V get(K key);

    @Override
    public abstract void put(K key, V value);

    protected void moveToHead(N node) {
        deleteNode(node);
        addToHead(node);
    }

    protected void addToHead(N node) {
        if (node == null) return;
        N oldHead = head.getNext();
        head.setNext(node);
        node.setPrev(head);
        node.setNext(oldHead);
        oldHead.setPrev(node);
    }

    protected void deleteNode(N node) {
        if (node == null) return;
        N next = node.getNext();
        node.getPrev().setNext(next);
        next.setPrev(node.getPrev());
    }
}
