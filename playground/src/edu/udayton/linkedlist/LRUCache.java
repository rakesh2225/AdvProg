package edu.udayton.linkedlist;

class LRUCache {

    private int capacity;

    class Node {
        int val;
        int key;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node head;
    private Node tail;

    private LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int getNode(Node node, Node prev, int key) {
        if (node == null) {
            return -1;
        }
        printCache();
        if (node.key == key) {
            System.out.println("Matched key is " + key);
            if (tail.key == key) {
                return tail.val;
            }
            prev.next = node.next;
            node.next = null;
            tail.next = node;
            tail = tail.next;
            head = head.next;
            System.out.println("Val : " + tail.val);
            return tail.val;
        }
        return getNode(node.next, node, key);
    }

    public int get(int key) {
        if (head == null) {
            return -1;
        }
        if (head.key == tail.key) {
            if (tail.key == key) {
                return tail.val;
            }
            return -1;
        }
        if (head.key == key) {
            Node tmp = head;
            head = head.next;
            tail.next = tmp;
            tail = tail.next;
            tail.next = null;
            return tail.val;
        }
        return getNode(head, head, key);
    }

    private void printCache() {
        Node cur = head;
        System.out.print("Current cache is: ");
        while (cur != null) {
            System.out.print(cur.key + " ");
            cur = cur.next;
        }
        System.out.println("");
    }

    private void putNode(Node headPtr, int key, int val) {
        Node prev = headPtr;
        Node cur = headPtr;
        for (; cur != null; cur = cur.next) {
            if (cur.key == key) {
                cur.val = val;
                prev.next = cur.next;
                cur.next = null;
                //if (cur.key != tail.key) {
                    tail.next = cur;
                    tail = tail.next;
                    tail.next = null;
                    System.out.println("PutNode at end " + tail.val);
                //}
                break;
            }
        }
        if (cur == null) {
            tail.next = new Node(key, val);
            tail = tail.next;
            capacity--;
        }
        if (capacity <= 0) {
            System.out.println("Replaced " + head.val + " with " + tail.val);
            head = head.next;
        }
    }

    public void put(int key, int value) {
        printCache();
        System.out.println("Putting " + key);
        if (head == null) {
            head = new Node(key, value);
            tail = head;
            printCache();
            return;
        }
        Node temp = head;
        putNode(temp, key, value);
        System.out.println("PUT complete");
        printCache();
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
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
        lru.printCache();
    }
}

