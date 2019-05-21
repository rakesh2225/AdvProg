package edu.udayton.linkedlist;

public class ListSum1 {

    public static void main(String[] args) {
        ListNode l2 = new ListNode(0, null);
        ListNode l1 = new ListNode(9, new ListNode(1, new ListNode(6, null)));
        ListNode res = new ListSum1().addTwoNumbers(l1, l2);
        res.printList();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addNum(l2, l1, l2);
    }

    private ListNode addNum(ListNode prev, ListNode l1, ListNode res) {
        ListNode head = res;
        int carry = 0;
        int sum = 0;
        while (l1 != null) {
            sum = carry + l1.val;
            if (res != null) {
                sum += res.val;
            }
            carry = sum != 0 ? sum / 10 : 0;
            System.out.println(sum + ", " + carry);
            if (res != null) {
                res.val = sum % 10;
                prev = res;
                res = res.next;
            } else {
                prev.next = l1;
                res = l1;
                res.val = sum % 10;
                prev = res;
                res = res.next;
                break;
            }
            System.out.println("Res :" +prev.val);
            l1 = l1.next;
        }
        while (res != null) {
            sum = carry + res.val;
            carry = sum != 0 ? sum / 10 : 0;
            res.val = sum % 10;
            prev = res;
            res = res.next;
            System.out.println("Res1 :" +prev.val);
        }
        if (carry > 0) {
            res = new ListNode(carry);
            prev.next = res;
        }
        //System.out.println("Res :" + head.val);
        return head;
    }
}
