package grokking.cyclic_sort;

import java.util.Arrays;

class DuplicateNumber {

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int findNumber(int[] nums) {
        for (int i = 0; i < nums.length; ) {
            int j = nums[i] - 1;
            if (i != j) {
                if (nums[i] == nums[j]) { // found dupe, so return it
                    return nums[i];
                }
                swap(nums, i, j);
            } else {
                i++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(DuplicateNumber.findNumber(new int[]{1, 4, 4, 3, 2}));
        System.out.println(DuplicateNumber.findNumber(new int[]{2, 1, 3, 3, 5, 4}));
        System.out.println(DuplicateNumber.findNumber(new int[]{2, 4, 1, 4, 4}));
    }
}