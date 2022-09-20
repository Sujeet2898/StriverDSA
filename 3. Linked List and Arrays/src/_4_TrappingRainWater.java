/*
Question: Trapping Rain Water
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.


Input: height = [4,2,0,3,2,5]
Output: 9
 */

public class _4_TrappingRainWater {

    // 1. Brute Force Approach
    // Time Complexity: O(N^2)  --> O(N*N) as for each index we are calculating leftMax and rightMax so it is a nested loop.
    // Space Complexity: O(1)

    public int trap1(int[] height) {
        int n = height.length;
        int waterTrapped = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            int leftMax = 0, rightMax = 0;

            // 0 to j
            while (j >= 0) {
                leftMax = Math.max(leftMax, height[j]);
                j--;
            }

            j = i;
            // j to n-1
            while (j < n) {
                rightMax = Math.max(rightMax, height[j]);
                j++;
            }
            waterTrapped += Math.min(leftMax, rightMax) - height[i];
        }
        return waterTrapped;
    }

    // 2. Prefix and Suffix arrays Approach
    // Time Complexity: O(N)  --> O(N) for traversing through the array only once. And O(2*N) for computing prefix and suffix array.
    // Space Complexity: O(N)+O(N) for prefix and suffix arrays.

    public int trap2(int[] height) {
        int n = height.length;
        int prefix[] = new int[n];
        int suffix[] = new int[n];

        prefix[0] = height[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = Math.max(prefix[i - 1], height[i]);
        }

        suffix[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], height[i]);
        }

        int waterTrapped = 0;
        for (int i = 0; i < n; i++) {
            waterTrapped += Math.min(prefix[i], suffix[i]) - height[i];
        }
        return waterTrapped;
    }

    // 3. Two pointer approach
    // Time Complexity: O(N)  --> O(N) because we are using 2 pointer approach.
    // Space Complexity: O(1) because we are not using anything extra.

    public int trap3(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int waterTrapped = 0;
        int maxLeft = 0, maxRight = 0;
        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                } else {
                    waterTrapped += maxLeft - height[left];
                }
                left++;
            } else {
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    waterTrapped += maxRight - height[right];
                }
                right--;
            }
        }
        return waterTrapped;
    }
}
