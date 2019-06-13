package grokking.two_pointers;

import java.util.Arrays;

class TripletSumCloseToTarget {

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDistance = Math.abs(target - (nums[0] + nums[1] + nums[2]));
        int minSum = (nums[0] + nums[1] + nums[2]);
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                }
                if (Math.abs(target - sum) < minDistance) {
                    minDistance = Math.abs(target - sum);
                    minSum = sum;
                }
                if (sum < target) {
                    left++;
                }
                if (sum > target) {
                    right--;
                }
            }
        }
        return minSum;
    }

    public static void main(String[] args) {
        System.out.println(TripletSumCloseToTarget.threeSumClosest(new int[]{-2, 0, 1, 2}, 2));
        System.out.println(TripletSumCloseToTarget.threeSumClosest(new int[]{-3, -1, 1, 2}, 1));
        System.out.println(TripletSumCloseToTarget.threeSumClosest(new int[]{1, 0, 1, 1}, 100));
    }
}
