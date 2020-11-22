package com.jojo.cache;

import com.jojo.cache.constants.EvictionStrategy;
import com.jojo.cache.impl.LFUCache;
import com.jojo.cache.impl.LRUCache;

public class SimpleCacheFactory {
    public static <K, V> SimpleCache <K, V> getCache(int maxSize, EvictionStrategy strategy) {
        switch (strategy) {
            case LFU:
                return new LFUCache<>(maxSize);
            case LRU:
                return new LRUCache<>(maxSize);
        }
        throw new UnsupportedOperationException("Cache with strategy: " + strategy + " not supported");
    }
}
