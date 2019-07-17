public class RotateBin {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(new RotateBin().search(arr, 4));
        System.out.println(new RotateBin().search(arr, 5));
        System.out.println(new RotateBin().search(arr, 6));
        System.out.println(new RotateBin().search(arr, 7));
        System.out.println(new RotateBin().search(arr, 0));
        System.out.println(new RotateBin().search(arr, 1));
        System.out.println(new RotateBin().search(arr, 2));
    }

    private int binSearch(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        //System.out.println("start: " + start + ", mid: " + mid + ", end: " + end);
        //System.out.println(nums[start] + ", " + nums[mid] + ", " + nums[end]);
        if (nums[mid] == target) {
            return mid;
        }
        if ((target < nums[mid] && target < nums[start])
            || (target > nums[mid] && target > nums[start])) {
            return binSearch(nums, mid + 1, end, target);
        }
        return binSearch(nums, start, mid - 1, target);
    }
    public int search(int[] nums, int target) {
        if (nums.length <= 0) {
            return -1;
        }
        if (nums.length == 1 && nums[0] == target) {
            return 0;
        }
        return binSearch(nums, 0, nums.length - 1, target);
    }
}
