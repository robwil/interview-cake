package grokking.cyclic_sort;

import java.util.*;

public class FirstKMissingPositives {
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static List<Integer> findNumbers(int[] nums, int k) {
        List<Integer> missingNumbers = new ArrayList<>();
        if (k == 0) {
            return missingNumbers;
        }
        // first cyclic sort
        for (int i = 0; i < nums.length; ) {
            int j = nums[i] - 1;
            if (i != j && j >= 0 && j < nums.length && nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }
        System.out.println(Arrays.toString(nums));
        // find  missing positives within array, stopping when k becomes 0

        Set<Integer> outOfBoundsNumbers = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                missingNumbers.add(i+1);
                if (--k == 0) {
                    return missingNumbers;
                }
                // keep track of out-of-bounds numbers (greater than array length), as those might be needed later to fulfill k requirement
                if (nums[i] >= nums.length + 1) {
                    outOfBoundsNumbers.add(nums[i]);
                }
            }
        }
        // edge case where all positive numbers of nums.length size exist, so just pick next ones
        int n = nums.length + 1;
        while (k > 0) {
            if (!outOfBoundsNumbers.contains(n)) {
                missingNumbers.add(n);
            }
            n++;
            k--;
        }
        return missingNumbers;
    }

    public static void main(String[] args) {
        List<Integer> missingNumbers = FirstKMissingPositives.findNumbers(new int[]{3, -1, 4, 5, 5}, 3);
        System.out.println("Missing numbers: " + missingNumbers);

        missingNumbers = FirstKMissingPositives.findNumbers(new int[]{2, 3, 4}, 3);
        System.out.println("Missing numbers: " + missingNumbers);

        missingNumbers = FirstKMissingPositives.findNumbers(new int[]{-2, -3, 4}, 2);
        System.out.println("Missing numbers: " + missingNumbers);
    }
}
