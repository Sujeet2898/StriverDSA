/*
Question: Max Consecutive Ones
Given a binary array nums, return the maximum number of consecutive 1's in the array.

Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.

Input: nums = [1,0,1,1,0,1]
Output: 2
 */

public class _6_MaxConsecutiveOnes {

    // Time Complexity: O(N)
    // Space Complexity: O(1)

    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int maximum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                count = 0;
            }

            maximum = Math.max(maximum, count);
        }
        return maximum;
    }
}
