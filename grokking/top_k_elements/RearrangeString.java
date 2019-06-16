package grokking.top_k_elements;

import java.util.*;

class RearrangeString {

    public String reorganizeString(String S) {
        char[] chars = S.toCharArray();
        // Step 1: get frequencies
        Map<Character, Integer> freqCounts = new HashMap<>();
        for (char c : chars) {
            int count = freqCounts.getOrDefault(c, 0);
            freqCounts.put(c, count + 1);
        }
        // Step 2: put all entries in maxHeap ordered by freqCount
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((c1, c2) -> freqCounts.getOrDefault(c2, 0) - freqCounts.getOrDefault(c1, 0));
        for (Character c : freqCounts.keySet()) {
            maxHeap.offer(c);
        }
        // Step 3: iterate through heap, re-adding elements that still have counts available
        StringBuilder sb = new StringBuilder();
        Character lastChar = null;
        while (!maxHeap.isEmpty()) {
            Character c = maxHeap.poll();
            if (lastChar != null && freqCounts.getOrDefault(lastChar, 0) > 0) {
                maxHeap.offer(lastChar);
            }
            sb.append(c);
            freqCounts.put(c, freqCounts.get(c) - 1);
            lastChar = c;
        }
        String s = sb.toString();
        return s.length() == S.length() ? s : "";
    }


    public static void main(String[] args) {
        System.out.println("Rearranged string: " + new RearrangeString().reorganizeString("aappp"));
        System.out.println("Rearranged string: " + new RearrangeString().reorganizeString("Programming"));
        System.out.println("Rearranged string: " + new RearrangeString().reorganizeString("aapa"));
        System.out.println("Rearranged string: " + new RearrangeString().reorganizeString("tree"));
        System.out.println("Rearranged string: " + new RearrangeString().reorganizeString("vvvlo"));
    }
}