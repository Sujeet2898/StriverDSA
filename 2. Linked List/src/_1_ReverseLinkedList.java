/*
Question: Reverse Linked List
Given the head of a singly linked list, reverse the list, and return the reversed list.

Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Input: head = [1,2]
Output: [2,1]

Input: head = []
Output: []
 */

public class _1_ReverseLinkedList {

    public class ListNode<T> {
        T data;
        ListNode<T> next;

        ListNode(T data){
            this.data = data;
            next = null;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null, next = null;
        while (head != null){
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }  
        return prev;
    }
}
