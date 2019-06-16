package leetcode;

import java.util.ArrayList;
import java.util.List;

public class MergeTwoSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            List<Integer> list = new ArrayList<>();
            ListNode current = this;
            while (current != null) {
                list.add(current.val);
                current = current.next;
            }
            return list.toString();
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = null;
        while (l1 != null && l2 != null) {
            ListNode newNode;
            if (l1.val < l2.val) {
                newNode = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                newNode = new ListNode(l2.val);
                l2 = l2.next;
            }
            if (head == null) {
                head = newNode;
            } else {
                current.next = newNode;
            }
            current = newNode;
        }
        while (l1 != null) {
            ListNode newNode = new ListNode(l1.val);
            l1 = l1.next;
            if (head == null) {
                head = newNode;
            } else {
                current.next = newNode;
            }
            current = newNode;
        }
        while (l2 != null) {
            ListNode newNode = new ListNode(l2.val);
            l2 = l2.next;
            if (head == null) {
                head = newNode;
            } else {
                current.next = newNode;
            }
            current = newNode;
        }
        return head;
    }

    public ListNode mergeTwoListsInPlace(ListNode l1, ListNode l2) {
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

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(7);

        ListNode head2 = new ListNode(3);
        head2.next = new ListNode(8);

        System.out.println("List 1 = " + head);
        System.out.println("List 2 = " + head2);
        ListNode merged = new MergeTwoSortedLists().mergeTwoListsInPlace(head, head2);
        System.out.println("Merged = " + merged);

        head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(7);
        head2 = null;

        System.out.println("List 1 = " + head);
        System.out.println("List 2 = " + head2);
        merged = new MergeTwoSortedLists().mergeTwoListsInPlace(head, head2);
        System.out.println("Merged = " + merged);

        head = null;
        head2 = new ListNode(1);
        head2.next = new ListNode(2);

        System.out.println("List 1 = " + head);
        System.out.println("List 2 = " + head2);
        merged = new MergeTwoSortedLists().mergeTwoListsInPlace(head, head2);
        System.out.println("Merged = " + merged);

        head = null;
        head2 = null;

        System.out.println("List 1 = " + head);
        System.out.println("List 2 = " + head2);
        merged = new MergeTwoSortedLists().mergeTwoListsInPlace(head, head2);
        System.out.println("Merged = " + merged);
    }
}
