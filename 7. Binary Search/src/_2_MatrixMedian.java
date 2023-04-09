/*
Question: Matrix Median
Given a matrix of integers A of size N x M in which each row is sorted.
Find and return the overall median of the matrix A.
Note: No extra memory is allowed.
Note: Rows are numbered from top to bottom and columns are numbered from left to right.

Input 1:
    A = [   [1, 3, 5],
            [2, 6, 9],
            [3, 6, 9]   ]
Output 1:
    5
Explanation 1:
    A = [1, 2, 3, 3, 5, 6, 6, 9, 9]
    Median is 5. So, we return 5.

Input 2:
    A = [   [5, 17, 100]    ]
Output 2:
    17
 */

import java.util.ArrayList;
import java.util.Collections;

public class _2_MatrixMedian {  

    // Naive Approach
    // Time Complexity: O((N * M) * log(N * M)), where ‘N’ is the number of rows and ‘M’ is the number of columns in the given matrix.
    // Reason: Since we are sorting a list/array of integers whose length is N * M, so the overall complexity will be O((N * M) * log(N * M)).
    // Space Complexity: O(N * M), where ‘N’ is the number of rows, and ‘M’ is the number of columns in the given matrix.
    // Reason: The only extra space which we are using is to store the elements of the matrix which are N * M in total. Therefore, the overall space complexity will be O(N * M).

    public static int findMedian1(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // ArrayList to store the elements of given matrix.
        ArrayList<Integer> data = new ArrayList<Integer>();

        // Iterate through matrix and push elements in the arraylist.
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                data.add(matrix[i][j]);
            }
        }

        // Sort the arraylist of elements in non-decreasing order.
        Collections.sort(data);

        // Return the median which is (N*M)/2 th element in the arraylist.
        return data.get((n * m) / 2);
    }

    // Binary Search
    // Time Complexity: O(N*logM) --> For every rows, we are implementing binary search on its column length
    // Space Complexity: O(1) --> not using any extra space

    public static int findMedian2(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int low =0;
        int high = Integer.MAX_VALUE;

        while(low <= high){
            int mid = (low + high)/2;
            int count =0;

            for(int i = 0; i < n; i++) {
                count += countSmallerThanEqualToMid(matrix[i], mid);
            }
            
            if(count <= (n * m)/2){
                low = mid + 1;
            }else high = mid - 1;
        }
        return low;
    }

    private static int countSmallerThanEqualToMid(int matrix[], int x){  // x -> Mid
        int low = 0;
        int high = matrix.length-1;

        while(low <= high){
            int mid = (low + high)/2;

            if(matrix[mid] <= x){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return low;
    }
}
