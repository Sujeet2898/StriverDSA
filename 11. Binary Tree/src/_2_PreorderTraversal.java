/*
Question: Preorder Traversal
Given the root of a binary tree, return the preorder traversal of its nodes' values.

Input: root = [1,null,2,3]
Output: [1,2,3]

Input: root = []
Output: []

Input: root = [1]
Output: [1]
 */

import java.util.ArrayList;
import java.util.Stack;

public class _2_PreorderTraversal {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int data) {
            this.val = data;
            left = null;
            right = null;
        }
    }

    // 1. Using Recursion
    // Time Complexity: O(N) -->  We are traversing N nodes and every node is visited exactly once.
    // Space Complexity: O(N) --> Space is needed for the recursion stack.

    public ArrayList<Integer> preorderTraversal1(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    private void preOrder(TreeNode node, ArrayList<Integer> list){
        if(node != null){
            list.add(node.val);
            preOrder(node.left, list);
            preOrder(node.right, list);
        }
    }

    // 2. Iterative Approach
    // Time Complexity: O(N) -->  We are traversing N nodes and every node is visited exactly once.
    // Space Complexity: O(N) -->  In the worst case, (a tree with every node having a single right child and left-subtree

    public ArrayList<Integer> preorderTraversal2(TreeNode root) {
        // Create an array list to store the solution result
        ArrayList<Integer> res = new ArrayList<>();

        // Return the result if the tree is empty
        if (root == null) {
            return res;
        }

        // Create an empty stack and push the root node
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // Loop till stack is empty
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);

            // Push the right child of the popped node into the stack
            if (curr.right != null)
                stack.push(curr.right);

            // Push the left child of the popped node into the stack
            if (curr.left != null)
                stack.push(curr.left);
        }

        return res;
    }

    // 3. Morris Preorder Traversal of a Binary tree
    // Time Complexity: O(N) -->  We are traversing N nodes and every node is visited exactly once.
    // Space Complexity: O(1) --> We are not using recursion.

    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();

        TreeNode curr = root;

        while(curr != null){
            // If we don't have left child, simply add root and traverse on right
            if(curr.left == null){
                list.add(curr.val);
                curr = curr.right;
            }

            // If we do have left child, so we need to first make a path then only we can come back to root after finishing this subtree's inorder traversal
            else {
                TreeNode predecessor = curr.left; //mark the left child

                //go to right till we get extreme right node
                while(predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }

                // if pre.right is null then link it to current to reach back
                if(predecessor.right == null){
                    predecessor.right = curr; // firstly make the thread
                    list.add(curr.val);  // only one change from inOrder to preOrder
                    curr = curr.left;
                }
                //pre.right is current meaning the left tree has been traversed so remove the link and traverse the right subtree now
                else {
                    predecessor.right = null; // firstly cut the thread
                    curr = curr.right;
                }
            }
        }
        return list;
    }
}
