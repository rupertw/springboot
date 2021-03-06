package me.www.nclrucache;

import me.www.lrucache.LRUCache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: www
 * @date: 2018/5/8 23:05
 * @description: TODO
 */
public class NonConcurrentLRUCache<K, V> implements LRUCache<K, V> {

    public static final Integer DEFAULT_CACHE_SIZE = 16;

    private LinkedHashMap<K, V> map;

    public NonConcurrentLRUCache() {
        map = new LinkedHashMaptLRUCache<>(NonConcurrentLRUCache.DEFAULT_CACHE_SIZE);
    }

    public NonConcurrentLRUCache(Integer cacheSize) {
        if (cacheSize == null || cacheSize <= 0) {
            cacheSize = NonConcurrentLRUCache.DEFAULT_CACHE_SIZE;
        }
        map = new LinkedHashMaptLRUCache<>(cacheSize);
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public String toString() {
        return super.toString() + map;
    }

    /**
     * 基于LinkedHashMap实现LRUCache
     *
     * @param <K>
     * @param <V>
     */
    private class LinkedHashMaptLRUCache<K, V> extends LinkedHashMap<K, V> {
        /**
         * 缓存容量大小
         */
        private final int cacheSize;

        private static final float DEFAULT_LOAD_FACTOR = 0.75f;

        public LinkedHashMaptLRUCache(int cacheSize) {
            super((int) Math.ceil(cacheSize / LinkedHashMaptLRUCache.DEFAULT_LOAD_FACTOR) + 1,
                    LinkedHashMaptLRUCache.DEFAULT_LOAD_FACTOR, true);
            this.cacheSize = cacheSize;
        }

        /**
         * 回收策略（插入新元素后，如果大小超过容量，删除最老的元素）
         *
         * @param eldest
         * @return
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > cacheSize;
        }
    }

}
