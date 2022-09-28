/*
Question: K maximum sum combinations from two arrays
Given two equally sized arrays (A, B) and N (size of both arrays).
A sum combination is made by adding one element from array A and another element of array B. Display the maximum K valid sum combinations from all the possible sum combinations.

Examples:

Input :  A[] : {3, 2}
         B[] : {1, 4}
         K : 2 [Number of maximum sum combinations to be printed]
Output : 7    // (A : 3) + (B : 4)
         6    // (A : 2) + (B : 4)

Input :  A[] : {4, 2, 5, 1}
         B[] : {8, 0, 3, 5}
         K : 3
Output : 13   // (A : 5) + (B : 8)
         12   // (A : 4) + (B :  8)
         10   // (A : 2) + (B : 8)
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class _6_kMaximumSumCombinationsFromTwoArrays {

    // Time complexity: O(N^2)
    // Space complexity: O(N^2)

    public static void KMaxCombinations(int[] arr1, int[] arr2, int N, int K){
        // max heap.
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());

        // Insert all the possible combinations in max heap.
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                pq.add(arr1[i] + arr2[j]);

        // Pop first N elements from max heap and display them.
        int count = 0;

        while (count < K) {
            System.out.println(pq.peek());
            pq.remove();
            count++;
        }
    }
}
