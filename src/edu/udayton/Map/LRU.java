package edu.udayton.Map;

import java.util.*;

class LRU {

    int capacity;
    Map<Integer, Integer> cache = new HashMap<>();
    Map<Integer, Integer> lru = new HashMap<>();

    class Node {
        int val;
        Node next;
    }

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            lru.put(key, lru.get(key) + 1);
            return cache.get(key);
        }
        return -1;
    }

    private int getLRU() {
        int lruKey = 0;
        int lruTime = 0;
        Iterator it = lru.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (lruTime <= (int)pair.getValue()) {
                lruKey = (int)pair.getKey();
            }
        }
        return lruKey;
    }

    public void put(int key, int value) {
        System.out.println("PUT:: Key: " + key + ", cap: " + capacity);
        if (capacity <= 0) {
            int replace = getLRU();
            cache.remove(replace);
            System.out.println("Replaced " + replace + ", with " + key);
        }
        cache.put(key, value);
        lru.put(key, 1);
        if (capacity > 0) capacity--;
    }

    public static void main(String[] args) {
        LRU lru = new LRU(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println("Get 1: " + lru.get(1));
        lru.put(3, 3);
        System.out.println("Get 2: " + lru.get(2));
        lru.put(4, 4);
        System.out.println("Get 1: " + lru.get(1));
        System.out.println("Get 3: " + lru.get(3));
        System.out.println("Get 4: " + lru.get(4));
    }
}

