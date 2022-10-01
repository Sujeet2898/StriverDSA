/*
Question: Next Greater Element
-------------------------------
Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
return the next greater number for every element in nums.
The next greater number of a number x is the first greater number to its traversing-order next in the array,
which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number.
The second 1's next greater number needs to search circularly, which is also 2.

Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]
 */

import java.util.Scanner;
import java.util.Stack;

public class _8_NextGreaterElement {

    // Brute force approach
    // Time Complexity: O(N^2) --> In the worst case, we will be searching for the next greater element of each element. For searching the next greater element in arr it can take O(N) time, thus total time would be O(N^2).
    // Space Complexity: O(1) --> only a constant extra space is required.

    public static int[] nextGreaterElements1(int[] nums) {
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            res[i] = -1;
            for(int j = i + 1; j % nums.length < nums.length && j < 2 * nums.length; j++){
                if(nums[j % nums.length] > nums[i]){
                    res[i] = nums[j % nums.length];
                    break;
                }
            }
        }
        return res;
    }
/* Input:  1 2 3 4 3
   Output: 2 3 4 -1 4
   */

    // Stack approach
    // Time Complexity: O(N) --> In the worst case, we will be pushing and popping all the elements into the stack only once. Thus complexity would be O(N).
    // Space Complexity: O(N) --> In the worst case, all the elements can be in the stack.

    public static int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int nge[] = new int[n];

        // Stack contains the index of the element to be processed
        Stack<Integer> stack = new Stack<>();

        // Process array in circular fashion. Each element will be processed twice
        for (int i = 2 * n - 1; i >= 0; i--) {

            // 1. Remove top element of the stack if it is smaller or equal to current element of array
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }

            // 2. Peek the top element of the stack if it is greater than current element of array
            if (i < n) {
                if (!stack.isEmpty()) {
                    nge[i] = stack.peek();
                } else {
                    nge[i] = -1;
                }
            }

            // Always add the element into the stack
            stack.push(nums[i % n]);
        }

        return nge;
    }
/* Input:  1 2 3 4 3
   Output: 2 3 4 -1 4
   ----------------------------------------------------------------------
   If given array is not circular
   ----------------------------------------------------------------------
   */

    // Brute force approach
    // Time Complexity: O(N^2) --> In the worst case, we will be searching for the next greater element of each element. For searching the next greater element in arr it can take O(N) time, thus total time would be O(N^2).
    // Space Complexity: O(1) --> only a constant extra space is required.

    public static int[] nextGreaterElement1(int[] nums) {
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            res[i] = -1;
            for(int j = i + 1; j < nums.length; j++){
                if(nums[j ] > nums[i]){
                    res[i] = nums[j];
                    break;
                }
            }
        }
        return res;
    }
/* Input:  1 2 3 4 3
   Output: 2 3 4 -1 -1
   ----------------------------------------------------------------------
   If given array is not circular
   ----------------------------------------------------------------------
   */

    // Stack approach
    // Time Complexity: O(N) --> In the worst case, we will be pushing and popping all the elements into the stack only once. Thus complexity would be O(N).
    // Space Complexity: O(N) --> In the worst case, all the elements can be in the stack.

    public static int[] nextGreaterElement2(int[] nums) {
        int n = nums.length;
        int nge[] = new int[n];

        // Stack contains the index of the element to be processed
        Stack<Integer> stack = new Stack<>();

        // Process array in circular fashion. Each element will be processed twice
        for (int i = n - 1; i >= 0; i--) {

            // 1. Remove top element of the stack if it is smaller or equal to current element of array
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }

            // 2. Peek the top element of the stack if it is greater than current element of array
            if (i < n) {
                if (!stack.isEmpty()) {
                    nge[i] = stack.peek();
                } else {
                    nge[i] = -1;
                }
            }

            // Always add the element into the stack
            stack.push(nums[i]);
        }

        return nge;
    }
/* Input:  1 2 3 4 3
   Output: 2 3 4 -1 -1
   */

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int input[] = new int[n];
        for(int i = 0; i < n; i++) {
            input[i] = s.nextInt();
        }
        int output[] = nextGreaterElement2(input);

        for(int i = 0; i < output.length; i++) {
            System.out.print(output[i] + " ");
        }
    }
}
