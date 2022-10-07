/*
Question: Right/Left view of binary tree
Given a Binary Tree, print Left view of it. Left view of a Binary Tree is set of nodes visible when tree is visited from Left side.
The task is to complete the function leftView(), which accepts root of the tree as argument.

Left view of following tree is 1 2 4 8.
Right view of following tree is 1 3 7 8.
          1
        /   \
      2      3
    /  \    /  \
  4     5  6    7
   \
     8

 */

import java.util.ArrayList;
import java.util.List;

public class _4_RightLeftViewOfBinaryTree {

    static class TreeNode {
        int val;
        TreeNode right, left;
        TreeNode(int data) {
            this.val = data;
            right = null;
            left = null;
        }
    }

    // 1. Right view of binary tree
    // Time Complexity: O(N) -->  We are traversing N nodes and every node is visited exactly once.
    // Space Complexity: O(H) --> Space is equal to the Height of the Tree.

    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    public void rightView(TreeNode curr, List<Integer> result, int level){
        if(curr == null){
            return;
        }
        if(level == result.size()){
            result.add(curr.val);
        }

        rightView(curr.right, result, level + 1);
        rightView(curr.left, result, level + 1);
    }

    // 2. Left view of binary tree
    // Time Complexity: O(N) -->  We are traversing N nodes and every node is visited exactly once.
    // Space Complexity: O(H) --> Space is equal to the Height of the Tree.

    public List<Integer> liftSideView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        leftView(root, result, 0);
        return result;
    }

    public void leftView(TreeNode curr, List<Integer> result, int level){
        if(curr == null){
            return;
        }
        if(level == result.size()){
            result.add(curr.val);
        }

        leftView(curr.left, result, level + 1);  // reverse of rightView
        leftView(curr.right, result, level + 1); // reverse of rightView
    }
}
