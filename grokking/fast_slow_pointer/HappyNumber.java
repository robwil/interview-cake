package grokking.fast_slow_pointer;

/**
 * https://leetcode.com/problems/happy-number/
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 *
 * Example:
 *
 * Input: 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */

public class HappyNumber {
    private int nextIteration(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n = n / 10;
        }
        return sum;
    }

    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = nextIteration(slow);
            fast = nextIteration(nextIteration(fast));
        } while (slow != fast && fast != 1);
        return fast == 1;
    }

    public static void main(String[] args) {
        System.out.println(new HappyNumber().nextIteration(82));
        System.out.println(new HappyNumber().isHappy(23));
        System.out.println(new HappyNumber().isHappy(12));
    }
}
