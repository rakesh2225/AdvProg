package edu.udayton.linkedlist;

public class SortedListMerge {

    public static void main(String[] args) {
        //ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode l1 = null;
        //l1.printList();
        //ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));
        ListNode l2 = new ListNode(0, null);
        l2.printList();
        ListNode res = new SortedListMerge().mergeTwoLists(l1, l2);
        res.printList();
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode nextNode;
        ListNode l1Prev = l1;
        ListNode res = l1;
        while (l1 != null && l2 != null) {
            while (l1 != null && l2 != null
                    && l1.val < l2.val) {
                l1Prev = l1;
                l1 = l1.next;
            }
            if (l1 != null && l2 != null) {
                nextNode = l2.next;
                l2.next = l1Prev.next;
                l1Prev.next = l2;
                l1Prev = l2;
                l2 = nextNode;
            }
        }
        if (l1 == null && l2 != null) {
            if (l1Prev != null) {
                l1Prev.next = l2;
            } else {
                res = l2;
            }
            return res;
        }
        return res;
    }
}
