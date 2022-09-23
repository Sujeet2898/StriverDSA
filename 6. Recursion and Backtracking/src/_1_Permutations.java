/*
Question: Permutations
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Input: nums = [0,1]
Output: [[0,1],[1,0]]

Input: nums = [1]
Output: [[1]]
 */

import java.util.ArrayList;

public class _1_Permutations {

    // Using recursion
    // Time Complexity: O(N! x N)
    // Space Complexity:  O(N)

    public ArrayList<ArrayList<Integer>> permute1(int[] nums) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList < > ();
        recurPermute(0, nums, ans);
        return ans;
    }

    private void recurPermute(int index, int[] nums, ArrayList<ArrayList<Integer>> ansList) {
        if(index == nums.length){
            // copy the curr to ansList
            ArrayList<Integer> curr = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                curr.add(nums[i]);
            }
            ansList.add(new ArrayList<>(curr));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(i, index, nums);
            recurPermute(index + 1, nums, ansList);
            swap(i, index, nums);
        }
    }
    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Using Backtracking
    // Time Complexity: O(N! x N)
    // Space Complexity:  O(1)

    public ArrayList<ArrayList<Integer>> permute(int[] nums) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    public void backtrack(ArrayList<ArrayList<Integer>> ansList, ArrayList<Integer> curr, int [] nums){
        if (curr.size() == nums.length){
            ansList.add(new ArrayList(curr)); //if the curr ansList equals nums length then add to resultant ansList
        }else{
            for(int i = 0; i < nums.length; i++){
                if(curr.contains(nums[i])) {
                    continue;
                }
                curr.add(nums[i]);//Add the current traversing element from nums
                backtrack(ansList, curr, nums);//Backtrack to its previous node by calling backtrack function recursively
                curr.remove(curr.size()-1);//remove the last element of the curr to find another way from the prev node which we got ofter backtracking
            }
        }
    }
}

/*
Question: all Permutations of a String/Array
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Input: str = abc
Output: [[a, b, c], [a, c, b], [b, a, c], [b, c, a], [c, a, b], [c, b, a]]

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Input: nums = [0,1]
Output: [[0,1],[1,0]]

Input: nums = [1]
Output: [[1]]


import java.util.ArrayList;
import java.util.Scanner;

public class _1_Permutations {

    // all Permutations of a String

    // Time Complexity: O(n*n!) --> there are n! permutations and it requires O(n) time to print a permutation.
    // Space Complexity: O(|str|)

    public static ArrayList<ArrayList<String>> find_permutation(String str) {
        ArrayList<ArrayList<String>> ansList = new ArrayList<>();
        permutation(str, ansList, new ArrayList<>());
        return ansList;
    }

    private static void permutation(String str, ArrayList<ArrayList<String>> ansList, ArrayList<String> list){

        if(str.length() == 0){
            ansList.add(new ArrayList<>(list));
            return;
        }

        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);

            String leftStr = str.substring(0, i);
            String rightStr = str.substring(i + 1);
            String restOfStr = leftStr + rightStr;

            list.add(String.valueOf(ch));
            permutation(restOfStr, ansList, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        System.out.println(find_permutation(str));
    }
}
*/