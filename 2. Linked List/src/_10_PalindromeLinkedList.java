/*
Question: Palindrome Linked List
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

Input: head = [1,2,2,1]
Output: true

Input: head = [1,2]
Output: false
 */

import java.util.ArrayList;

public class _10_PalindromeLinkedList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // Time Complexity: O(N)  --> Iterating through the list to store elements in the array.
    // Space Complexity: O(N)  --> Using an array to store list elements for further computations.

    public boolean isPalindrome1(ListNode head) {
        ArrayList<Integer> arr = new ArrayList<>();
        ListNode temp = head;

        while(temp != null){
            arr.add(temp.val);
            temp = temp.next;  
        }

        int j = arr.size()-1;

        for(int i = 0; i < j; i++, j--){
            if(arr.get(i) != arr.get(j)){
                return false;
            }
        }

        return true;
    }

    // Time Complexity: O(N)  --> O(N/2)+O(N/2)+O(N/2)
    // O(N/2) for finding the middle element, reversing the list from the middle element, and traversing again to find palindrome respectively.
    // Space Complexity: O(1)  --> No extra data structures are used for computation.

    public boolean isPalindrome2(ListNode head) {

        // finding middle of the LinkedList
        ListNode slow = head, fast = head, mid = null;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // Odd --> fast is not null
        if (fast != null){
            mid = slow.next;
        } else {
            mid = slow;
        }

        // Reversing the second half of LinkedList
        ListNode prev = null, next = null;
        while (mid != null){
            next = mid.next;
            mid.next = prev;
            prev = mid;
            mid = next;
        }

        // Comparing two LinkedList
        while (prev != null){
            if (prev.val != head.val){
                return false;
            }
            prev = prev.next;
            head = head.next;
        }
        return true;
    }
}
