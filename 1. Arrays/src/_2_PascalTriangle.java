/*
Question: Pascal's Triangle
Given an integer numRows, return the first numRows of Pascal's triangle.
In Pascal's triangle, each number is the sum of the two numbers directly above it.
Input: n = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
Input: n = 1
Output: [[1]]
 */

/*
    Time Complexity: O(N^2)
    Space complexity: O(1)

    Where N denotes the number of Rows.
*/

import java.util.ArrayList;

public class _2_PascalTriangle {
    public static ArrayList<ArrayList<Long>> printPascal(int n) {
        ArrayList<ArrayList<Long>> triangle = new ArrayList<ArrayList<Long>>();

        for (int i = 1; i <= n; i++) {

            long icj = 1;
            ArrayList<Long> temp = new ArrayList<Long>();
            for (int j = 1; j <= i; j++) {
                temp.add(icj);
                long icjp1 = icj * (long)(i - j) / (long)j;
                icj = icjp1;
            }
            triangle.add(temp);
        }
        return triangle;
    }

    public static void main(String[] args) {
        
    }
}

