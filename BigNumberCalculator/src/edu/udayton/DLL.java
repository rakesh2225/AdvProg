/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udayton;

/**
 *
 * @author shamanth
 */
public class DLL {
    
    private Node first;
    
    private Node last;

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }
    
    public void insertLast(Node node) {
        if (first == null) {
            first = node;
            return;
        }
        Node temp = first;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.prev = temp;
    }
    
    public int insertAtIndex(Node node, int index) {
        if (index < 0 || (first == null && index > 0)) {
            return - 1;
        }
        int i = 0;
        if (index == 0) {
            node.next = first;
            first.prev = node;
            first = node;
        }
        for (Node cur = first; cur != null; cur = cur.next, i++) {
            if (i + 1== index) {
                node.next = cur.next;
                node.prev = cur;
                cur.next = node;
                return 1;
            }
        }
        return -1;
    }
    
}
