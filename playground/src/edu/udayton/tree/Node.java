package edu.udayton.tree;

public class Node {

    Node left;
    Node right;
    int val;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right) {
        this.left = left;
        this.right = right;
        this.val = val;
    }
}
