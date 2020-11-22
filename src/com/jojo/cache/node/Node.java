package com.jojo.cache.node;

public interface Node <K, V, T> {
    K getKey();

    V getValue();

    void setValue(V value);

    T getPrev();

    void setPrev(T prev);

    T getNext();

    void setNext(T next);
}
