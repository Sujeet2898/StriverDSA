/*
Question: Remove Nth Node From End of List
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Input: head = [1,2,3,4,5], n = 2  
Output: [1,2,3,5]

Input: head = [1], n = 1
Output: []

Input: head = [1,2], n = 1
Output: [1]
 */

public class _4_RemoveNthNodeFromEndofList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Time Complexity: O(2N) --> O(N) for finding length of linkedlist and again O(N) for traversing L-n
    // Space Complexity: O(1)

    public ListNode removeNthFromEnd1(ListNode head, int n) {

        // It stores the Length of Linked List.
        int L = 0;

        // Creating temporory node and finding length of linkedlist
        ListNode temp = head;
        while (temp != null) {
            L++;
            temp = temp.next;
        }

        // If head of Linked List is the Kth node from end.
        // i.e deleting first node
        if (n == L) {
            temp = head;
            head = head.next;
            return head;
        } else {

            // deleting other node
            int countNode = 0;
            temp = head;
            while (temp != null) {
                countNode++;

                // Remove the Kth node from end of Linked List.
                if (L - n == countNode) {
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
            }
            return head;
        }
    }

    // Time Complexity: O(N) --> fast pointer will move at maximum of n
    // Space Complexity: O(1)

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode start = new ListNode();
        start.next = head;
        ListNode fast = start;
        ListNode slow = start;

        for(int i = 1; i <= n; i++){
            fast = fast.next;
        }

        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next= slow.next.next;

        return start.next;
    }
}
