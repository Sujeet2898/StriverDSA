/*
Question: Subarray with given XOR
Given an array of integers arr and an integer k.
Find the total number of subarrays having bitwise XOR of all elements equals to k.

Input 1:
 arr = [4, 2, 2, 6, 4]
 k = 6
Input 2:
 arr = [5, 6, 7, 8, 9]
 k = 5

Output 1:
 4
Output 2:
 2

Explanation 1:
 The subarrays having XOR of their elements as 6 are:
 [4, 2], [4, 2, 2, 6, 4], [2, 2, 6], [6]

Explanation 2:
 The subarrays having XOR of their elements as 5 are [5] and [5, 6, 7, 8, 9]
 */

import java.util.HashMap;

public class _23_SubarrayWithGivenXOR {

    // Using HashMap
    // Time Complexity: O(N^2)
    // Space Complexity: O(1)

    public int solve1(int[] arr, int k) {
        int count = 0;

        // Traverse through each subarray
        for (int i = 0; i < arr.length; i++) {
            int xor = 0;
            for (int j = i; j < arr.length; j++) {
                xor ^= arr[j];

                // Check if currentXor is equal to x
                if (xor == k) {
                    count++;
                }
            }
        }

        // Return the variable ans
        return count;
    }

    // Using HashMap
    // Time Complexity: O(N)
    // Space Complexity: O(N)

    public int solve2(int[] arr, int k) {

        // Hashmap for storing key as xor and value as count of that xor
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0; // for getting total no of subArray
        int xor = 0;

        for(int i = 0; i < arr.length; i++){
            xor ^= arr[i];

            if (map.containsKey(xor ^ k)){
                count += map.get(xor ^ k);
            }

            if(xor == k){
                count++;
            }

            if(map.containsKey(xor)){
                map.put(xor, map.get(xor) + 1);
            }else{
                map.put(xor, 1);
            }
        }
        return count;
    }
}
