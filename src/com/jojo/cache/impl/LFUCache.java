package com.jojo.cache.impl;

import com.jojo.cache.node.impl.LinkedNodeWithCount;

import java.util.*;

public class LFUCache<K, V> extends AbstractCache<K, V, LinkedNodeWithCount<K, V>> {
    private final PriorityQueue<LinkedNodeWithCount<K, V>> queue;

    public LFUCache(int size) {
        this.cacheSize = size;
        this.head = new LinkedNodeWithCount<>();
        this.tail = new LinkedNodeWithCount<>();
        this.head.setNext(tail);
        this.tail.setPrev(head);
        this.nodeMap = new HashMap<>();
        this.queue = new PriorityQueue<>(Comparator.comparingInt(LinkedNodeWithCount::getCount));
    }

    @Override
    public V get(K key) {
        if (!nodeMap.containsKey(key)) return null;
        LinkedNodeWithCount<K, V> curNode = nodeMap.get(key);
        curNode.incrementCount();
        updateQueue(curNode);
        moveToHead(curNode);
        return curNode.getValue();
    }

    private void removeLast() {
        List<LinkedNodeWithCount<K, V>> minList = new ArrayList<>();
        if (nodeMap.isEmpty() || queue.isEmpty()) return;
        int min_count = queue.peek().getCount();

        while (!queue.isEmpty() && queue.peek().getCount() == min_count) minList.add(queue.poll());
        if (minList.size() == 1) {
            LinkedNodeWithCount<K, V> minNode = minList.get(0);
            deleteNode(minNode);
            nodeMap.remove(minNode.getKey());
        } else {
            LinkedNodeWithCount<K, V> cur = tail.getPrev();
            while (cur != head) {
                if (minList.contains(cur)) {
                    deleteNode(cur);
                    nodeMap.remove(cur.getKey());
                    for (LinkedNodeWithCount<K, V> n : minList) {
                        if (n != cur) queue.offer(n);
                    }
                    break;
                } else {
                    cur = cur.getPrev();
                }
            }
        }
    }

    private void updateQueue(LinkedNodeWithCount<K, V> node) {
        if (node == null) return;
        queue.remove(node);
        queue.offer(node);
    }

    public void put(K key, V value) {
        if (cacheSize == 0) return;
        if (!nodeMap.containsKey(key) && queue.size() == cacheSize) removeLast();
        if (nodeMap.containsKey(key)) {
            LinkedNodeWithCount<K, V> curNode = nodeMap.get(key);
            curNode.setValue(value);
            curNode.incrementCount();
            updateQueue(curNode);
            moveToHead(curNode);
        } else {
            LinkedNodeWithCount<K, V> newNode = new LinkedNodeWithCount<>(key, value);
            nodeMap.put(key, newNode);
            addToHead(newNode);
            queue.offer(newNode);
        }
    }
}