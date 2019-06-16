package grokking.top_k_elements;

import java.util.*;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int distFromOrigin() {
        // ignoring sqrt
        return (x * x) + (y * y);
    }
}

class KClosestPointsToOrigin {

    public static List<Point> findClosestPoints(Point[] points, int k) {
        if (k <= 0 || k > points.length) {
            return new ArrayList<>();
        }
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(Comparator.comparingInt(Point::distFromOrigin).reversed());
        for (int i = 0; i < k; i++) {
            maxHeap.offer(points[i]);
        }
        for (int i = k; i < points.length; i++) {
            if (points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()) {
                maxHeap.offer(points[i]);
                maxHeap.poll();
            }
        }
        return new ArrayList<>(maxHeap);
    }

    // different signature for Leetcode version
    private int distFromOrigin(int[] point) {
        // ignore sqrt for simpler comparison
        return (point[0] * point[0] + point[1] * point[1]);
    }

    public int[][] kClosest(int[][] points, int k) {
        if (k <= 0 || k > points.length) {
            return null;
        }
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((p1, p2) -> distFromOrigin(p2) - distFromOrigin(p1));
        for (int i = 0; i < k; i++) {
            maxHeap.offer(points[i]);
        }
        for (int i = k; i < points.length; i++) {
            if (distFromOrigin(points[i]) < distFromOrigin(maxHeap.peek())) {
                maxHeap.offer(points[i]);
                maxHeap.poll();
            }
        }
        return new ArrayList<>(maxHeap).toArray(new int[0][0]);
    }

    public static void main(String[] args) {
        Point[] points = new Point[]{new Point(1, 3), new Point(3, 4), new Point(2, -1)};
        List<Point> result = KClosestPointsToOrigin.findClosestPoints(points, 2);
        System.out.print("Here are the k points closest the origin: ");
        for (Point p : result)
            System.out.print("[" + p.x + " , " + p.y + "] ");
    }
}
