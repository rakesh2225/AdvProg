package edu.udayton.arrays;

public class TargetSum_BF {

    public static void main(String[] args) {
        int arr[] = new TargetSum().twoSum( new int[]{2,7,11,15},9);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
}
