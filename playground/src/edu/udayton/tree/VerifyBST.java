package edu.udayton.tree;

import java.util.Stack;

public class VerifyBST {

    public static void main(String[] args) {
        VerifyBST bst = new VerifyBST();
        Node rootNode = new Node(5, new Node(2, new Node(1), new Node(3)), new Node(8, new Node(7), null));
        System.out.println("Verify BST using Stack: " + bst.verifyBSTStack(rootNode));

        System.out.println("Verify BST using Iterator: " + bst.verifyBSTIterate(rootNode, null, null));
        new VerifyBST().inOrderTraversal(rootNode);
        new VerifyBST().preOrderTraversal(rootNode);
    }

    private void inOrderTraversal(Node root) {
        System.out.print("InOrderTraversal: ");
        Stack<Node> nodes = new Stack<>();
        while (root != null || !nodes.isEmpty()) {
            while (root != null){
                nodes.push(root);
                root = root.left;
            }
            root = nodes.pop();
            System.out.print(root.val + " ");
            root = root.right;
        }
        System.out.println();
    }

    private void preOrderTraversal(Node root) {
        Stack<Node> nodes = new Stack<>();
        System.out.print("PreOrder: ");
        while (root != null || !nodes.isEmpty()) {
            while (root != null){
                System.out.print(root.val + " ");
                nodes.push(root);
                root = root.left;
            }
            root = nodes.pop();
            root = root.right;
        }
        System.out.println();
    }


    private boolean verifyBSTStack(Node root) {
        Stack<Node> nodes = new Stack<>();
        double inorderVal = - Double.MAX_VALUE;
        while (root != null || !nodes.isEmpty()) {
            while (root != null){
                nodes.push(root);
                root = root.left;
            }
            root = nodes.pop();
            if (root.val <= inorderVal) {
                return false;
            }
            inorderVal = root.val;
            root = root.right;
        }
        return true;
    }

    private boolean verifyBSTIterate(Node root, Integer minVal, Integer maxVal) {
        if (root == null) {
            return true;
        }
        if ((minVal != null && root.val <= minVal) || (maxVal != null && root.val >= maxVal)) {
            return false;
        }
        if (verifyBSTIterate(root.left, minVal, root.val)) {
            return verifyBSTIterate(root.right, root.val, maxVal);
        }
        return false;
    }
}
