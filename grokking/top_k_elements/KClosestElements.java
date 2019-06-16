package grokking.top_k_elements;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class KClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (k <= 0 || k > arr.length) {
            return new ArrayList<>();
        }
        // Step 1: find closest element to X using binary search
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (right - left <= 2) {
                // don't think I need this because below logic will get both left and right
//                left = (Math.abs(arr[left]-x) < Math.abs(arr[right]-x)) ? left : right;
                break;
            }
            int mid = left + (right - left) / 2;
            if (arr[mid] == x) {
                left = mid;
                break;
            } else if (arr[mid] < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        int closest = left;

        // Step 2: expand from center of closest element, adding K elements from both directions
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((index1, index2) -> {
            int dist1 = Math.abs(arr[index1] - x);
            int dist2 = Math.abs(arr[index2] - x);
            if (dist1 == dist2) {
                return index1 - index2;
            }
            return dist1 - dist2;
        });
        left = closest;
        right = closest + 1;
        int added = 0;
        while (added < k) {
            if (left >= 0) {
                minHeap.offer(left);
            }
            if (right < arr.length) {
                minHeap.offer(right);
            }
            right++;
            left--;
            added++;
        }

        // Get K closest elements by polling K times off heap
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(arr[minHeap.poll()]);
        }
        result.sort(Comparator.comparingInt(i -> i));
        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = new KClosestElements().findClosestElements(new int[]{5, 6, 7, 8, 9}, 3, 7);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = new KClosestElements().findClosestElements(new int[]{2, 4, 5, 6, 9}, 3, 6);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = new KClosestElements().findClosestElements(new int[]{2, 4, 5, 6, 9}, 3, 10);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = new KClosestElements().findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = new KClosestElements().findClosestElements(new int[]{0,1,1,1,2,3,6,7,8,9}, 9, 4);
        System.out.println("'K' closest numbers to 'X' are: " + result);

    }
}

