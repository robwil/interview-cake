package grokking.top_k_elements;

import java.util.PriorityQueue;

/**
 * Given ‘N’ ropes with different lengths, we need to connect these ropes into one big rope with minimum cost. The cost of connecting two ropes is equal to the sum of their lengths.
 *
 * Example 1:
 *
 * Input: [1, 3, 11, 5]
 * Output: 33
 * Explanation: First connect 1+3(=4), then 4+5(=9), and then 9+11(=20). So the total cost is 33 (4+9+20)
 * Example 2:
 *
 * Input: [1, 3, 11, 5, 2]
 * Output: 42
 * Explanation: First connect 1+2(=3), then 3+3(=6), 6+5(=11), 11+11(=22). Total cost is 42 (3+6+11+22)
 * Input: [1, 3, 11, 5, 2]
 * Output: 42
 * Explanation: First connect 1+2(=3), then 3+3(=6), 6+5(=11), 11+11(=22). Total cost is 42 (3+6+11+22)
 */
class ConnectRopes {

    public static int minimumCostToConnectRopes(int[] ropeLengths) {
        if (ropeLengths.length == 0) {
            return 0;
        }
        // intuition: it's always best to merge ropes in ascending sorted order, so we need to sort.
        // approach: heap sort using min heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < ropeLengths.length; i++) {
            minHeap.offer(ropeLengths[i]);
        }
        int cost = 0;
        int prevSize = minHeap.poll();
        while (!minHeap.isEmpty()) {
            int item = minHeap.poll();
            cost += (prevSize + item);
            prevSize = prevSize + item;
        }
        return cost;
    }

    public static void main(String[] args) {
        int result = ConnectRopes.minimumCostToConnectRopes(new int[] { 1, 3, 11, 5 });
        System.out.println("Minimum cost to connect ropes: " + result);
        result = ConnectRopes.minimumCostToConnectRopes(new int[] { 1, 3, 11, 5, 2 });
        System.out.println("Minimum cost to connect ropes: " + result);
    }
}

