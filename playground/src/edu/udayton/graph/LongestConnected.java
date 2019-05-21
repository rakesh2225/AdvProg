package edu.udayton.graph;

import java.util.HashMap;
import java.util.Map;

public class LongestConnected {

    public static void main(String[] args) {
        //int[] A = {1, 2, 3, 3};
        //int[] B = {2, 3, 1, 4};
        int[] A = {1, 2, 4, 5};
        int[] B = {2, 3, 5, 6};
        System.out.println(new LongestConnected().getLongest(A, B, 4));
    }

    public int getLongest(int[] A, int[] B, int N) {
        int longest = 0;
        int connectedLen = 0;
        Map<Integer, Integer> visited = new HashMap<Integer, Integer>();
        for (int j = 0; j < A.length; j++) {
            if (visited.containsKey(A[j]) || visited.containsKey(B[j])) {
                connectedLen++;
            } else {
                if (longest < connectedLen) {
                    longest = connectedLen;
                }
                connectedLen = 1;
            }
            visited.put(A[j], A[j]);
            visited.put(B[j], B[j]);
        }
        if (longest < connectedLen) {
            longest = connectedLen;
        }
        return longest;
    }
}
