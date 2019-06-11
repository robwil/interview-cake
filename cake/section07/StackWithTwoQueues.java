package cake.section07;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

// https://leetcode.com/problems/implement-stack-using-queues/
public class StackWithTwoQueues {
    static class MyStack {

        Queue<Integer> outQueue;
        Queue<Integer> overflowQueue;

        /** Initialize your data structure here. */
        public MyStack() {
            overflowQueue = new LinkedList<>();
            outQueue = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            if (outQueue.isEmpty()) {
                outQueue.offer(x);
                return;
            }
            // otherwise, move current outQueue item to overflowQueue first
            overflowQueue.offer(outQueue.poll());
            outQueue.offer(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            int returnVal = outQueue.poll();
            // move N-1 items from overflow to outQueue, then swap pointers
            while (overflowQueue.size() > 1) {
                outQueue.offer(overflowQueue.poll());
            }
            Queue<Integer> tmp = outQueue;
            outQueue = overflowQueue;
            overflowQueue = tmp;
            return returnVal;
        }

        /** Get the top element. */
        public int top() {
            return outQueue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return outQueue.isEmpty() && overflowQueue.isEmpty();
        }
    }

    @Test
    public void basicQueueOperationsTest() {
        final MyStack q = new MyStack();
        q.push(1);
        q.push(2);
        q.push(3);
        assertFalse(q.empty());
        assertEquals("pop #1", 3, q.pop());
        assertEquals("pop #2", 2, q.pop());
        assertFalse(q.empty());
        q.push(4);
        assertEquals("pop #3", 4, q.pop());
        assertEquals("pop #4", 1, q.pop());
        assertTrue(q.empty());
    }

    @Test(expected = Exception.class)
    public void exceptionWhenDequeueFromNewQueueTest() {
        final MyStack q = new MyStack();
        q.pop();
    }

    @Test(expected = Exception.class)
    public void exceptionWhenDequeueFromEmptyQueueTest() {
        final MyStack q = new MyStack();
        q.push(1);
        q.push(2);
        q.pop();
        q.pop();
        q.pop();
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(StackWithTwoQueues.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}