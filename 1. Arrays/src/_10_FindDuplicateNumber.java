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
--------------------------------------------------

   public int findDuplicate(int[] nums) {
        //slow ptr moves 1 step while fast ptr moves 2 steps at a time, if they meet then there is a cycle the starting point of that cycle is the no. which is repeating
        int slow = nums[0], fast = nums[0];
        
        //DETECTING CYCLE -> use floyd's cycle detection
        while(true){
            slow = nums[slow]; //moves by 1 step
            fast = nums[nums[fast]]; //moves by 2 steps
            
            if(slow == fast){//cycle detected
                break;
            }
        }
        
        //DETECTING STARTING POINT OF THE CYCLE
        //reset slow or fast anyone and then move both by 1 step, wherever they meet is the starting point of the cycle
        slow = nums[0];
        
        while(slow!=fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow; // return fast; //both are pointing at starting pt of cycle
    }
}
