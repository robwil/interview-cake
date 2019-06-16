package grokking.top_k_elements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

class Element {
    public int num;
    public int frequency;
    public int insertionOrder;

    public Element(int num, int frequency, int insertionOrder) {
        this.num = num;
        this.frequency = frequency;
        this.insertionOrder = insertionOrder;
    }

    @Override
    public String toString() {
        return "Element{" +
                "num=" + num +
                ", frequency=" + frequency +
                ", insertionOrder=" + insertionOrder +
                '}';
    }
}

class FrequencyStack {
    // My Solution based on heaps:
    // O(log N) insertion time
    // O(N) space

//    private Map<Integer, Integer> freqCounts = new HashMap<>();
//    private PriorityQueue<Element> maxHeap = new PriorityQueue<>((i1, i2) -> {
//        Integer count1 = i1.frequency;
//        Integer count2 = i2.frequency;
//        if (count1.equals(count2)) { // tie breaker is to use insertion order
//            return i2.insertionOrder - i1.insertionOrder;
//        }
//        return count2 - count1;
//    });
//    private int insertionCount = 0;
//
//    public void push(int num) {
//        freqCounts.put(num, freqCounts.getOrDefault(num, 0) + 1);
//        maxHeap.offer(new Element(num, freqCounts.get(num), insertionCount++));
//    }
//
//    public int pop() {
//        Element elem = maxHeap.poll();
//        int num = elem.num;
//        int count = freqCounts.get(num);
//        if (count - 1 > 0) {
//            freqCounts.put(num, count - 1);
//        } else {
//            freqCounts.remove(num);
//        }
//        return num;
//    }

    // below solution taken from Leetcode Solution explanation.
    Map<Integer, Integer> freq;
    Map<Integer, Stack<Integer>> group;
    int maxfreq;

    public FrequencyStack() {
        freq = new HashMap();
        group = new HashMap();
        maxfreq = 0;
    }

    public void push(int x) {
        int f = freq.getOrDefault(x, 0) + 1;
        freq.put(x, f);
        if (f > maxfreq) {
            maxfreq = f;
        }

        group.computeIfAbsent(f, z-> new Stack()).push(x);
    }

    public int pop() {
        int x = group.get(maxfreq).pop();
        freq.put(x, freq.get(x) - 1);
        if (group.get(maxfreq).size() == 0) {
            group.remove(maxfreq);
            maxfreq--;
        }
        return x;
    }

    public static void main(String[] args) {
        FrequencyStack frequencyStack = new FrequencyStack();
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(3);
        frequencyStack.push(2);
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(5);
        // 1 1
        // 2 2 2
        // 3
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
    }
}