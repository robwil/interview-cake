package grokking.cyclic_sort;

import java.util.Arrays;

public class CorruptPair {
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] findNumbers(int[] nums) {
        // perform cyclic sort first
        for (int i = 0; i < nums.length; ) {
            int j = nums[i] - 1;
            if (i == j || nums[i] == nums[j]) {
                i++;
                continue;
            }
            swap(nums, i, j);
        }
        System.out.println(Arrays.toString(nums));

        // at this point, the number in wrong spot is the duplicate number and the missing number is that index
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                // and return in sorted order, why not
                if (i + 1 < nums[i]) {
                    return new int[]{i + 1, nums[i]};
                }
                return new int[]{nums[i], i + 1};
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = CorruptPair.findNumbers(new int[]{3, 1, 2, 5, 2});
        System.out.println(nums[0] + ", " + nums[1]);
        nums = CorruptPair.findNumbers(new int[]{3, 1, 2, 3, 6, 4});
        System.out.println(nums[0] + ", " + nums[1]);
    }

}
