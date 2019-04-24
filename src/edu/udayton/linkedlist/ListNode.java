package edu.udayton.linkedlist;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

    public void printList() {
        ListNode node = this;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println("");
    }
}
