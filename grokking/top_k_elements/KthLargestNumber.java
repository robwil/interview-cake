package grokking.top_k_elements;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestNumber {
    // Option 1: sort and then iterate K times
    // time O(N lg N) space O(N)
    private int findKthLargestSort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - 1 - k + 1];
    }

    // Option 2: heap of K elements
    // time O(N lg K) space O(K)
    private int findKthLargestHeap(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.offer(nums[i]);
                minHeap.poll(); // remove top element to keep size at K+1
            }
        }
        return minHeap.peek();
    }

    // Option 3: quicksort pivot
    // time O(N) average but O(N^2) worst case, O(1) space
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    private int pivot(int[] nums, int left, int right, int pivot) {
        // move pivot to end
        swap(nums, pivot, right);
        // compare each element to pivot, moving them to left
        for (int i = left; i <= right; i++) {
            if (nums[i] < nums[right]) {
                swap(nums, i, left);
                left++;
            }
        }
        // put pivot from its position at end (right) to next spot
        swap(nums, left, right);
        // return pivot's new position
        return left;
    }
    private int findKthLargestPivot(int[] nums, int k) {
        // pick random starting pivot
        Random r = new Random();
        int left = 0;
        int right = nums.length - 1;
        int expectedIndex = nums.length - 1 - k + 1;
        int pivotIndex = pivot(nums, left, right, r.nextInt(nums.length));
        while (pivotIndex != expectedIndex) {
            if (pivotIndex < expectedIndex) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
            if (left == right) {
                return nums[left];
            }
            int nextRandom = r.nextInt(right - left);
            pivotIndex = pivot(nums, left, right, left + nextRandom);
        }
        return nums[pivotIndex];
    }

    public int findKthLargest(int[] nums, int k) {
        if (k <= 0 || k > nums.length) {
            return -1;
        }
        return findKthLargestPivot(nums, k);
    }

    public static void main(String[] args) {
        // sorted version = -2,0,1,2,3,3,5,7,17
        int[] nums = new int[]{5,3,7,1,2,3,0,-2,17};
        System.out.println("expected = 17 when k = 1. actual = " + new KthLargestNumber().findKthLargest(nums, 1));
        System.out.println("expected = 7 when k = 2. actual = " + new KthLargestNumber().findKthLargest(nums, 2));
        System.out.println("expected = 5 when k = 3. actual = " + new KthLargestNumber().findKthLargest(nums, 3));
        System.out.println("expected = 3 when k = 4. actual = " + new KthLargestNumber().findKthLargest(nums, 4));
        System.out.println("expected = 3 when k = 5. actual = " + new KthLargestNumber().findKthLargest(nums, 5));
    }
}
