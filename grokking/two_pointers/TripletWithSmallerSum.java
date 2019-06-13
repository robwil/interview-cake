package grokking.two_pointers;

import java.util.Arrays;

class TripletWithSmallerSum {

    public static int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int found = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
//                System.out.printf("Considering %d, %d, %d\n", nums[i], nums[left], nums[right]);
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    found += right - left; // all values between left and right will be valid 3rd values
                    left++;
                } else {
                    right--;
                }
            }
        }
        return found;
    }

    public static void main(String[] args) {
        System.out.println(TripletWithSmallerSum.threeSumSmaller(new int[] { -1, 0, 2, 3 }, 3));
        System.out.println(TripletWithSmallerSum.threeSumSmaller(new int[] { -1, 4, 2, 1, 3 }, 5));
    }
}
