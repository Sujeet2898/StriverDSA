/*
Question: Intersection of Two Linked Lists
Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
If the two linked lists have no intersection at all, return null.
For example, the following two linked lists begin to intersect at node c1:
A:         a1-->a2-->
                     c1-->c2-->c3
B:    b1-->b2-->b3-->

The test cases are generated such that there are no cycles anywhere in the entire linked structure.
Note that the linked lists must retain their original structure after the function returns.
Custom Judge:
The inputs to the judge are given as follows (your program is not given these inputs):
* intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
* listA - The first linked list.
* listB - The second linked list.
* skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
* skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program.
If you correctly return the intersected node, then your solution will be accepted.
-----------------------------------------------------------------------------------------------
Example 1:
A:        4-->1-->
                  8-->4-->5
B:    5-->6-->1-->

Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'

Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the
intersected node in A; There are 3 nodes before the intersected node in B.
- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B)
are different node references. In other words, they point to two different locations in memory, while the nodes with
value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.

Example 2:
A:    1-->9-->1-->
                  2-->4
B:            3-->

Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'

Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the
intersected node in A; There are 1 node before the intersected node in B.

Example 1:
A:    2-->6-->4

B:        1-->5

Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection

Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not
intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.
 */

import java.util.HashSet;

public class _7_IntersectionOfTwoLinkedLists {

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    }

    // 1. Brute Force
    // Time Complexity: O(N * M)  --> Since, for each element of the second list, we are traversing for the first list
    // Space Complexity: O(1) --> As we are using constant extra memory

    public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        while(headB != null){
            ListNode temp = headA;

            while(temp != null){

                // if both nodes are same
                if(temp == headB){
                    return headB;
                }
                temp = temp.next;
            }
            headB = headB.next;
        }
        //intersection is not present between the lists return null
        return null;
    }

    // 2. Hashing Approach
    // Time Complexity: O(N + M)  --> Since we are traversing on the first and second linked list separately.
    // Space Complexity: O(N) or O(M) --> Since we are storing nodes in the hash set

    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();

        while(headA != null){
            set.add(headA);
            headA = headA.next;
        }
        while(headB != null){
            if(set.contains(headB)){
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    // 3. Parallel Traversal Approach: Difference of length
    // Time Complexity: O(N)  --> O(N) for finding length + O( abs(length of list1 - length of list2)) + O( min(length of list1, length of list2))
    // Space Complexity: O(1) --> As we are using constant extra memory

    private static int length(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }

    public static ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        // get the length of both list
        int headALength = length(headA);
        int headBLength = length(headB);

        // move headA and headB to the same start point
        while (headALength > headBLength) {
            headA = headA.next;
            headALength--;
        }

        while (headALength < headBLength) {
            headB = headB.next;
            headBLength--;
        }

        // find the intersection until end
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    // 4. Best Optimised Approach:
    // Time Complexity: O(N)  --> 2 * max(m,n)
    // Space Complexity: O(1) --> As we are using constant extra memory

    public ListNode getIntersectionNode4(ListNode headA, ListNode headB) {
        ListNode d1 = headA, d2 = headB;  // d1 and d2 are dummy nodes
        while(d1 != d2){
            if(d1 == null){
                d1 = headB;
            }
            else{
                d1 = d1.next;
            }

            if(d2 == null){
                d2 = headA;
            }
            else{
                d2 = d2.next;
            }
        }
        return d1;
    }
}
