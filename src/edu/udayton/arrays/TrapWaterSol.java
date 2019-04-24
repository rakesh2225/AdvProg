package edu.udayton.arrays;

import java.util.Stack;

public class TrapWaterSol {

    public static void main(String[] args) {
        //int[] rain = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        //int[] rain = new int[]{0,1,0,2};
        int[] rain = new int[]{2, 0, 2};
        System.out.println(new TrapWaterSol().trap(rain));
    }

    int trap(int[] height)
    {
        int ans = 0, current = 0;
        Stack<Integer> st = new Stack<Integer>();
        while (current < height.length) {
            while (!st.empty() && height[current] > height[st.peek()]) {
                int top = st.peek();
                st.pop();
                if (st.empty())
                    break;
                int distance = current - st.peek() - 1;
                int bounded_height = Math.min(height[current], height[st.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            st.push(current++);
        }
        return ans;
    }
}
