package grokking.cyclic_sort;

import java.util.Arrays;

class MissingNumber {

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int findMissingNumber(int[] nums) {
        // perform cyclic sort first, but need to deal with the extra element
        for (int i = 0; i < nums.length; ) {
            if (nums[i] == i || nums[i] >= nums.length) {
                i++;
                continue;
            }
            swap(nums, i, nums[i]);
        }
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        System.out.println(MissingNumber.findMissingNumber(new int[]{4, 0, 3, 1}));
        System.out.println(MissingNumber.findMissingNumber(new int[]{8, 3, 5, 2, 4, 6, 0, 1}));
        System.out.println(MissingNumber.findMissingNumber(new int[]{0}));
        System.out.println(MissingNumber.findMissingNumber(new int[]{1, 0, 2, 4, 3}));
    }
}
