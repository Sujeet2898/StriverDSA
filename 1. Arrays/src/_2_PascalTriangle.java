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
    public static ArrayList<ArrayList<Long>> printPascal1(int n) {
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

/*
Time Complexity: We are creating a 2D array of size (numRows * numCols) (where 1 <= numCols <= numRows),
and we are traversing through each of the cells to update it with its correct value, so Time Complexity = O(numRows2).
Space Complexity: Since we are creating a 2D array, space complexity = O(numRows2).
 */

    public static ArrayList<ArrayList<Integer>> printPascal2(int n){
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row, pre = null;
        for (int i = 1; i <= n; i++) {
            row = new ArrayList<Integer>();
            for (int j = 1; j <= i; ++j) {
                if (j == 1 || j == i) {
                    row.add(1);
                }
                else {
                    row.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = row;
            res.add(row);
        }
        return res;
    }
}

