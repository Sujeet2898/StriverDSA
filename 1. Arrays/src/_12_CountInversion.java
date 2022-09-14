/*
Question : Count Inversion
Given an integer array nums, return the number of count of inversion in the array.
A inversion is a pair (i, j) where 0 <= i < j < nums.length and nums[i] > nums[j].
 */

public class _12_CountInversion {

/*  Time Complexity: O(N ^ 2)
    Space Complexity: O(1)
    Where N is the size of the array.
 */

    public static long getInversions1(long[] arr, int n) {
        // Variable to store the total inversions in the array.
        long totalInversions = 0;

        // Check for each element whether any smaller element is present on right side.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    totalInversions ++;
                }

            }
        }
        return totalInversions;
    }

    /**
     * *************************************************
     Question : MergeSort
     ----------------------
     class Solution {
     public int[] sortArray(int[] nums) {
     mergesort(nums, 0, nums.length-1);
     return nums;
     }

     public void mergesort(int[] nums, int start, int end){
     if(start < end){
     int mid = (start + end) / 2;
     mergesort(nums, start, mid);
     mergesort(nums, mid + 1, end);
     merge(nums, start, mid, end);
     }
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
     k = 0;
     for (k = 0; k < temp.length; k++){
     nums[start + k] = temp[k];
     }
     }
     }
     ******************************************************************
     */

// TC: O(nlogn){mergeSort} + O(n){merge} + O(n){counting}
// SC: O(n){temporary array}

    static int result = 0;
    public static void mergesort(long[] nums, int start, int end){
        if(start == end){
            return;
        }
        int mid = (start + end) / 2;
        mergesort(nums, start, mid);
        mergesort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }

    public static void merge(long[] nums, int start, int mid, int end){

        int i = start;
        int j = mid + 1;
        int k = 0;
        long[] temp = new long[end - start + 1];

        while(i <= mid && j <= end){
            while(i <= mid && nums[i] <= nums[j]){
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
        k = 0;
        for(k = 0; k < temp.length; k++){
            nums[start + k] = temp[k];
        }
    }

    public static long getInversions2(long arr[], int n) {
        mergesort(arr, 0, n - 1);
        return result;
    }
}
