package com.jojo.cache.node.impl;

import com.jojo.cache.node.Node;

public abstract class BaseLinkedNode<K, V, T> implements Node<K, V, T> {
    private K key;
    private V value;
    private T prev;
    private T next;

    public BaseLinkedNode() {
    }

    public BaseLinkedNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public T getPrev() {
        return prev;
    }

    public void setPrev(T prev) {
        this.prev = prev;
    }

    public T getNext() {
        return next;
    }

    public void setNext(T next) {
        this.next = next;
    }
}
