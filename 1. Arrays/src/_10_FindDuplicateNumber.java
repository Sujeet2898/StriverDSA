/*
Question: Find the Duplicate Number
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and uses only constant extra space.

Input: nums = [1,3,4,2,2]
Output: 2

Input: nums = [3,1,3,4,2]
Output: 3
 */

import java.util.ArrayList;
import java.util.HashMap;

public class _10_FindDuplicateNumber {

    // Time Complexity: O(n) and Space Complexity: O(n)
    public static int findDuplicate(ArrayList<Integer> arr, int n){
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        // Iterate in array
        for (int val : arr) {
            if (map.containsKey(val)) {
                int oldFrequency = map.get(val);
                int newFrequency = oldFrequency + 1;
                map.put(val, newFrequency);
                ans = val;
            } else {
                map.put(val, 1);
            }
        }
        return ans;
    }
}

// Time Complexity: O(n) and Space Complexity: O(1)
Floyd’s Tortoise and Hare Approach
--------------------------------------------------
For this approach, we divide the whole process into two phases and use two pointers named tortoise and hare..
Phase 1(Find the intersection point): The hare would be twice as fast as the tortoise.
Hence, HARE = ARR[ARR[HARE]] and TORTOISE = ARR[TORTOISE]. Since the tortoise is slow, the hare would catch it
at some point, this point will be our intersection point.
Phase 2:
Now, we reduce the speed of the hare and make it equal to the speed of the tortoise,
meaning now HARE = ARR[HARE] and TORTOISE = ARR[TORTOISE]. The tortoise starts from the start of the array, while the
hare starts from the intersection point found in the first phase. Now, the point where the hare and the tortoise
intersect will be the starting point of the loop which is the repeated element and hence returned.

/*

	Time complexity: O(N)
	Space complexity: O(1)

	Where N is the length of the array.

*/

import java.util.ArrayList;

public class Solution{

    public static int findDuplicate(ArrayList<Integer> arr, int n){

        // Initialise tortoise and hare pointers.
        int tortoise = arr.get(0);
        int hare = arr.get(0);

        do{

            // Hare pointer moves twice as fast as tortoise.
            tortoise = arr.get(tortoise);
            hare = arr.get(arr.get(hare));
        }

        // Find the intersection point of the two runners.
        while (tortoise != hare);

        // To find the entrance to the cycle tortoise begins from start of array while hare begins from intersection point.
        tortoise = arr.get(0);

        while (tortoise != hare) {

            // This time both runners move with same speed.
            tortoise = arr.get(tortoise);
            hare = arr.get(hare);
        }

        // Return the entrance to the cycle, which will be the repeated element.
        return hare;

    }
}
