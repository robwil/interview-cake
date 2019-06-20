package leetcode.recursion;

/**
 * https://leetcode.com/explore/learn/card/recursion-i/251/scenario-i-recurrence-relation/2378/
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
    public ListNode reverseListIterative(ListNode head) {
        // 1 -> 2 -> 3
        // 1 <- 2
        ListNode current = head;
        ListNode prev = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        System.out.println(head);
        System.out.println(new ReverseLinkedList().reverseListIterative(head));
    }
}
