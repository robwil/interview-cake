package grokking.two_pointers;

import java.util.*;

class TripletSumToZero {

//    private static int binSearch(int[] arr, int i, int j, int target) {
//        int left = 0;
//        int right = arr.length;
//        while (left < right) {
//            int mid = (right - left) / 2;
//            if (arr[mid] < target) { // search right half
//
//            }
//        }
//    }

    public static List<List<Integer>> searchTriplets(int[] arr) {
        LinkedHashSet<List<Integer>> triplets = new LinkedHashSet<>();
        Map<Integer, Integer> values = new HashMap<>();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            values.put(arr[i], i);
        }
        // once sorted, we can use pointers to navigate all pairs, then do binary search to see if a third exists
        // since we already spent O(n lg n) on sorting, the n searches won't increase time complexity.
        // an alternate approach would be to use a map - space O(N) - to remove the need to search
        int i = 0, j = arr.length - 1;
//        boolean moveI = true;
        while (i < j) {
            int currentSum = arr[i] + arr[j];
            Integer otherIndex = values.get(-currentSum);
            if (otherIndex != null && otherIndex != i && otherIndex != j) {
                triplets.add(Arrays.asList(arr[i], arr[j], otherIndex));
            }
            i++;
            j--;
//            if (moveI) {
//                i++;
//            } else {
//                j--;
//            }
//            moveI = !moveI;
        }
        // convert Set<List> to List<List>
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : triplets) {
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(TripletSumToZero.searchTriplets(new int[]{-3, 0, 1, 2, -1, 1, -2}));
        System.out.println(TripletSumToZero.searchTriplets(new int[]{-5, 2, -1, -2, 3}));
    }
}

/**
 * -3, 0, 1, 2, -1, 1, -2
 * -3, 0, 1
 * -3, 0, 2
 * -3, 0, -1
 * -3, 0, 1
 * -3, 0, -2
 */