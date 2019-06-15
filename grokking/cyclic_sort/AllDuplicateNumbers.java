package grokking.cyclic_sort;

import java.util.*;

public class AllDuplicateNumbers {
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static List<Integer> findNumbers(int[] nums) {
        for (int i = 0; i < nums.length; ) {
            int j = nums[i] - 1;
            if (i != j) {
                if (nums[i] == nums[j]) {
                    i++;
                    continue;
                }
                swap(nums, i, j);
            } else {
                i++;
            }
        }
        Set<Integer> dupeNumbers = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // any item not in its right position is a duplicate
            if (nums[i] != i + 1) {
                dupeNumbers.add(nums[i]);
            }
        }
        return new ArrayList<>(dupeNumbers);
    }

    public static void main(String[] args) {
        List<Integer> duplicates = AllDuplicateNumbers.findNumbers(new int[]{3, 4, 4, 5, 5});
        System.out.println("Duplicates are: " + duplicates);

        duplicates = AllDuplicateNumbers.findNumbers(new int[]{5, 4, 7, 2, 3, 5, 3, 3});
        System.out.println("Duplicates are: " + duplicates);

    }
}
