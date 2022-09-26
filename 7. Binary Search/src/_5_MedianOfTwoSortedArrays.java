/*
Question: Median of Two Sorted Arrays
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 */

public class _5_MedianOfTwoSortedArrays {

    // Naive Approach
    // Time Complexity: O(M+N) --> We traverse through both the arrays linearly.
    // Space Complexity: O(M+N) --> We store the final array whose size is m+n.

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1.length < 1 && nums2.length < 1) {
            return 0;
        }
        if (nums1.length < 1) {
            return findMedian(nums2);
        }
        if (nums2.length < 1) {
            return findMedian(nums1);
        }

        int[] merged = new int[nums1.length + nums2.length];
        merged = merge(nums1, nums2, merged);

        return findMedian(merged);
    }

    private static double findMedian(int[] arr) {

        // even length
        if (arr.length % 2 == 0) {
            int mid = (arr.length) / 2;
            return ((double) arr[mid] + (double) arr[mid-1]) / 2;  // arr = {1,2,3,4} --> midIndex = 4/2 = 2 --> median = (arr[2] + arr[1])/2 = 2.5
        }
        // odd length
        return (double) arr[(arr.length-1) / 2];  // arr = {1,2,3} --> midIndex = 2/2 = 1 --> median = arr[1] = 2
    }

    private static int[] merge(int[] nums1, int[] nums2, int[] merged) {
        int index1 = 0;
        int index2 = 0;
        int indexM = 0;

        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] <= nums2[index2]) {
                merged[indexM] = nums1[index1];
                index1++;
            } else {
                merged[indexM] = nums2[index2];
                index2++;
            }

            indexM++;
        }
        while (index1 < nums1.length) {
            merged[indexM] = nums1[index1];
            index1++;
            indexM++;
        }
        while (index2 < nums2.length) {
            merged[indexM] = nums2[index2];
            index2++;
            indexM++;
        }

        return merged;
    }

    // Binary Search Approach
    // Time Complexity: O(log(min(n1,n2))) --> We are applying binary search on the array which has a minimum size.
    // Space Complexity: O(1) --> No extra array is used.

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2){
        // ensuring nums2 is longer than num1
        if(nums2.length < nums1.length){
            return findMedianSortedArrays2(nums2, nums1);
        }

        int n1 = nums1.length;
        int n2 = nums2.length;
        int low = 0;
        int high = n1;

        while (low <= high){
            // partition of nums1
            int cut1 = (low + high)/2;
            // partition of nums2.
            int cut2 = (n1 + n2 + 1)/2 - cut1;   // +1 because it plays well with both odd and even number of elements in the combined array

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];

            int right1 = (cut1 == n1) ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = (cut2 == n2) ? Integer.MAX_VALUE : nums2[cut2];

            // median found
            if(left1 <= right2 && left2 <= right1){
                // even
                if ((n1 + n2) % 2 == 0){
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                }
                // odd
                else {
                    return Math.max(left1, left2);
                }
            }
            
            // too right on nums1, move left
            else if (left1 > right2){
                high = cut1 - 1;
            }
            // too left on nums1, move right
            else {
                low = cut1 + 1;
            }
        }

        return 0.0;
    }
}
