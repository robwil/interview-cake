package grokking.k_way_merge;

import leetcode.MergeTwoSortedLists;

import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;

    ListNode(int value) {
        this.val = value;
    }
}



class MergeKSortedLists {
    class QueueElement {
        public ListNode node;
        int k;

        QueueElement(ListNode node, int k) {
            this.node = node;
            this.k = k;
        }
    }

    // Approach 1: using min heap of size K
    // Time O(N lg K) and Space O(K)

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode preHead = new ListNode(-1);

        // Step 1: create K-sized min heap, where K is number of lists
        // initialize with min value from each list
        PriorityQueue<QueueElement> minHeap = new PriorityQueue<>(Comparator.comparingInt(elem -> elem.node.val));
        for (int k = 0; k < lists.length; k++) {
            if (lists[k] != null) {
                minHeap.offer(new QueueElement(lists[k], k));
                lists[k] = lists[k].next;
            }
        }

        // Step 2: while min heap is not empty, poll for min element
        ListNode current = preHead;
        while (!minHeap.isEmpty()) {
            QueueElement elem = minHeap.poll();
            ListNode node = elem.node;
            int k = elem.k;
            current.next = node;
            current = current.next;
            // need to add another element from kth list, that we just added element from
            if (lists[k] != null) {
                minHeap.offer(new QueueElement(lists[k], k));
                lists[k] = lists[k].next;
            }
        }

        return preHead.next;
    }

    // Approach 2: taken from Leetcode solution
    // use divide and conquer to merge K lists logK times, using divide and conquer
    // Time O(N lg K) and Space O(1)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode current = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = (l1 == null) ? l2 : l1;
        return preHead.next;
    }
    public ListNode mergeKListsConstantSpace(ListNode[] lists) {
        int k = lists.length;
        int interval = 1;
        while (interval < k) {
            for (int i = 0; i < k - interval; i += interval*2) {
                lists[i] = mergeTwoLists(lists[i], lists[i+interval]);
            }
            interval *= 2;
        }
        return k > 0 ? lists[0] : null;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(6);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);

        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(4);

        ListNode result = new MergeKSortedLists().mergeKListsConstantSpace(new ListNode[] { l1, l2, l3 });
        System.out.print("Here are the elements form the merged list: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}