package grokking.two_heaps;

import java.util.*;

public class SlidingWindowMedian {

    private double getMedian(PriorityQueue<Integer> leftHalf, PriorityQueue<Integer> rightHalf) {
        System.out.println("leftHalf = " + leftHalf);
        System.out.println("rightHalf = " + rightHalf);
        if (leftHalf.size() == rightHalf.size()) {
            return ((double) leftHalf.peek() + (double) rightHalf.peek()) / 2.0;
        } else if (leftHalf.size() > rightHalf.size()) {
            return leftHalf.peek();
        }
        return rightHalf.peek();
    }

    private void addToHeaps(int num, PriorityQueue<Integer> firstHalfMaxHeap, PriorityQueue<Integer> secondHalfMinHeap) {
        if (firstHalfMaxHeap.isEmpty() || num <= firstHalfMaxHeap.peek()) {
            firstHalfMaxHeap.offer(num);
        } else {
            secondHalfMinHeap.offer(num);
        }
        rebalanceHeaps(firstHalfMaxHeap, secondHalfMinHeap);
    }

    private void rebalanceHeaps(PriorityQueue<Integer> firstHalfMaxHeap, PriorityQueue<Integer> secondHalfMinHeap) {
//        if (firstHalfMaxHeap.isEmpty() && secondHalfMinHeap.isEmpty()) {
//            return;
//        }
        if (firstHalfMaxHeap.size() > secondHalfMinHeap.size() + 1) {
            secondHalfMinHeap.offer(firstHalfMaxHeap.poll());
        } else if (secondHalfMinHeap.size() > firstHalfMaxHeap.size()) {
            firstHalfMaxHeap.offer(secondHalfMinHeap.poll());
        }
    }

    private void removeFromHeaps(int num, PriorityQueue<Integer> firstHalfMaxHeap, PriorityQueue<Integer> secondHalfMinHeap) {
        if (num <= firstHalfMaxHeap.peek()) {
            firstHalfMaxHeap.remove(num);
        } else {
            secondHalfMinHeap.remove(num);
        }
        rebalanceHeaps(firstHalfMaxHeap, secondHalfMinHeap);
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> firstHalfMaxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> secondHalfMinHeap = new PriorityQueue<>(Comparator.comparingInt(i -> i));
        List<Double> medians = new ArrayList<>();
        if (k <= 0) {
            return new double[]{};
        }

        // insert initial k-sized window into heaps
        for (int i = 0; i < k; i++) {
            addToHeaps(nums[i], firstHalfMaxHeap, secondHalfMinHeap);
        }
        medians.add(getMedian(firstHalfMaxHeap, secondHalfMinHeap));

        // walk the rest of array, calculating medians as we go
        for (int i = k; i < nums.length; i++) {
            removeFromHeaps(nums[i - k], firstHalfMaxHeap, secondHalfMinHeap);
            addToHeaps(nums[i], firstHalfMaxHeap, secondHalfMinHeap);
            medians.add(getMedian(firstHalfMaxHeap, secondHalfMinHeap));
        }

        return medians.stream().mapToDouble(i -> i).toArray();
    }


    public static void main(String[] args) {
        SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();
        double[] result = slidingWindowMedian.medianSlidingWindow(new int[]{1, 2, -1, 3, 5}, 2);
        System.out.println("Sliding window medians are: " + Arrays.toString(result));

        slidingWindowMedian = new SlidingWindowMedian();
        result = slidingWindowMedian.medianSlidingWindow(new int[]{1, 2, -1, 3, 5}, 3);
        System.out.println("Sliding window medians are: " + Arrays.toString(result));

        result = slidingWindowMedian.medianSlidingWindow(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, 2);
        System.out.println("Sliding window medians are: " + Arrays.toString(result));

        result = slidingWindowMedian.medianSlidingWindow(new int[]{2147483647, 1, 2, 3, 4, 5, 6, 7, 2147483647}, 2);
        System.out.println("Sliding window medians are: " + Arrays.toString(result));

        result = slidingWindowMedian.medianSlidingWindow(new int[]{-2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648}, 3);
        System.out.println("Sliding window medians are: " + Arrays.toString(result));

        result = slidingWindowMedian.medianSlidingWindow(new int[]{1, 2}, 1);
        System.out.println("Sliding window medians are: " + Arrays.toString(result));

        result = slidingWindowMedian.medianSlidingWindow(new int[]{1, 4, 2, 3}, 4);
        System.out.println("Sliding window medians are: " + Arrays.toString(result));
    }

}
