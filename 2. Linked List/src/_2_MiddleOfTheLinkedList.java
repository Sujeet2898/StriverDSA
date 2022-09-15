/*
Question: Middle of the Linked List
Given the head of a singly linked list, return the middle node of the linked list.
If there are two middle nodes, return the second middle node.

Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.

Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 */

public class _2_MiddleOfTheLinkedList {

    public class ListNode<T> {
        T data;
        ListNode<T> next;

        ListNode(T data){
            this.data = data;
            next = null;
        }
    }

    // Using Naive Approach
    // Time Complexity: O(N) + O(N/2)   -->O(N) for finding no. of nodes and O(N/2) to reach the middle node
    // Space Complexity: O(1)
    public ListNode middleNode1(ListNode head) { // ListNode without parameter
        int n = 0;
        ListNode temp = head;

        while(temp != null){
            n++;
            temp = temp.next;
        }

        temp = head;
        for(int i = 0; i < n/2; i++){
            temp = temp.next;
        }
        return temp;
    }

    // Using Tortoise-Hare-Approach
    // Time Complexity: O(N/2)  --> fast tortoise will cover total distance in n/2 times
    // Space Complexity: O(1)
    public ListNode middleNode2(ListNode head) {  // ListNode without parameter
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
