/*
Question: Copy List with Random Pointer
A linked list of length n is given such that each node contains an additional random pointer,
which could point to any node in the list, or null.
Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has
its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should
point to new nodes in the copied list such that the pointers in the original list and copied list represent the same
list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding
two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes.
Each node is represented as a pair of [val, random_index] where:
val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.

Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
 */

import java.util.HashMap;

public class _2_CopyListWithRandomPointer {

    class Node {  
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 1. Using HashMap
    // Time Complexity: O(N)  --> O(N) for the first traversal during creating duplicate head and putting into map
    //                      again O(N) for the traversing while pointing next and random pointer
    // Space Complexity: O(N)  --> Since we are using hashMap

    public Node copyRandomList1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        return copyRandomList(head, map);
    }

    public Node copyRandomList(Node head, HashMap<Node, Node> map){
        if(head == null) {
            return null;
        }

        // creating duplicate head
        Node newHead = new Node(head.val);

        // putting original head and its duplicate head into map
        map.put(head, newHead);

        // pointing next pointer of duplicate head, same as original head points to next
        newHead.next = copyRandomList(head.next, map);

        // pointing random pointer of duplicate head by getting the exact random of original head from map
        newHead.random = map.get(head.random);

        // return duplicate head
        return newHead;
    }

    // 2. Optimized approach
    // Time Complexity: O(N)  --> O(N) for creating all the nodes
    //                        and O(N) for assigning random pointers for the copy nodes.
    //                  and again O(N) for restoring the original list, and extracting the copy list.
    // Space Complexity: O(1)  --> Since we are not using any extra space apart from the one we are creating the deep copy linkedList and that's allowed


    public Node copyRandomList2(Node head) {
        Node iter = head;
        Node front = head;

        // First round: make copy of each node, and link them together side-by-side in a single list.
        while (iter != null) {
            front = iter.next;

            Node copy = new Node(iter.val);
            iter.next = copy;
            copy.next = front;

            iter = front;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        Node pseudoHead = new Node(0);
        Node copy = pseudoHead;

        while (iter != null) {
            front = iter.next.next;

            // extract the copy
            copy.next = iter.next;
            copy = copy.next;

            // restore the original list
            iter.next = front;

            iter = front;
        }

        return pseudoHead.next;
    }
}
