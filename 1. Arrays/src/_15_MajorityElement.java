import java.util.HashMap;

/*
Question: Majority Element
Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Input: nums = [3,2,3]
Output: 3

Input: nums = [2,2,1,1,1,2,2]
Output: 2
 */
public class _15_MajorityElement {

    // Brute Force Approach
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static int findMajority(int[] arr, int n) {
        // Iterating each element in the array/list and check if it is a majority element.
        for (int i = 0; i < n; i++) {
            // Variable to store the frequency of the current element at index 'i'.
            int maxCount = 0;

            // Iterating the array/list to count the frequency of the current element at index 'i'.
            for (int j = 0; j < n; j++) {
                // Increment the count of variable if an occurrence of the current element is observed.
                if (arr[j] == arr[i]) {
                    maxCount++;
                }
            }

            // If count of any element exceeds 'n' / 2, then it is the majority element.
            if (maxCount > n / 2) {
                return arr[i];
            }
        }

        // If no majority element found, return -1.
        return -1;
    }

    // Using HashMap
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int majority(int[] arr, int n) {
        // Declaring HashMap to store the frequency of elements.
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        // Iterating each element in the array to count frequencies.
        for (int i = 0; i < n; i++) {
            hashMap.put(arr[i], hashMap.getOrDefault(arr[i], 0) + 1);

            // If frequency of the element is greater than 'n' / 2, then return the element.
            if (hashMap.get(arr[i]) > n / 2) {
                return arr[i];
            }
        }

        // If no majority element found, return -1.
        return -1;
    }

    // Moore's Voting Algorithm
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int majorityElement(int[] nums) {

        int count = 0;
        int element = 0;

        for (int i = 0; i < nums.length; i++){
            if (count == 0){
                element = nums[i];
            }else if(element == nums[i]){
                count++;
            }else {
                count--;
            }
        }
        return element;
    }
}
