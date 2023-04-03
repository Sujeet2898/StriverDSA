/*
Question: Combination Sum II
--------------------------------------------
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in
candidates where the candidate numbers sum to target.
Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

Input: arr = [10,1,2,7,6,1,5], target = 8
Output:
[[1,1,6],[1,2,5],[1,7],[2,6]]

Input: arr = [2,5,2,1,2], target = 5
Output:
[[1,2,2],[5]]
 */

import java.util.ArrayList;
import java.util.Arrays;

public class _4_CombinationSumII {

/*
Time Complexity:O(2^n*k)
Reason: Assume if all the elements in the array are unique then the no. of subsequence you will get will be O(2^n).
we also add the ds to our ans when we reach the base case that will take “k”//average space for the ds.

Space Complexity:O(k*x)
Reason: if we have x combinations then space will be x*k where k is the average length of the combination.
 */

    public ArrayList<ArrayList<Integer>> combinationSum2(int[] arr, int target) {

        // sorting our array will allow us to skip repetitions easily
        Arrays.sort(arr);

        ArrayList<ArrayList<Integer>> ansList = new ArrayList<>();

        findSubsets(arr, target,0, new ArrayList<Integer>(), ansList);
        return ansList;
    }

    public void findSubsets(int[] arr, int target, int idx, ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> ansList){

        // Be careful to always add a copy of the list, else you would essentially be changing the same list over and over again
        if(target == 0){
            ansList.add(new ArrayList<Integer>(curr));
            return;
        }

        if(target < 0 || idx >= arr.length) {
            return;
        }


        for(int i = idx; i < arr.length ; i++){

            // make a choice (add the number at index)
            curr.add(arr[i]);  

            // backtrack (generate dependent subsets)
            findSubsets(arr,target - arr[i],i+1, curr, ansList);

            // undo your choice (remove the number)
            curr.remove(curr.size()-1);

            // This is the tricky part; we want to skip all the repetitions of the number
            while(i+1 < arr.length && arr[i] == arr[i+1]){
                i++;
            }
        }
    }
}
