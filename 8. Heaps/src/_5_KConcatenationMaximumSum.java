/*
Question: K-Concatenation Maximum Sum
Given an integer array arr and an integer k, modify the array by repeating it k times.
For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.

Input: arr = [1,2], k = 3
Output: 9

Input: arr = [1,-2,1], k = 5
Output: 2

Input: arr = [-1,-2], k = 7
Output: 0
 */

public class _5_KConcatenationMaximumSum {

    // Time complexity: O(N)
    // Space complexity: O(1)

    public int kConcatenationMaxSum(int[] arr, int k) {
        int sum = 0;
        return kadanesKConcat(arr, k, sum);
    }

    public static int kadanes(int[] arr) {
        int currentSum = arr[0];
        int overAllSum = arr[0];

        for (int i = 1; i < arr.length; i++) {

            // Join the incoming train if it is +ve
            if (currentSum >= 0) {
                currentSum += arr[i];  
            } else {
                // Form a new train if the incoming train is -ve
                currentSum = arr[i];
            }

            // Updating the overall sum
            if (currentSum > overAllSum) {
                overAllSum = currentSum;
            }
        }
        return overAllSum;
    }

    public static int kadanesOfTwo(int[] arr) {
        int[] newArr = new int[arr.length * 2];
        // 1st Copy
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        // 2nd Copy
        for (int i = 0; i < arr.length; i++) {
            newArr[i + arr.length] = arr[i];
        }
        return kadanes(newArr);
    }

    public static int kadanesKConcat(int[] arr, int k, int sum) {
        if (k == 1) {
            return kadanes(arr);

        } else if (sum < 0) {
            return kadanesOfTwo(arr);

        } else {
            // sum >= 0
            return kadanesOfTwo(arr) + (k - 2) * sum;
        }
    }
}
