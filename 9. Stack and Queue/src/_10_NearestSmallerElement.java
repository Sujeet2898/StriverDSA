/*
Question: Nearest Smaller Element
----------------------------------
Given an array, find the nearest smaller element G[i] for every element A[i] in the array such that the element has an index smaller than i.
Input 1:
    A = [4, 5, 2, 10, 8]
Output 1:
    G = [-1, 4, -1, 2, 2]
Explaination 1:
    index 1: No element less than 4 in left of 4, G[1] = -1
    index 2: A[1] is only element less than A[2], G[2] = A[1]
    index 3: No element less than 2 in left of 2, G[3] = -1
    index 4: A[3] is nearest element which is less than A[4], G[4] = A[3]
    index 4: A[3] is nearest element which is less than A[5], G[5] = A[3]

Input 2:
    A = [3, 2, 1]
Output 2:
    [-1, -1, -1]
Explaination 2:
    index 1: No element less than 3 in left of 3, G[1] = -1
    index 2: No element less than 2 in left of 2, G[2] = -1
    index 3: No element less than 1 in left of 1, G[3] = -1
 */

import java.util.Scanner;
import java.util.Stack;

public class _10_NearestSmallerElement {

    // Brute force approach
    // Time Complexity: O(N^2) --> In the worst case, we will be searching for the nearest Smaller element of each element. For searching the nearest Smaller element in arr it can take O(N) time, thus total time would be O(N^2).
    // Space Complexity: O(1) --> only a constant extra space is required.

    public static int[] nearestSmallerElement1(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = -1;
        for(int i = 1; i < nums.length; i++){
            res[i] = -1;
            int j;
            for(j = i - 1; j >= 0; j--){
                if(nums[j ] < nums[i]){
                    res[i] = nums[j];
                    break;
                }
            }
        }
        return res;
    }

    // Stack approach
    // Time Complexity: O(N) --> In the worst case, we will be pushing and popping all the elements into the stack only once. Thus complexity would be O(N).
    // Space Complexity: O(N) --> In the worst case, all the elements can be in the stack.

    public static int[] nearestSmallerElement2(int[] nums) {
        int n = nums.length;
        int nse[] = new int[n];

        // Stack contains the index of the element to be processed
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < n; i++) {

            // 1. Remove top element of the stack if it is greater or equal to current element of array
            while(!stack.isEmpty() && stack.peek() >= nums[i]){
                stack.pop();
            }

            // 2. Peek the top element of the stack if it is smaller than current element of array
            if(i < n){
                if (!stack.isEmpty()) {
                    nse[i] = stack.peek();
                } else {
                    nse[i] = -1;
                }
            }

            // Always add the element into the stack
            stack.push(nums[i]);
        }

        return nse;
    }


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int input[] = new int[n];
        for(int i = 0; i < n; i++) {
            input[i] = s.nextInt();
        }
        int output[] = nearestSmallerElement1(input);

        for(int i = 0; i < output.length; i++) {
            System.out.print(output[i] + " ");
        }
    }
}
