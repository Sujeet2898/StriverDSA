/*
Question: Next Permutation
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
More formally, if all the permutations of the array are sorted in one container according to their lexicographical order,
then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement
is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.
 */

/*
    Time Complexity : O(N)
    Space Complexity : O(1)

    Where 'N' is the length of the given permutation.
*/

import java.util.ArrayList;
import java.util.Collections;

public class _3_NextPermutation {
    public static ArrayList<Integer> nextPermutation(ArrayList<Integer> permutation){
        int swapPosition1 = -1;
        int swapPosition2 = -1;

        // Finding breakpoint (first breakpoint can be at second last index)
        for(int i = permutation.size() - 2; i >= 0; i--){
            if(permutation.get(i) < permutation.get(i + 1)){
                swapPosition1 = i;
                break;
            }
        }

        // If there is no breakpoint, simply reverse it
        if(swapPosition1 == -1){
            reverse(permutation, 0, permutation.size() - 1);
            return permutation;
        }

        // If there is breakpoint
        for(int i = permutation.size() - 1; i >= 0; i--){
            if(permutation.get(i) > permutation.get(swapPosition1)){
                swapPosition2 = i;
                break;
            }
        }

        swap(permutation, swapPosition1, swapPosition2);
        reverse(permutation, swapPosition1 + 1, permutation.size() - 1);
        return permutation;
    }

    public static void swap(ArrayList<Integer> nums, int i, int j){
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }

    public static void reverse(ArrayList<Integer> nums, int start, int end){
        while(start < end){
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
