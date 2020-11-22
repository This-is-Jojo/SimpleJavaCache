package com.jojo.cache.impl;

import com.jojo.cache.SimpleCache;
import com.jojo.cache.SimpleCacheFactory;
import com.jojo.cache.constants.EvictionStrategy;
import org.junit.jupiter.api.Test;

class LFUCacheTest {

    @Test
    void testLFUCache() {
        SimpleCache<String, String> cache = SimpleCacheFactory.getCache(3, EvictionStrategy.LFU);

        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");

        cache.get("key3");
        cache.get("key3");
        cache.get("key3");
        cache.get("key2");
        cache.get("key2");
        cache.get("key1");

        cache.put("key4", "value4");

        assert cache.get("key1") == null;
    }
}