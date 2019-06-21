package leetcode;

public class LargestContainer {
    public int maxArea(int[] height) {
//         // Brute force N^2: compare every 2 possible lines
//         int maxArea = 0;
//         for (int i = 0; i < height.length; i++) {
//             for (int j = 0; j < height.length; j++) {
//                 if (i == j) continue;
//                 int area = Math.min(height[i], height[j]) * Math.abs(i-j);
//                 maxArea = Math.max(maxArea, area);
//             }
//         }
//         return maxArea;

        // Two pointer approach: O(N)
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * Math.abs(left-right);
            maxArea = Math.max(maxArea, area);
            // leave behind the smaller of the two heights
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
