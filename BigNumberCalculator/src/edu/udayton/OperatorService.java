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
        DLL dll = new DLL(); 
        Node newNode = new Node(num.charAt(0));
        dll.setFirst(newNode);
        dll.setLast(newNode);
        for (int i = 1; i < num.length(); i++) {
            Node node = new Node(num.charAt(i), dll.getFirst(), null);
            dll.getFirst().prev = node;
            dll.setFirst(node);
        }
        return dll;
    }    
    
}
