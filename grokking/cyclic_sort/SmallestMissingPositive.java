package grokking.cyclic_sort;

public class SmallestMissingPositive {
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int firstMissingPositive(int[] nums) {
        // first cyclic sort
        for (int i = 0; i < nums.length; ) {
            int j = nums[i] - 1;
            if (i != j && j >= 0 && j < nums.length && nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }
//        System.out.println(Arrays.toString(nums));
        // find first missing positive within array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // edge case where all positive numbers of nums.length size exist, so just pick next one
        return nums.length + 1;
    }

    public static void main(String[] args) {
        System.out.println(SmallestMissingPositive.firstMissingPositive(new int[]{-3, 1, 5, 4, 2})); // expect 3
        System.out.println(SmallestMissingPositive.firstMissingPositive(new int[]{3, -2, 0, 1, 2})); // expect 4
        System.out.println(SmallestMissingPositive.firstMissingPositive(new int[]{3, 2, 5, 1})); // expect 4
        System.out.println(SmallestMissingPositive.firstMissingPositive(new int[]{1, 2, 0})); // expect 3
        System.out.println(SmallestMissingPositive.firstMissingPositive(new int[]{3, 4, -1, 1})); // expect 2
        System.out.println(SmallestMissingPositive.firstMissingPositive(new int[]{7, 8, 9})); // expect 1
        System.out.println(SmallestMissingPositive.firstMissingPositive(new int[]{1, 1})); // expect 1
    }
}
