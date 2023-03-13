/*
Question: Majority Element II
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Input: nums = [3,2,3]
Output: [3]
Example 2:

Input: nums = [1]
Output: [1]
Example 3:

Input: nums = [1,2]
Output: [1,2]
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _16_MajorityElementII {

    // Brute Force Approach
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public List<Integer> majorityElement0(int[] nums) {
        List< Integer > ans = new ArrayList< >();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int cnt = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] == nums[i])
                    cnt++;
            }

            if (cnt > (n / 3))
                ans.add(nums[i]);
        }
        return ans;
    }

    // Using HashMap
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public List<Integer> majorityElement1(int[] nums) {
        List < Integer > ans = new ArrayList < > ();
        int n = nums.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        // Iterating each element in the array to count frequencies
        for (int i = 0; i < n; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);

            // If frequency of the element is greater than 'n' / 3, then return the element.
            if (hashMap.get(nums[i]) > n / 3) {
                ans.add(nums[i]);
            }
        }
        return ans;
    }

    // Extended Boyer Moore’s Voting Algorithm
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public List<Integer> majorityElement(int[] nums) {
        int number1 = -1, number2 = -1, count1 = 0, count2 = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1){
                count1++;
            }
            else if(nums[i] == number2){
                count2++;
            }
            else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        ArrayList < Integer > ans = new ArrayList < Integer > ();
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1){
                count1++;
            }
            else if (nums[i] == number2){
                count2++;
            }
        }
        if (count1 > len / 3){
            ans.add(number1);
        }
        if (count2 > len / 3){
            ans.add(number2);
        }
        return ans;
    }
}
