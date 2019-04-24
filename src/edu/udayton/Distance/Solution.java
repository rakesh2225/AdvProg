package edu.udayton.Distance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public int shortestDistance(int m, int n, List<List<Integer>> grid) {
        if (grid == null || grid.get(0).size() == 0) return 0;
        final int[] shift = new int[]{0, 1, 0, -1, 0};
        int row = m;
        int col = n;
        int[][] distance = new int[row][col];
        int[][] reach = new int[row][col];
        int buildingNum = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid.get(i).get(j) == 1) {
                    buildingNum++;
                    Queue<int[]> myQueue = new LinkedList<int[]>();
                    myQueue.offer(new int[]{i, j});

                    boolean[][] isVisited = new boolean[row][col];
                    int level = 1;

                    while (!myQueue.isEmpty()) {
                        int qSize = myQueue.size();
                        for (int q = 0; q < qSize; q++) {
                            int[] curr = myQueue.poll();
                            for (int k = 0; k < 4; k++) {
                                int nextRow = curr[0] + shift[k];
                                int nextCol = curr[1] + shift[k + 1];

                                if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
                                        && grid.get(nextRow).get(nextCol) == 0 && !isVisited[nextRow][nextCol]) {
                                    distance[nextRow][nextCol] += level;
                                    reach[nextRow][nextCol]++;

                                    isVisited[nextRow][nextCol] = true;
                                    myQueue.offer(new int[]{nextRow, nextCol});
                                }
                            }
                        }
                        level++;
                    }
                }
            }
        }

        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid.get(i).get(j) == 0 && reach[i][j] == buildingNum) {
                    shortest = Math.min(shortest, distance[i][j]);
                }
            }
        }
        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> int1 = new ArrayList<>();
        List<Integer> int2 = new ArrayList<>();
        List<Integer> int3 = new ArrayList<>();
        List<Integer> int4 = new ArrayList<>();
        List<Integer> int5 = new ArrayList<>();
        int1.add(1);int1.add(1);int1.add(1);int1.add(1);
        int2.add(1);int2.add(1);int2.add(1);int2.add(1);
        int3.add(1);int3.add(1);int3.add(1);int3.add(1);
        int4.add(1);int4.add(1);int4.add(1);int4.add(1);
        int5.add(1);int5.add(1);int5.add(1);int5.add(1);
        list.add(int1);
        list.add(int2);
        list.add(int3);
        list.add(int4);
        list.add(int5);
        System.out.println(new Solution().shortestDistance(5, 4 , list));
    }
}
