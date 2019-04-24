package edu.udayton.bst;

public class BSTNode {

    BSTNode left;
    BSTNode right;
    int val;

    public BSTNode(int val) {
        this.val = val;
    }

    public BSTNode(int val, BSTNode left, BSTNode right) {
        this.left = left;
        this.right = right;
        this.val = val;
    }
}
