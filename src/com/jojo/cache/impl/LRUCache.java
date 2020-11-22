package com.jojo.cache.impl;

import com.jojo.cache.node.impl.LinkedNode;

import java.util.HashMap;

public class LRUCache<K, V> extends AbstractCache<K, V, LinkedNode<K, V>> {
    public LRUCache(int size) {
        this.cacheSize = size;
        this.head = new LinkedNode<>();
        this.tail = new LinkedNode<>();
        this.head.setNext(tail);
        this.tail.setPrev(head);
        this.nodeMap = new HashMap<>();
    }

    @Override
    public V get(K key) {
        if (!nodeMap.containsKey(key)) return null;
        LinkedNode<K, V> curNode = nodeMap.get(key);
        moveToHead(curNode);
        return curNode.getValue();
    }

    private void removeLast() {
        if (nodeMap.isEmpty()) return;

        LinkedNode<K, V> lastNode = tail.getPrev();
        nodeMap.remove(lastNode.getKey());
        deleteNode(lastNode);
    }

    public void put(K key, V value) {
        if (cacheSize == 0) return;
        if (!nodeMap.containsKey(key) && nodeMap.size() == cacheSize) removeLast();
        if (nodeMap.containsKey(key)) {
            LinkedNode<K, V> curNode = nodeMap.get(key);
            curNode.setValue(value);
            moveToHead(curNode);
        } else {
            LinkedNode<K, V> newNode = new LinkedNode<>(key, value);
            nodeMap.put(key, newNode);
            addToHead(newNode);
        }
    }
}
