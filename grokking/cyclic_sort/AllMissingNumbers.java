package grokking.cyclic_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AllMissingNumbers {

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static List<Integer> findNumbers(int[] nums) {
        List<Integer> missingNumbers = new ArrayList<>();

        // start with cyclic sort on input
        for (int i = 0; i < nums.length; ) {
            if (nums[i] == i + 1 || nums[i] == nums[nums[i] - 1]) {
                i++;
                continue;
            }
            swap(nums, i, nums[i] - 1);
        }
        System.out.println(Arrays.toString(nums));

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                missingNumbers.add(i + 1);
            }
        }
        // 2 4 1 2
        // 4 2 1 2
        // 2 2 1 4

        return missingNumbers;
    }

    public static void main(String[] args) {
        List<Integer> missing = AllMissingNumbers.findNumbers(new int[]{2, 4, 1, 2});
        System.out.println("Missing numbers: " + missing);

        missing = AllMissingNumbers.findNumbers(new int[]{2, 3, 1, 8, 2, 3, 5, 1});
        System.out.println("Missing numbers: " + missing);

        missing = AllMissingNumbers.findNumbers(new int[]{2, 3, 2, 1});
        System.out.println("Missing numbers: " + missing);
    }
}