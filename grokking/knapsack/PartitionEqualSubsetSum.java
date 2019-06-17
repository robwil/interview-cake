package grokking.knapsack;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * <p>
 * Note:
 * <p>
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1, 5, 11, 5]
 * <p>
 * Output: true
 * <p>
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: [1, 2, 3, 5]
 * <p>
 * Output: false
 * <p>
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum {
    // Brute force recursive solution
    // Time O(2^N)
    // Space O(N)
    private boolean canPartitionRecursive(int[] nums, int i, int sum1, int sum2) {
        if (i == nums.length) {
            return sum1 == sum2;
        }
        // for each i in nums, we try putting it in sum1 or sum2 and see how that works out
        boolean result1 = canPartitionRecursive(nums, i + 1, sum1 + nums[i], sum2);
        boolean result2 = canPartitionRecursive(nums, i + 1, sum1, sum2 + nums[i]);
        return result1 || result2;
    }

    // Brute force recursive solution
    // Time O(2^N)
    // Space O(N)
    private boolean canPartitionRecursive2(int[] nums, int i, int sum) {
        if (i == nums.length) {
            return false;
        }
        if (sum == 0) {
            return true;
        }
        // for each i in nums, we try putting it in sum vs. leaving it out
        if (nums[i] <= sum) {
            if (canPartitionRecursive2(nums, i + 1, sum - nums[i])) {
                return true;
            }
        }
        return canPartitionRecursive2(nums, i + 1, sum);
    }

    public boolean canPartition(int[] nums) {
        // Original brute force. Since this uses 3 parameters, it's not as easy to memoize with DP.
//        return canPartitionRecursive(nums, 0, 0, 0);

        // Brute force utilizing the thought to find a subset with sum S/2
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0) {
            return false;
        }
//        return canPartitionRecursive2(nums, 0, sum / 2);

        // DP solution using bottom-up
        // the memoization here is on currentIndex and targetSum
//        sum = sum / 2;
//        boolean[][] dp = new boolean[sum + 1][nums.length];
//        for (int currentIndex = 0; currentIndex < nums.length; currentIndex++) {
//            dp[0][currentIndex] = true; // can always make sum = 0 with empty subset
//        }
//        for (int targetSum = 1; targetSum <= sum; targetSum++) {
//            dp[targetSum][0] = nums[0] == targetSum;
//        }
//        for (int targetSum = 1; targetSum <= sum; targetSum++) {
//            for (int currentIndex = 1; currentIndex < nums.length; currentIndex++) {
//                if (dp[targetSum][currentIndex - 1]) {
//                    dp[targetSum][currentIndex] = true;
//                } else if (targetSum - nums[currentIndex] >= 0) {
//                    dp[targetSum][currentIndex] = dp[targetSum - nums[currentIndex]][currentIndex - 1];
//                }
//            }
//        }

        // DP solution optimized for space (only single array row)
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int targetSum = 1; targetSum <= sum; targetSum++) {
            dp[targetSum] = nums[0] == targetSum;
        }
        for (int currentIndex = 1; currentIndex < nums.length; currentIndex++) {
            for (int targetSum = sum; targetSum >= 0; targetSum--) {
                if (targetSum >= nums[currentIndex]) {
                    dp[targetSum] = dp[targetSum - nums[currentIndex]];
                }
            }
        }
        return dp[sum];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 11, 5};
        System.out.println("expected true, actual = " + new PartitionEqualSubsetSum().canPartition(arr));

        arr = new int[]{1, 2, 3, 5};
        System.out.println("expected false, actual = " + new PartitionEqualSubsetSum().canPartition(arr));

        arr = new int[]{1, 1, 3, 4, 7};
        System.out.println("expected true, actual = " + new PartitionEqualSubsetSum().canPartition(arr));

        arr = new int[]{1, 2, 5};
        System.out.println("expected false, actual = " + new PartitionEqualSubsetSum().canPartition(arr));
    }
}
