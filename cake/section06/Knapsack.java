package cake.section06;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

class Knapsack {

    public static int work = 0;

    public int solveKnapsack0(int[] profits, int[] weights, int capacity) {
        Knapsack.work = 0;
        return this.knapsackRecursive0(profits, weights, capacity, 0);
    }

    // Brute Force solution
    // Time O(2^N) since each branch requires checking 2 things, and depth of recursion is N for each item
    // Space O(N) from call stack
    private int knapsackRecursive0(int[] profits, int[] weights, int capacity, int currentIndex) {
        // Error checking
        if (profits.length == 0 || weights.length != profits.length || currentIndex > weights.length - 1) {
            return 0;
        }

        // Base case: no more capacity
        if (capacity <= 0) {
            return 0;
        }

        Knapsack.work++;

        // Compare profit without item vs. profit with item (both calculated recursively)
        int profitWithout = knapsackRecursive0(profits, weights, capacity, currentIndex + 1);
        if (weights[currentIndex] <= capacity) {
            // only check including item if it doesn't require too much capacity
            int profitWith = profits[currentIndex] + knapsackRecursive0(profits, weights, capacity - weights[currentIndex], currentIndex + 1);
            return Math.max(profitWithout, profitWith);
        }
        return profitWithout;
    }

    // Top-down memoization approach, memoizing on the recursive call
    // I did this kind of wrong, since it only uses currentIndex as the key, without considering capacity.
    public int solveKnapsack1(int[] profits, int[] weights, int capacity) {
        Knapsack.work = 0;
        Map<Integer, Integer> memo = new HashMap<>(profits.length);
        return this.knapsackRecursive1(memo, profits, weights, capacity, 0);
    }

    private int knapsackRecursive1(Map<Integer, Integer> memo, int[] profits, int[] weights, int capacity, int currentIndex) {
        // Error checking
        if (profits.length == 0 || weights.length != profits.length || currentIndex > weights.length - 1) {
            return 0;
        }

        // Base case: no more capacity
        if (capacity <= 0) {
            return 0;
        }

        Knapsack.work++;

        // Compare profit without item vs. profit with item (both calculated recursively)
        int profitWithout = knapsackRecursive1(memo, profits, weights, capacity, currentIndex + 1);
        int profitWith = 0;
        if (weights[currentIndex] <= capacity) {
            // only check including item if it doesn't require too much capacity

            // If already calculated this sub-problem, no need to recurse
            if (memo.containsKey(currentIndex)) {
                profitWith = memo.get(currentIndex);
            } else {
                profitWith = profits[currentIndex] + knapsackRecursive1(memo, profits, weights, capacity - weights[currentIndex], currentIndex + 1);
                memo.put(currentIndex, profitWith);
            }
        }
        return Math.max(profitWithout, profitWith);
    }

    // Top-down memoization approach, using the more standard 2D array approach to memoize the capacity/currentIndex results
    public int solveKnapsack2(int[] profits, int[] weights, int capacity) {
        Knapsack.work = 0;
        Integer[][] memo = new Integer[weights.length][capacity + 1];
        return this.knapsackRecursive2(memo, profits, weights, capacity, 0);
    }

    private int knapsackRecursive2(Integer[][] memo, int[] profits, int[] weights, int capacity, int currentIndex) {
        // Error checking
        if (profits.length == 0 || weights.length != profits.length || currentIndex > weights.length - 1) {
            return 0;
        }

        // Base case: no more capacity
        if (capacity <= 0) {
            return 0;
        }

        // Check memoization to see if we already calculated this sub-problem
        if (memo[currentIndex][capacity] != null) {
            return memo[currentIndex][capacity];
        }

        Knapsack.work++;

        // Compare profit without item vs. profit with item (both calculated recursively)
        int profitWithout = knapsackRecursive2(memo, profits, weights, capacity, currentIndex + 1);
        int profitWith = 0;
        if (weights[currentIndex] <= capacity) {
            // only check including item if it doesn't require too much capacity
            profitWith = profits[currentIndex] + knapsackRecursive2(memo, profits, weights, capacity - weights[currentIndex], currentIndex + 1);
        }
        int maxProfit = Math.max(profitWithout, profitWith);
        memo[currentIndex][capacity] = maxProfit;
        return maxProfit;
    }

    // Bottom-up iterative approach, using the standard 2D array approach to memoize the capacity/currentIndex results
    public int solveKnapsack3(int[] profits, int[] weights, int capacity) {
        Knapsack.work = 0;
        Integer[][] results = new Integer[weights.length][capacity + 1];
        // Initialize c=0 row with all 0s, since we can't get any profit with 0 capacity
        for (int i = 0; i < weights.length; i++) {
            results[i][0] = 0;
        }

        // Initialize i=0 column
        for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {
            // we simply include it if it fits, otherwise don't include it
            if (weights[0] <= currentCapacity) {
                results[0][currentCapacity] = profits[0];
            }
        }

        for (int currentCapacity = 1; currentCapacity <= capacity; currentCapacity++) {
            for (int i = 1; i < weights.length; i++) {
                Knapsack.work++;

                int profitWithout = results[i-1][currentCapacity];
                int profitWith = 0;
                if (weights[i] <= currentCapacity) {
                    profitWith = profits[i] + results[i-1][currentCapacity - weights[i]];
                }
                results[i][currentCapacity] = Math.max(profitWith, profitWithout);
            }
        }
        return results[weights.length-1][capacity];
    }

    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};

        int[] capacitiesToCheck = {7, 6};
        int[] expectedResults = {22, 17};
        for (int i = 0; i < capacitiesToCheck.length; i++) {
            int capacity = capacitiesToCheck[i];
            System.out.println("Total knapsack profit for capacity = " + capacity);
            int maxProfit = ks.solveKnapsack0(profits, weights, capacity);
            System.out.println("\tExpected profit = " + expectedResults[i]);
            System.out.println("\tAlgo #0  profit = " + maxProfit);
            System.out.println("\tAlgo #0  work  = " + Knapsack.work);
            maxProfit = ks.solveKnapsack1(profits, weights, capacity);
            System.out.println("\tAlgo #1  profit = " + maxProfit);
            System.out.println("\tAlgo #1  work  = " + Knapsack.work);
            maxProfit = ks.solveKnapsack2(profits, weights, capacity);
            System.out.println("\tAlgo #2  profit = " + maxProfit);
            System.out.println("\tAlgo #2  work  = " + Knapsack.work);
            maxProfit = ks.solveKnapsack3(profits, weights, capacity);
            System.out.println("\tAlgo #3  profit = " + maxProfit);
            System.out.println("\tAlgo #3  work  = " + Knapsack.work);
        }
    }
}
