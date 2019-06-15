package grokking.topological_sort;

import java.util.*;

class TaskSchedulingOrder {
    public int[] findOrder(int tasks, int[][] prerequisites) {
        List<Integer> sortedOrder = new ArrayList<>();

        // build adjacency lists and map for in-degrees
        Map<Integer, List<Integer>> adjacencyLists = new HashMap<>();
        Map<Integer, Integer> inDegrees = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int parent = prerequisites[i][0];
            int child = prerequisites[i][1];
            adjacencyLists.putIfAbsent(parent, new LinkedList<>());
            adjacencyLists.get(parent).add(child);
            inDegrees.putIfAbsent(child, 0);
            inDegrees.put(child, inDegrees.get(child) + 1);
        }
        // initial sources are those that are not in in-degree map yet
        Queue<Integer> sources = new LinkedList<>();
        for (int i = 0; i < tasks; i++) {
            if (!inDegrees.containsKey(i)) {
                sources.offer(i);
            }
        }
        // BFS
        while (!sources.isEmpty()) {
            Integer source = sources.poll();
            sortedOrder.add(source);
            List<Integer> children = adjacencyLists.get(source);
            if (children == null) {
                continue;
            }
            for (Integer child : children) {
                int currentCount = inDegrees.get(child);
                inDegrees.put(child, currentCount - 1);
                if (currentCount - 1 == 0) {
                    // no dependencies anymore, so can consider it a new source
                    sources.offer(child);
                }
            }
        }

        // handle for cyclic dependency (where we can't reach all tasks)
        if (sortedOrder.size() != tasks) {
            return new int[0];
        }

        return sortedOrder.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] result = new TaskSchedulingOrder().findOrder(3, new int[][]{new int[]{0, 1}, new int[]{1, 2}});
        System.out.println("expected [0, 1, 2] actual " + Arrays.toString(result));

        result = new TaskSchedulingOrder().findOrder(3,
                new int[][]{new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 0}});
        System.out.println("expected [] actual " + Arrays.toString(result));

        result = new TaskSchedulingOrder().findOrder(6, new int[][]{new int[]{2, 5}, new int[]{0, 5}, new int[]{0, 4},
                new int[]{1, 4}, new int[]{3, 2}, new int[]{1, 3}});
        System.out.println("expected [0 1 4 3 2 5] actual " + Arrays.toString(result));
    }
}