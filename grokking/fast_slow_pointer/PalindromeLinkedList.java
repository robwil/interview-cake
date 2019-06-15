package grokking.fast_slow_pointer;

class PalindromeLinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // reverse list (or sublist if edge != null), returning new head
    private static ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode previous = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode current = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            current = current.next;
            fast = fast.next.next;
            if (current == fast) {
                return false; // cycle detected, so must be bad input
            }
        }
        // short-circuit case where fast is still head, it means it's single element list which is a palindrome
        if (fast == head) {
            return true;
        }
        // otherwise, current is now middle node. save that for later
        ListNode middle = current;
        // next step is to reverse second half of list, using standard in-place reverse technique
        ListNode secondHalf = reverseList(middle);
        // now, let's compare the list starting from head and previous (reverse half's head)
        current = head;
        fast = secondHalf;
        boolean isPalindrome = true;
        while (current != fast && current != null && fast != null) {
            if (current.val != fast.val) {
                isPalindrome = false;
                break;
            }
            current = current.next;
            fast = fast.next;
        }

        // before returning, must re-reverse second half
        reverseList(secondHalf);

        return isPalindrome;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        System.out.println("expect true. Is palindrome: " + PalindromeLinkedList.isPalindrome(head));

        // 2 -> 4
        head.next = new ListNode(4);
        System.out.println("expect false. Is palindrome: " + PalindromeLinkedList.isPalindrome(head));

        // 2 -> 4 -> 6 -> 4 -> 2
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        System.out.println("expect true. Is palindrome: " + PalindromeLinkedList.isPalindrome(head));

        head.next.next.next.next.next = new ListNode(2);
        System.out.println("expect false. Is palindrome: " + PalindromeLinkedList.isPalindrome(head));

        // 1 -> 1 -> 2 -> 1
        head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        System.out.println("expect false. Is palindrome: " + PalindromeLinkedList.isPalindrome(head));
    }
}
