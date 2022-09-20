/*
Question: Rotate List
Given the head of a linked list, rotate the list to the right by k places.

Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

Input: head = [0,1,2], k = 4
Output: [2,0,1]
 */

public class _1_RotateList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 1. Brute Force Approach
    // Time Complexity: O(N * k)  --> O(Number of nodes present in the list*k)
    // For k times, we are iterating through the entire list to get the last element and move it to first.
    // Space Complexity: O(1)  --> No extra data structure is used for computation.


    public ListNode rotateRight1(ListNode head, int k) {

        // edge cases
        if(head == null || head.next == null || k == 0){
            return head;
        }

        // For k times, we are iterating through the entire list to get the last element and move it to first.
        for (int i = 0; i < k; i++) {
            ListNode temp = head;

            // finding second last node
            while (temp.next.next != null) {
                temp = temp.next;
            }
            // finding last node
            ListNode end = temp.next;

            // breaking connection between second last node and last node
            temp.next = null;

            // connect last node to head
            end.next = head;
            head = end;
        }
        return head;
    }

    // 2. Optimized Approach
    // Time Complexity: O(N)  --> O(length of list) + O(length of list – (length of list % k))
    // O(length of the list) for calculating the length of the list. O(length of the list – (length of list%k)) for breaking link.
    // Space Complexity: O(1)  --> No extra data structure is used for computation.

    public ListNode rotateRight2(ListNode head, int k) {

        // edge cases
        if(head == null || head.next == null || k == 0){
            return head;
        }

        // compute the length of given linked list
        ListNode temp = head;
        int length = 1;
        while(temp.next != null){
            length++;
            temp = temp.next;
        }

        // go till length-k node
        temp.next = head;  // connect last node to head
        k = k % length;  // find modulus
        k = length - k;

        while(k != 0){   // k > 0
            temp = temp.next;
            k--;
        }

        // After reaching length-k node, simply make the next node as head and then break the previous connection
        head = temp.next;
        temp.next = null;

        return head;
    }
}
