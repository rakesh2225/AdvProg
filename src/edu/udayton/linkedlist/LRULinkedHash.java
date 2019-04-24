package edu.udayton.linkedlist;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LRULinkedHash extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRULinkedHash(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int val) {
        super.put(key, val);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRULinkedHash lru = new LRULinkedHash(2);
        //System.out.println("Get 1: " + lru.get(0));
        lru.put(2, 1);
        //System.out.println("Get 2: " + lru.get(2));
        lru.put(1, 1);
        //System.out.println("Get 2: " + lru.get(2));
        lru.put(2, 3);
        //System.out.println("Get 2: " + lru.get(2));
        lru.put(4, 1);
        System.out.println("Get 1: " + lru.get(1));
        System.out.println("Get 2: " + lru.get(2));
        //System.out.println("Get 4: " + lru.get(4));
    }
}
