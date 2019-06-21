package grokking.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/fruit-into-baskets/
 * <p>
 * Input: Fruit=['A', 'B', 'C', 'A', 'C']
 * Output: 3
 * Explanation: We can put 2 'C' in one basket and one 'A' in the other from the subarray ['C', 'A', 'C']
 * <p>
 * Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
 * Output: 5
 * Explanation: We can put 3 'B' in one basket and two 'C' in the other basket.
 * This can be done if we start with the second letter: ['B', 'C', 'B', 'B', 'C']
 */
public class FruitIntoBaskets {
    public int totalFruit(int[] tree) {
        // Initial thought: this is basically longest k-distinct string problem with k=2
        // since k=2, I can just maintain an int[2] instead of a full hash map
        // but I'm going to use hash map here anyway because I want practice with that pattern
        int K = 2;
        Map<Integer, Integer> distinctCounts = new HashMap<>();
        int windowStart = 0;
        int max = 0;
        for (int windowEnd = 0; windowEnd < tree.length; windowEnd++) {
            // for each item, add windowEnd. if distinct count size breaks constraint, shrink from windowStart
            distinctCounts.put(tree[windowEnd], distinctCounts.getOrDefault(tree[windowEnd], 0) + 1);
            while (distinctCounts.size() > K) {
                int count = distinctCounts.get(tree[windowStart]);
                if (count == 1) {
                    distinctCounts.remove(tree[windowStart]);
                } else {
                    distinctCounts.put(tree[windowStart], count - 1);
                }
                windowStart++;
            }
            max = Math.max(max, windowEnd - windowStart + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        FruitIntoBaskets f = new FruitIntoBaskets();
        System.out.println("Maximum number of fruits: " +
                f.totalFruit(new int[]{'A', 'B', 'C', 'A', 'C'}));
        System.out.println("Maximum number of fruits: " +
                f.totalFruit(new int[]{'A', 'B', 'C', 'B', 'B', 'C'}));
        System.out.println("Maximum number of fruits: " +
                f.totalFruit(new int[]{'B', 'B', 'C', 'B', 'B', 'C'}));
    }
}
