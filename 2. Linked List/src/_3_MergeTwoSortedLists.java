/*
Question: Merge Two Sorted Lists
You are given the heads of two sorted linked lists list1 and list2.
Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.

Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

Input: list1 = [], list2 = []
Output: []

Input: list1 = [], list2 = [0]
Output: [0]
 */

public class _3_MergeTwoSortedLists {

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    // Time Complexity: O(N + M) Where N and M are the number of nodes in both lists respectively.
    // We will be reversing both linked lists once which will take linear time on both.

    // Space Complexity: O(N + M)
    // Considering the recursive stack space.

    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if(list1 == null){
            return list2;
        }

        if(list2 == null){
            return list1;
        }

        if((list1.val) < (list2.val)){
            list1.next = mergeTwoLists1(list1.next, list2);
            return list1;
        }
        else{
            list2.next = mergeTwoLists1(list1, list2.next);
            return list2;
        }
    }

    // Time Complexity: O(N + M) Where N and M are the number of nodes in both lists respectively.
    // We will be reversing both linked lists once which will take linear time on both.

    // Space Complexity: O(1)  i.e. constant space complexity.
    // We are storing the result of the addition in one of the given linked lists. Hence, we are not using any extra space.

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {

        if (list1 == null){
            return list2;
        }

        if (list2 == null){
            return list1;
        }

        ListNode t1 = list1; // head1,
        ListNode t2 = list2; // head2,
        ListNode head3 = null;
        ListNode tail3 = null;

        if (t1.val <= t2.val){
            head3 = t1;
            tail3 = t1;
            t1 = t1.next;
        } else {
            head3 = t2;
            tail3 = t2;
            t2 = t2.next;
        }

        while (t1 != null && t2 != null){
            if (t1.val <= t2.val){
                tail3.next = t1;
                tail3 = t1;     // or tail3 = tail3.next
                t1 = t1.next;
            } else {
                tail3.next = t2;
                tail3 = t2;   // or tail3 = tail3.next
                t2 = t2.next;
            }
        }

        if (t1 != null){      // rest is also attached
            tail3.next = t1;
        } else {
            tail3.next = t2;
        }

        return head3;
    }
}
