package edu.udayton.arrays;

import java.util.Arrays;

public class MaxPartArray {
    public int maxSumAfterPartitioning(int[] A, int K) {
        if (A.length == 0 || K == 0) {
            return 0;
        }
        Arrays.sort(A);
        int rep = K;
        int shift = A.length - 1;
        int sum = 0;
        int max = A[A.length - 1];
        for (int i = A.length - 1; i >= 0; i--) {
            System.out.println(A[i]);
            if (rep == 0) {
                rep = K;
                max = A[--shift];
            }
            sum += max;
            rep--;
            System.out.println("Max: " + max + ", sum: " + sum + ", rep: " + rep);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] A = {1,4,1,5,7,3,6,1,9,9,3};
        //999999997777
        int  K = 4;
        System.out.println(new MaxPartArray().maxSumAfterPartitioning(A, 4));
    }
}
