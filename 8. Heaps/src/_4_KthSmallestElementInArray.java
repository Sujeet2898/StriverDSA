/*
Question:  Kth Smallest Element in an Array
Given an integer array nums and an integer k, return the kth smallest element in the array.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.
You must solve it in O(n) time complexity.

Input: nums = [3,2,1,5,6,4], k = 2
Output: 2

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 3

 */

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class _4_KthSmallestElementInArray {

    // Sorting the Array
    // Time complexity: O(nlogn)
    // Space complexity: O(1)

    public int findKthSmallest1(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;

        return nums[k - 1];
    }

    // Time complexity should be O(n * logk)
    // space complexity should not be more than O(k).

    public int findKthSmallest2(int[] nums, int k) {  

        // Max Priority Queue
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < nums.length; i++){

            // Adding first K elements into funnel (PriorityQueue)
            if (i < k){
                pq.add(nums[i]);

            }else {

                // If maxElement of funnel (PriorityQueue) is greater than the next element of K
                // then, remove the maxElement & add next element of K into funnel (PriorityQueue)
                if (nums[i] < pq.peek()){
                    pq.remove();
                    pq.add(nums[i]);
                }
            }
        }
        return pq.peek();
    }
}
