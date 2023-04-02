/*
Question: Combination Sum
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of
candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times.
Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]

Example 3:

Input: candidates = [2], target = 1
Output: []

 */

import java.util.ArrayList;  

public class _3_CombinationSum {

/*
Time Complexity: O(2^t * k) where t is the target, k is the average length
Reason: Assume if you were not allowed to pick a single element multiple times, every element will have a couple of options:
pick or not pick which is 2^n different recursion calls, also assuming that the average length of every combination generated is k.
(to put length k data structure into another data structure)
Why not (2^n) but (2^t) (where n is the size of an array)?
Assume that there is 1 and the target you want to reach is 10 so 10 times you can “pick or not pick” an element.

Space Complexity: O(k*x), k is the average length and x is the no. of combinations
 */

    public ArrayList<ArrayList<Integer>> combinationSum(int[] arr, int target) {

        ArrayList<ArrayList<Integer>> ansList = new ArrayList<>();

        getTargetCombination(arr, 0, target, new ArrayList<Integer>(), ansList);

        return ansList;
    }
    public void getTargetCombination(int[] arr, int index, int target, ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> ansList) {

        /**
         * Base case
         * 1. If target is reaching to Zero
         * 2. Current Position is equal to the length of the Array
         */

        if (target == 0) {
            ansList.add(new ArrayList<>(curr));
            return;
        }
        if (index == arr.length) {
            return;
        }

        /**
         * There are two cases
         * 1. Pick the current value if the current value (i.e arr[index]) is less than or equal to the target
         *    value then use the same attribute by passing the same index
         *
         *  2. Not picking up the current element by not reducing the target value and increasing the index
         */

        // picked
        if (arr[index] <= target) {

            curr.add(arr[index]);
            getTargetCombination(arr, index, target - arr[index], curr, ansList);

            // removing the last element because post adding of the value the call came back
            curr.remove(curr.size() - 1);
        }

        // not picked
        getTargetCombination(arr, index + 1, target, curr, ansList);
    }
}
