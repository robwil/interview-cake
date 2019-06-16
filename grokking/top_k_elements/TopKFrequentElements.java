package grokking.top_k_elements;

import java.util.*;

class TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
        // Step 0: error checking
        if (k <= 0 || k > nums.length) {
            return new ArrayList<>();
        }

        // Step 1: calculate frequencies using HashMap
        Map<Integer, Integer> freqCounts = new HashMap<>();
        for (int num : nums) {
            int count = freqCounts.getOrDefault(num, 0);
            freqCounts.put(num, count + 1);
        }

        // Step 2: find K top frequent elements using minHeap of K size
        PriorityQueue<Integer> mostFrequent = new PriorityQueue<>(Comparator.comparingInt(elem -> freqCounts.getOrDefault(elem, 0)));
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : freqCounts.entrySet()) {
            if (i < k) {
                mostFrequent.offer(entry.getKey());
            } else if (entry.getValue() > freqCounts.getOrDefault(mostFrequent.peek(), 0)) {
                mostFrequent.offer(entry.getKey());
                mostFrequent.poll();
            }
            i++;
        }
        return new ArrayList<>(mostFrequent);
    }

    public static void main(String[] args) {
        List<Integer> result = new TopKFrequentElements().topKFrequent(new int[]{1, 3, 5, 12, 11, 12, 11}, 2);
        System.out.println("Here are the K frequent numbers: " + result);

        result = new TopKFrequentElements().topKFrequent(new int[]{5, 12, 11, 3, 11}, 2);
        System.out.println("Here are the K frequent numbers: " + result);

        result = new TopKFrequentElements().topKFrequent(new int[]{4, 1, -1, 2, -1, 2, 3}, 2);
        System.out.println("Here are the K frequent numbers: " + result);
    }
}