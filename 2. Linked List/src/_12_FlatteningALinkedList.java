/*
Question: Flattening a Linked List
Given a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
(i) a next pointer to the next node,
(ii) a bottom pointer to a linked list where this node is head.
Each of the sub-linked-list is in sorted order.
Flatten the Link List such that all the nodes appear in a single level while maintaining the sorted order.
Note: The flattened list will be printed using the bottom pointer instead of next pointer. For more clearity have a
look at the printList() function in the driver code.

Example 1:

Input:
5 -> 10 -> 19 -> 28
|     |     |     |
7     20    22   35
|           |     |
8          50    40
|                 |
30               45
Output:  5-> 7-> 8- > 10 -> 19-> 20->
22-> 28-> 30-> 35-> 40-> 45-> 50.
Explanation:
The resultant linked lists has every node in a single level.
(Note: | represents the bottom pointer.)

Example 2:

Input:
5 -> 10 -> 19 -> 28
|          |
7          22
|          |
8          50
|
30
Output: 5->7->8->10->19->22->28->30->50
Explanation:
The resultant linked lists has every node in a single level.
(Note: | represents the bottom pointer.)
 */

public class _12_FlatteningALinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode bottom;

        ListNode(int d) {
            val = d;
            next = null;
            bottom = null;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode ans = new ListNode(0);
        ListNode temp = ans;

        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                temp.bottom = list1;
                temp = temp.bottom;
                list1 = list1.bottom;
            }else{
                temp.bottom = list2;
                temp = temp.bottom;
                list2 = list2.bottom;
            }
        }
        while(list1 != null){
            temp.bottom = list1;
            temp = temp.bottom;
            list1 = list1.bottom;
        }
        while(list2 != null){
            temp.bottom = list2;
            temp = temp.bottom;
            list2 = list2.bottom;
        }
        return ans.bottom;
    }
    
    public static ListNode flattenLinkedList(ListNode root) {
        // Base Case
        if(root == null || root.next == null){
            return root;
        }

        // recursion for list on right
        root.next = flattenLinkedList(root.next);

        // merge
        root = mergeTwoLists(root, root.next);

        return root;
    }
}
