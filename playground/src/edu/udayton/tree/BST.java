package edu.udayton.tree;

public class BST {

    private void printBST(Node node) {
        if (node == null) {
            return;
        }
        printBST(node.left);
        System.out.print(node.val + " ");
        printBST(node.right);
    }

    private boolean searchNode(Node node, int val) {
        if (node == null) {
            return false;
        }
        if (node.val == val) {
            return true;
        }
        if (node.val > val)
            return searchNode(node.left, val);
        else
            return searchNode(node.right, val);
    }

    private Node getPredecessor(Node node, Node prevNode) {
        System.out.println("Searching a Predecessor for " + prevNode.val);
        if (node == null) {
            return prevNode;
        }
        if (node.right == null) {
            prevNode.right = node.left;
            return node;
        }
        return getPredecessor(node.left, node);
    }

    private Node getSuccessor(Node node, Node prevNode) {
        System.out.println("Searching a Successor for " + prevNode.val);
        if (node == null) {
            return prevNode;
        }
        if (node.left == null) {
            prevNode.left = node.right;
            return node;
        }
        return getSuccessor(node.left, node);
    }

    private boolean deleteNode(Node node, Node prevNode, int delVal) {
        if (node == null) {
            return false;
        }
        if (node.val == delVal) {
            Node replaceNode = null;
            if (node.left == null && node.right == null) {
                prevNode.left = null;
            }
            if (node.right != null) {
                replaceNode = getSuccessor(node.right, node);
                replaceNode.right = node.right;
                prevNode.right = replaceNode;
            } else if (node.left != null) {
                replaceNode = getPredecessor(node.left, node);
                replaceNode.left = node.left;
                prevNode.right = replaceNode;
            } else {
                node = null;
            }
            System.out.println("LargeNode: " + replaceNode.val + " prev: " + prevNode.val + ", node: " + node.val);
            return true;
        }
        if (node.val < delVal)
            return deleteNode(node.right, node, delVal);
        else
            return deleteNode(node.left, node, delVal);
    }

    public static void main(String[] args) {
        int delVal = 2;
        System.out.println("Initiating BST...");
        BST bst = new BST();
        Node rootNode = new Node(5, new Node(2, new Node(1), new Node(3)), new Node(8, new Node(7), null));
        bst.printBST(rootNode);
        System.out.println("Found? " + bst.searchNode(rootNode, delVal));
        System.out.println("Deleted? " + bst.deleteNode(rootNode, rootNode, delVal));
        System.out.println("Found? " + bst.searchNode(rootNode, delVal));
        bst.printBST(rootNode);
    }
}
