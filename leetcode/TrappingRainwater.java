package leetcode;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 */
public class TrappingRainwater {
    public int trap(int[] height) {
//        // let's start with the brute force approach.
//        // the rain trapped at each position i is equivalent to min(maxLeft, maxRight) - height[i]
//        int rainTrapped = 0;
//        for (int i = 0; i < height.length; i++) {
//            int maxLeft = 0;
//            int maxRight = 0;
//            for (int j = 0; j < height.length; j++) {
//                if (j < i && height[j] > maxLeft) {
//                    maxLeft = height[j];
//                } else if (j > i && height[j] > maxRight) {
//                    maxRight = height[j];
//                }
//            }
//            int capturedRain = Math.min(maxLeft, maxRight) - height[i];
//            if (capturedRain > 0) {
//                rainTrapped += capturedRain;
//            }
//        }
//        return rainTrapped;

//        // DP approach, memoizing maxLeft and maxRight calculations
//        int[] maxLeft = new int[height.length];
//        int currentMaxLeft = 0;
//        int[] maxRight = new int[height.length];
//        int currentMaxRight = 0;
//        for (int i = 0, j = height.length - 1; i < height.length && j >= 0; i++, j--) {
//            currentMaxLeft = Math.max(height[i], currentMaxLeft);
//            maxLeft[i] = currentMaxLeft;
//            currentMaxRight = Math.max(height[j], currentMaxRight);
//            maxRight[j] = currentMaxRight;
//        }
//        int rainTrapped = 0;
//        for (int i = 0; i < height.length; i++) {
//            int capturedRain = Math.min(maxLeft[i], maxRight[i]) - height[i];
//            if (capturedRain > 0) {
//                rainTrapped += capturedRain;
//            }
//        }
//        return rainTrapped;

        // simply even further by realizing I can actually combine all my loops.
        int currentMaxLeft = 0;
        int currentMaxRight = 0;
        int rainTrapped = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            // a taller bar exists on current bar's right side
            if (height[i] < height[j]) {
                if (height[i] < currentMaxLeft) { // also have a taller bar on left side, so add that bit
                    rainTrapped += currentMaxLeft - height[i];
                } else { // otherwise, found new max left
                    currentMaxLeft = height[i];
                }
                i++;
            } else { // right bar is lower than or equal to left bar
                if (height[j] < currentMaxRight) { // have a taller bar on right side
                    rainTrapped += currentMaxRight - height[j];
                } else {
                    currentMaxRight = height[j];
                }
                j--;
            }
        }
        return rainTrapped;
    }

    public static void main(String[] args) {
        int[] input = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("expected = 6, actual = " + new TrappingRainwater().trap(input));
    }
}
