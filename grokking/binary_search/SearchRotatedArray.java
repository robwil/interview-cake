package grokking.binary_search;

public class SearchRotatedArray {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) { // first half is sorted ascending
                if (nums[left] <= target && target < nums[mid]) {
                    // target is between left and mid
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // second half is sorted ascending
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchRotatedArray s = new SearchRotatedArray();
        System.out.println(s.search(new int[]{10, 15, 1, 3, 8}, 15));
        System.out.println(s.search(new int[]{4, 5, 7, 9, 10, -1, 2}, 10));
        System.out.println(s.search(new int[]{1,3,5}, 2));
    }
}
