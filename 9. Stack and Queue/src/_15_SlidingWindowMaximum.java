/*
Question: Sliding Window Maximum
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
You can only see the k numbers in the window. Each time the sliding window moves right by one position.
Return the max sliding window.

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Input: nums = [1], k = 1
Output: [1]
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class _15_SlidingWindowMaximum {

    // Brute force approach
    // Time Complexity: O(N^2) --> One loop for traversing and another to findMax
    // Space Complexity: O(k) --> No.of windows

    public int[] maxSlidingWindow1(int[] nums, int k) {
        // assume nums is not null
        int n = nums.length;
        if (n == 0 || k == 0) {
            return new int[0];
        }

        int numOfWindow = n - k + 1;
        int[] result = new int[numOfWindow]; // number of windows

        for (int i = 0; i < numOfWindow; i++) {
            int maxVal = nums[i];
            for (int j = i + 1; j <= i + k - 1; j++) {
                if (nums[j] > maxVal) { // update
                    maxVal = nums[j];
                }
            }
            result[i] = maxVal;
        }

        return result;
    }

    // DeQueue approach
    // Time Complexity: O(N) --> since each element is processed (add/remove) at most twice.
    // Space Complexity: O(k) --> No.of windows

    public int[] maxSlidingWindow2(int[] nums, int k) {
        //using deque
        if(nums.length == 0) {
            return nums;
        }
        Deque<Integer> dq = new ArrayDeque<>();
        int result[] = new int[nums.length - k + 1];
        int j = 0;

        for (int i = 0; i < nums.length; i++){

            //check for out of bounds
            if(!dq.isEmpty() && dq.peekFirst() == i - k ){
                dq.pollFirst();
            }

            //condition so that all the small elements are removed and the head has the largest element
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]){
                dq.pollLast();
            }

            //index is safe to be added now
            dq.addLast(i);

            //pushing to the array only when i > group k-1 i.e store the index only once for every sliding window
            if (i >= k-1 ){
                result[j] = nums[dq.peekFirst()];
                j++;
            }
        }
        return result;
    }
}
