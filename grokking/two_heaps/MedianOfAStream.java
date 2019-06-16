package grokking.two_heaps;

import java.util.PriorityQueue;

class MedianOfAStream {

    PriorityQueue<Integer> firstHalfMaxHeap;
    PriorityQueue<Integer> secondHalfMinHeap;

    public MedianOfAStream() {
        firstHalfMaxHeap = new PriorityQueue<>((i, j) -> j - i);
        secondHalfMinHeap = new PriorityQueue<>((i, j) -> i - j);
    }

    public void addNum(int num) {
        if (firstHalfMaxHeap.isEmpty() || num < firstHalfMaxHeap.peek()) {
            firstHalfMaxHeap.offer(num);
        } else {
            secondHalfMinHeap.offer(num);
        }

        // must balance the two heaps if they are more than 1 off
        int firstHalfSize = firstHalfMaxHeap.size();
        int secondHalfSize = secondHalfMinHeap.size();
        if (Math.abs(firstHalfSize - secondHalfSize) > 1) {
            if (firstHalfSize < secondHalfSize) {
                firstHalfMaxHeap.offer(secondHalfMinHeap.poll());
            } else {
                secondHalfMinHeap.offer(firstHalfMaxHeap.poll());
            }
        }

//        System.out.println("firstHalf = " + firstHalfMaxHeap);
//        System.out.println("secondHalf = " + secondHalfMinHeap);
    }

    public double findMedian() {
        int firstHalfSize = firstHalfMaxHeap.size();
        int secondHalfSize = secondHalfMinHeap.size();
        if (firstHalfSize == 0 && secondHalfSize == 0) {
            return -1;
        }
        if (firstHalfSize > 0 && firstHalfSize == secondHalfSize) {
            // even number of items, so take average of two top items
            return (firstHalfMaxHeap.peek() + secondHalfMinHeap.peek()) / 2.0;
        } else if (firstHalfSize < secondHalfSize) {
            return secondHalfMinHeap.peek();
        }
        return firstHalfMaxHeap.peek();
    }

    public static void main(String[] args) {
        MedianOfAStream medianOfAStream = new MedianOfAStream();
        medianOfAStream.addNum(3);
        medianOfAStream.addNum(1);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.addNum(5);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.addNum(4);
        System.out.println("The median is: " + medianOfAStream.findMedian());
    }
}