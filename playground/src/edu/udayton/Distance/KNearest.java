package edu.udayton.Distance;

import java.util.HashMap;
import java.util.Map;

public class KNearest {

    public int[][] kClosest(int[][] points, int K) {
        if (points.length <= 0 || K < 1) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        int count = 0;
        int[][] result = new int[K][2];
        double nearest = Double.MAX_VALUE;
        Map<Integer, Integer> values = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0] * points[i][0];
            int y = points[i][1] * points[i][1];
            double dist = Math.sqrt(x + y);

        }
        return result;
    }
}
