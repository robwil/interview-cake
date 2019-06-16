package grokking.top_k_elements;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FrequencySort {
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();
        // Step 1: get frequency of each char
        Map<Character, Integer> freqCount = new HashMap<>();
        for (char c : chars) {
            int count = freqCount.getOrDefault(c, 0);
            freqCount.put(c, count + 1);
        }
        // Step 2: use max heap to sort by frequency of chars
        PriorityQueue<Character> maxHeap = new PriorityQueue<>(Comparator.comparing(c -> freqCount.getOrDefault(c, 0)).reversed());
        for (Map.Entry<Character, Integer> entry : freqCount.entrySet()) {
            maxHeap.offer(entry.getKey());
        }
        // Step 3: output new string
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Character c = maxHeap.poll();
            // output c the same number of times as it appeared in original string
            for (int i = 0; i < freqCount.getOrDefault(c, 0); i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String result = new FrequencySort().frequencySort("Programming");
        System.out.println("Here is the given string after sorting characters by frequency: " + result);

        result = new FrequencySort().frequencySort("abcbab");
        System.out.println("Here is the given string after sorting characters by frequency: " + result);

        result = new FrequencySort().frequencySort("tree");
        System.out.println("Here is the given string after sorting characters by frequency: " + result);

        result = new FrequencySort().frequencySort("aaabbbccc");
        System.out.println("Here is the given string after sorting characters by frequency: " + result);
    }
}
