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
public class OperatorService {
    
    public DLL getDLL(String num) {
        if (num == null || num.length() < 1) {
            return null;
        }
        num = new StringBuilder(num).reverse().toString();
        DLL dll = new DLL();        
        Node cur = new Node(num.charAt(0));
        dll.setFirst(cur);
        for (int i = 1; i < num.length(); i++) {
            Node node = new Node(num.charAt(i));
            cur.next = node;
            node.prev = cur;
            cur = node;
        }
        return dll;
    }
    
    public void printDLL(DLL dll) {
        for (Node cur = dll.getFirst(); cur != null; cur = cur.next) {
            if (cur != null) {
                System.out.print(" -> " + cur.ele);
            }
        }
    }
    
}
