package edu.udayton.linkedlist;

public class ListAddFastest {

    public static void main(String[] args) {
        ListNode l2 = new ListNode(0, null);
        ListNode l1 = new ListNode(9, new ListNode(1, new ListNode(6, null)));
        ListNode res = new ListSum1().addTwoNumbers(l1, l2);
        res.printList();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode p1 = new ListNode(-1);
        ListNode pointer = p1;
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int temp = (val1 + val2 + carry);
            carry = temp / 10;
            pointer.next = new ListNode(temp % 10);
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            pointer = pointer.next;
        }
        return p1.next;
    }
}
