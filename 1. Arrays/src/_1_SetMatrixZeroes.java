/*
Question: Set Matrix Zeroes
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
You must do it in place.
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 */

public class _1_SetMatrixZeroes {

/*

Approach 1:

Brute-Force Approach
The basic idea is to maintain another boolean matrix ‘isZero’ which stores whether our resultant matrix should contain
a zero at a particular index or not. We can traverse every element of our original matrix. If the element is 0, then we
traverse the complete row and complete column of that particular element and set isZero values accordingly to true for
all the elements in that row and column.

Time Complexity
O(N * M * (M + N)) where N & M are dimensions of the given matrix.
We are traversing each element. For each element that is zero, we are traversing its complete row and column. So we have
O(M * N) elements and for each element, we can traverse a row and a column in the worst case, i.e. O(M + N). So total
complexity is O(M * N * (M + N).

Space Complexity
O(N * M) where N & M are dimensions of the given matrix.
We need to declare a 2D boolean matrix of size n x m. So the total required space will be O(N * M).
 */

/*
    Time Complexity: O(N * M * (N + M))
    Space Complexity:O(N * M)

    Where N & M are dimensions of the given matrix.
*/

    public static void setZero(int matrix[][]) {

        // Storing dimensions of matrix in n and m.
        int n = matrix.length;
        int m = matrix[0].length;

        // Declaring isZero boolean matrix.
        Boolean isZero[][] = new Boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                isZero[i][j] = false;
            }
        }

        // Traversing the original matrix.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // If that element of the matrix is equal to 0.
                if (matrix[i][j] == 0) {

                    // Traversing its complete column and setting all the isZero values to be true.
                    for (int k = 0; k < n; k++) {
                        isZero[k][j] = true;
                    }

                    // Traversing its complete row and setting all the isZero values to be true.
                    for (int k = 0; k < m; k++) {
                        isZero[i][k] = true;
                    }
                }
            }
        }

        // Travrsing isZero and if isZero at an index is true then we replace that
        // element with zero in original matrix.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isZero[i][j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
/*
Approach 2:
Super Space saver
The basic idea is to store whether a complete row and complete column should be zero or not but instead of declaring
extra memory for this purpose, we are using the first row and first column of the original matrix to store this
information. We are using two independent variables to store these values for the first row and first column. So we
only need constant space for those two variables. Like the last approach we first find out whether a row should or
column should be filled with all zeros or not. And after that, the eligible rows and columns are filled with zeros.
Time Complexity
O(N * M) where N & M are dimensions of the given matrix.
We are iterating over our matrix two times. So total complexity will be O(M * N) + O(M * N) = O(M * N)

Space Complexity
O(1)
We have to store two boolean variables. So we need constant storage space. Hence space required will be O(1).
 */

    public static void setZeros(int matrix[][]) {

        // Storing dimensions of matrix in n and m.
        int n = matrix.length;
        int m = matrix[0].length;

        // Declaring two boolean variables firstColZero and firstRowZero.
        Boolean firstColZero = false, firstRowZero = false;

        // Setting firstColZero to true if any element in first column is zero.
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Setting firstRowZero to true if any element in first row is zero.
        for (int j = 0; j < m; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // 0 presents in matrix except first row and first column
        // Setting values for each row and column to be zero or not.
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        // 0 presents in matrix either in first row or first column
        // If any element of first row is zero then that complete column should be zero and vice-versa.
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Setting first row to zero if firstRowZero is true.
        if (firstColZero) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }

        // Setting first column to zero if firstColZero is true.
        if (firstRowZero) {
            for (int j = 0; j < m; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    public static void main(String[] args) {

    }
}
