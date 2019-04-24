package edu.udayton.stacks;

class MinStack {

    /** initialize your data structure here. */
    class Node {

        int val;
        int min;
        Node next;

        Node(int val, int min) {
            this(val, min, null);
        }

        Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    Node head = null;

    public MinStack() {

    }

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x, null);
            return;
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }

    public void pop() {
        if (head == null) {
            return;
        }
        head = head.next;
    }

    public int top() {
        if (head == null) {
            return 0;
        }
        return head.val;
    }

    public int getMin() {
        if (head == null) {
            return 0;
        }
        return head.min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

        push(x) -- Push element x onto stack.
        pop() -- Removes the element on top of the stack.
        top() -- Get the top element.
        getMin() -- Retrieve the minimum element in the stack.
        Example:


 */