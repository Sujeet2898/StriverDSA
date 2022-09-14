/*
Question: Reverse Pairs
Given an integer array nums, return the number of reverse pairs in the array.
A reverse pair is a pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].

Input: nums = [1,3,2,3,1]
Output: 2
Explanation: The reverse pairs are:
(1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
(3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1

Input: nums = [2,4,3,5,1]
Output: 3
Explanation: The reverse pairs are:
(1, 4) --> nums[1] = 4, nums[4] = 1, 4 > 2 * 1
(2, 4) --> nums[2] = 3, nums[4] = 1, 3 > 2 * 1
(3, 4) --> nums[3] = 3, nums[4] = 1, 5 > 2 * 1
 */

public class _18_ReversePairs {

/*  Time Complexity: O(N ^ 2)
    Space Complexity: O(1)
    Where N is the size of the array.
 */
    static int reversePairs1(int arr[]) {
        int Pairs = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > 2 * arr[j]) Pairs++;
            }
        }
        return Pairs;
    }

---------------------------------------------------------
Question : MergeSort
----------------------
    class Solution {
        public int[] sortArray(int[] nums) {
            mergesort(nums, 0, nums.length-1);
            return nums;
        }

        public void mergesort(int[] nums, int start, int end){
            if(start == end) {
                return;
            }
                int mid = (start + end) / 2;
                mergesort(nums, start, mid);
                mergesort(nums, mid + 1, end);
                merge(nums, start, mid, end);
        }

        public void merge(int[] nums, int start, int mid, int end){
            int i = start;
            int j = mid + 1;
            int k = 0;
            int[] temp = new int[end - start + 1];
            while( i <= mid && j <= end){
                if (nums[i] < nums[j]){
                    temp[k] = nums[i];
                    k++;
                    i++;
                }
                else{
                    temp[k] = nums[j];
                    k++;
                    j++;
                }
            }

            //copy remaining elements
            while (i <= mid) {
                temp[k] = nums[i];
                k++;
                i++;
            }
            //copy remaining elements
            while (j <= end) {
                temp[k] = nums[j];
                k++;
                j++;
            }

            // putting element in sorted array in original array
            for (k = 0; k < temp.length; k++){
                nums[start + k] = temp[k];
            }
        }
    }
 -----------------------------------------------------------------

// TC: O(nlogn){mergeSort} + O(n){merge} + O(n){counting}
// SC: O(n){temporary array}

    static int result = 0;
    public int reversePairs2(int[] nums) {
        mergesort(nums, 0 , nums.length-1);
        return result;
    }

    public static void mergesort(int[] nums, int start, int end){
        if(start == end){
            return;
        }
        int mid = (start + end) / 2;
        mergesort(nums, start, mid);
        mergesort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }

    public static void merge(int[] nums, int start, int mid, int end){

        int i = start;
        int j = mid + 1;
        int k = 0;
        int[] temp = new int[end - start + 1];

        while(i <= mid && j <= end){
            while(i <= mid && (long)nums[i] <= 2*(long)nums[j]){
                i++;
            }
            result += mid - i + 1;
            j++;
        }

        i = start;
        j = mid + 1;

        while( i <= mid && j <= end){
            if (nums[i] < nums[j]){
                temp[k] = nums[i];
                k++;
                i++;
            }
            else{
                temp[k] = nums[j];
                k++;
                j++;
            }
        }

        //copy remaining elements
        while (i <= mid) {
            temp[k] = nums[i];
            k++;
            i++;
        }
        //copy remaining elements
        while (j <= end) {
            temp[k] = nums[j];
            k++;
            j++;
        }

        // putting element in sorted array in original array
        for(k = 0; k < temp.length; k++){
            nums[start + k] = temp[k];
        }
    }
}
