package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * Your algorithm should run in O(n) complexity.
 * <p>
 * Example:
 * <p>
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSubsequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> seenNumbers = new HashSet<>();
        for (int num : nums) {
            seenNumbers.add(num);
        }
        int maxSequenceLength = 0;
        for (int i = 0; i < nums.length; i++) {
            // don't do work for cases where the previous number exists in the set,
            // because we'll discover the largest sequence starting at nums[i]-1 wherever that is in the array
            int currentNum = nums[i];
            if (!seenNumbers.contains(currentNum - 1)) {
                // but, for each number that represents the start of some subsequence, let's calculate how long that sequence is
                int sequenceLength = 1;
                while (seenNumbers.contains(currentNum + 1)) {
                    sequenceLength++;
                    currentNum++;
                }
                // and consider it for the max
                if (sequenceLength > maxSequenceLength) {
                    maxSequenceLength = sequenceLength;
                }
            }
        }
        return maxSequenceLength;
    }

    public static void main(String[] args) {
        System.out.println("Expected 4: " + new LongestConsecutiveSubsequence().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }
}
