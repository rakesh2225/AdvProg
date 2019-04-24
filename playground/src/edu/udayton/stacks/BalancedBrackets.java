package edu.udayton.stacks;

import java.util.Stack;

public class BalancedBrackets {

    public static void main(String[] args) {
        System.out.println(new BalancedBrackets().isValid("([])"));
    }

    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!stack.empty()) {
                System.out.println(ch);
                switch (ch) {
                    case ')' : {
                        if ((char)stack.peek() == '(') {
                            stack.pop();
                            break;
                        }
                        stack.push(ch);
                    }
                    case '}' : {
                        if ((char)stack.peek() == '{') {
                            stack.pop();
                            break;
                        }
                        stack.push(ch);
                    }
                    case ']' : {
                        if ((char)stack.peek() == '[') {
                            stack.pop();
                            break;
                        }
                        stack.push(ch);
                    }
                    default: {
                        stack.push(ch);
                        break;
                    }
                }
            }
            else {
                stack.push(ch);
            }
        }

        if (stack.empty()) {
            return true;
        }
        System.out.println(stack.peek());
        return false;
    }
}
