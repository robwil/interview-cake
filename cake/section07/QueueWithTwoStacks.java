package cake.section07;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;

// https://leetcode.com/problems/implement-queue-using-stacks/
public class QueueWithTwoStacks {
    public static class MyQueue {

        private Deque<Integer> inStack;
        private Deque<Integer> outStack;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            inStack = new ArrayDeque<>();
            outStack = new ArrayDeque<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            // always insert to inStack
            inStack.push(x);
        }

        // Whenever we need to peek or pop, we must reverse the in stack once
        // by putting all those elements into the out stack.
        private void moveInStackToOutStack() {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (outStack.isEmpty()) {
                moveInStackToOutStack();
            }
            return outStack.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (outStack.isEmpty()) {
                moveInStackToOutStack();
            }
            return outStack.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }


    }

    @Test
    public void basicQueueOperationsTest() {
        final MyQueue q = new MyQueue();
        q.push(1);
        q.push(2);
        q.push(3);
        assertEquals("dequeue #1", 1, q.pop());
        assertEquals("dequeue #2", 2, q.pop());
        q.push(4);
        assertEquals("dequeue #3", 3, q.pop());
        assertEquals("dequeue #4", 4, q.pop());
    }

    @Test(expected = Exception.class)
    public void exceptionWhenDequeueFromNewQueueTest() {
        final MyQueue q = new MyQueue();
        q.pop();
    }

    @Test(expected = Exception.class)
    public void exceptionWhenDequeueFromEmptyQueueTest() {
        final MyQueue q = new MyQueue();
        q.push(1);
        q.push(2);
        q.pop();
        q.pop();
        q.pop();
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(QueueWithTwoStacks.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
