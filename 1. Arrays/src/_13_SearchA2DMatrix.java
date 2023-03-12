
public class _13_SearchA2DMatrix {

/*
Question: Search a 2D Matrix
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
 */

    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
public boolean searchMatrix1(int[][] matrix, int target) {
    if(matrix.length == 0){
        return false;
    }
    // traverse through the matrix
    for (int i = 0; i < matrix.length; i++){
        for (int j = 0; j < matrix[0].length; j++){
            if(matrix[i][j] == target){
                return true;
            }
        }
    }
    return false;
}

    // Time Complexity: O(log(n*m))
    // Space Complexity: O(1)
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix.length == 0){
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;

        int low = 0; // first index of matrix
        int high = (n * m) - 1; // last index of matrix

        // Binary search on first index to last index of matrix
        while(low <= high){
            int mid = (low + high)/2;
            if(matrix[mid / m][mid % m] == target){  // row = mid / m and column = mid % m
                return true;
            }
            if(matrix[mid / m][mid % m] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return false;
    }

/*
Question: Search in a row wise and column wise sorted matrix
Given an n x n matrix and an integer x, find the position of x in the matrix if it is present. Otherwise, print “Element not found”.
Every row and column of the matrix is sorted in increasing order. The designed algorithm should have linear time complexity.

Input: mat[4][4] = { {10, 20, 30, 40},{15, 25, 35, 45},{27, 29, 37, 48},{32, 33, 39, 50}}; and x = 29
Output: Found at (2, 1)
Explanation: Element at (2,1) is 29

Input : mat[4][4] = { {10, 20, 30, 40},{15, 25, 35, 45},{27, 29, 37, 48},{32, 33, 39, 50}}; and x = 100
Output : Element not found
Explanation: Element 100 does not exist in the matrix
 */

    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public boolean searchMatri1(int[][] matrix, int target) {
        if(matrix.length == 0){
            return false;
        }
        // traverse through the matrix
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == target){
                    return true;
                }
            }
        }
        return false;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public boolean searchMatri2(int[][] matrix, int target) {

        // set indexes for top right
        int i = 0, j = matrix.length - 1;

        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == target){
                return true;
            }
            // target is in left
            if (matrix[i][j] > target) {
                j--;
            }
            // target is in down
            // if mat[i][j] < target
            else {
                i++;
            }
        }
        return false;
    }
}

