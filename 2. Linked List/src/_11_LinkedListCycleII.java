/*
Question: Linked List Cycle II
Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer
is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
 */

import java.util.HashSet;

public class _11_LinkedListCycleII {

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

    public ListNode detectCycle1(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();

        while(head != null){
            if(set.contains(head)){
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

    // 2. Slow and Faster Pointer Approach
    // Time Complexity: O(N)  --> all the nodes of the list are visited. Fast pointer might take some turn. But is near to O(N)
    // Space Complexity: O(1)  --> No extra space is used apart from slow, fast and entry pointer.

    public ListNode detectCycle2(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode entry = head;

        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow){
                while(slow != entry) {
                    slow = slow.next;
                    entry = entry.next;
                }
                return slow;
            }
        }
        return null;
    }
}
