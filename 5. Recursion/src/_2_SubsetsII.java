/*
Question: Subsets II
Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

Input: nums = [0]
Output: [[],[0]]
 */

import java.util.ArrayList;
import java.util.Arrays;

public class _2_SubsetsII {

    // Time Complexity: O(2^n * k)  --> O(2^n) for generating every subset and
    // O(k) to insert every subset in another data structure if the average length of every subset is k. Overall O(k * 2^n).
    // Space Complexity: O(2^n * k)  --> O(2^n * k) to store every subset of average length k.
    // Auxiliary space is O(n)  if n is the depth of the recursion tree.

    public static ArrayList<ArrayList<Integer>> uniqueSubsets(int n, int arr[]) {
        // sorting our array will allow us to skip repetitions easily
        Arrays.sort(arr);

        ArrayList<ArrayList<Integer>> ansList = new ArrayList<>();

        findSubsets(arr, 0, new ArrayList<>(), ansList);

        return ansList;
    }

    private static void findSubsets(int[] arr, int idx, ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> ansList) {
        
        // Be careful to always add a copy of the list, else you would essentially be changing the same list over and over again
        ansList.add(new ArrayList(curr));

        if(idx == arr.length) {
            return;
        }

        for(int i = idx; i < arr.length; i++) {

            // make a choice (add the number at index)
            curr.add(arr[i]);

            // backtrack (generate dependent subsets)
            findSubsets(arr, i + 1, curr, ansList);

            // undo your choice (remove the number)
            curr.remove(curr.size() - 1);

            // This is the tricky part; we want to skip all the repetitions of the number
            while(i+1 < arr.length && arr[i] == arr[i+1]) {
                i++;
            }
        }
    }
}
