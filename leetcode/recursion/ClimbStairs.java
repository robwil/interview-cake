package leetcode.recursion;

import java.util.HashMap;
import java.util.Map;

/*
Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Input: 4
Output: 5
1+1+1+1
1+2+1
2+1+1
1+1+2
2+2+1

 */
public class ClimbStairs {
    private Map<Integer, Integer> memo = new HashMap<>();
    public int climbStairs(int n) {
        Integer cacheValue = memo.get(n);
        if (cacheValue != null) {
            return cacheValue;
        }
        if (n == 0 || n == 1) {
            memo.put(n, 1);
            return 1;
        }
        int result = climbStairs(n - 1) + climbStairs(n - 2);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ClimbStairs().climbStairs(10));
    }
}
