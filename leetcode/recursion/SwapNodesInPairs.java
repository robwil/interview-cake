package leetcode.recursion;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        // detect cycle while outputting to string
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        ListNode slow = this, fast = this;
        while (slow != null) {
            sb.append(slow.val);
            slow = slow.next;
            if (slow != null) {
                sb.append(", ");
            }
            if (fast != null) {
                fast = fast.next;
            }
            if (fast != null) {
                fast = fast.next;
                if (fast == slow) {
                    return "[cycle detected in list]";
                }
            }
        }
        sb.append(']');
        return sb.toString();
    }
}

public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        return helper(head, null);
    }

    private ListNode helper(ListNode head, ListNode prev) {
        if (head == null || head.next == null) {
            return head;
        }
        // swap head and head.next
        ListNode next = head.next;
        ListNode nextnext = head.next.next;
        next.next = head;
        if (prev != null) {
            prev.next = next;
        }
        head.next = helper(nextnext, head);
        return next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode after = new SwapNodesInPairs().swapPairs(head);
        System.out.println(after);
    }
}
