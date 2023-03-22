/*
Question: Reverse Nodes in k-Group
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a  
multiple of k then left-out nodes, in the end, should remain as it is.
You may not alter the values in the list's nodes, only nodes themselves may be changed.

Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]

Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
 */

public class _9_ReverseNodesInKGroup {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // Time Complexity: O(N)  --> Nested iteration with O((N/k)*k) which makes it equal to O(N).
    //  N/k for no. of groups and for every group, we are doing k iterations (reversing k-1 links)
    // Space Complexity: O(1)  --> No extra data structures are used for computation.

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // pre, cur, nex are at dummy node
        ListNode cur = dummy, nex = dummy, pre = dummy;

        // finding length of linkedList
        int length = 0;
        while(cur.next != null){
            cur = cur.next;
            length++;
        }

        // 1->2->3->4->5->6->7->8 and k = 3 so, group size = 8/3 = 2
        // iterate till groups of size is greater than or equal to k
        while(length >= k){

            // initial positions
            cur = pre.next;  // 1st node
            nex = cur.next;  // 2nd node

            // loop of k-1 times with each having 4 steps
            for(int i = 1; i < k; i++){
                cur.next = nex.next;  // 1->3  breaking the link between cur(1) and nex(2)
                nex.next = pre.next;  // 2->1
                pre.next = nex;     // pre->2  breaking the link between pre and cur(1)
                nex = cur.next;     // moving nex to 3
            }

            // pre stands at last node of previous reversed group of size k
            pre = cur;
            // update the length if length is greater than k
            length -= k;
        }
        return dummy.next; // return head
    }
}
