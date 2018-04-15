/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.udayton;

/**
 *
 * @author rakesh
 */
public class Node {
    
    public Object ele;
    
    public Node next;
    
    public Node prev;
    
    public Node(Object ele) {
        this.ele = ele;
    }
    
    public Node(Object ele, Node next, Node prev) {
        this.ele = ele;
        this.next = next;
        this.prev = prev;
    }
}
