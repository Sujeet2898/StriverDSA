/*
Question: Linked List Cycle
Given head, the head of a linked list, determine if the linked list has a cycle in it.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following
the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
Note that pos is not passed as a parameter.
Return true if there is a cycle in the linked list. Otherwise, return false.

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
 */

import java.util.HashSet;

public class _8_LinkedListCycle {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 1. Hashing Approach  
    // Time Complexity: O(N)  --> Entire list is iterated once.
    // Space Complexity: O(N)  --> All nodes present in the list are stored in a hash table.

    public boolean hasCycle1(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();

        while(head != null){
            if(set.contains(head)){
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    // 2. Slow and Faster Pointer Approach
    // Time Complexity: O(N)  --> all the nodes of the list are visited.
    // Space Complexity: O(1)  --> No extra data structure is used.

    public boolean hasCycle2(ListNode head) {

        if(head == null || head.next == null){
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;

        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow){
                return true;
            }
        }
        return false;
    }
}
