/*
Question: N-Queens
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

Input: n = 1
Output: [["Q"]]
 */

import java.util.ArrayList;

public class _2_NQueens {

    // Time Complexity: Exponential in nature since we are trying out all ways, to be precise its O(N! * N).
    // Space Complexity: O(N^2)

    public static ArrayList<ArrayList<String>> solveNQueens1(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        solve(0, board, result);
        return result;
    }

    private static boolean isSafe(char[][] board, int row, int col) {
        int duprow = row;
        int dupcol = col;
        while (row >= 0 && col >= 0) {
            if (board[row][col] == 'Q'){
                return false;
            }
            row--;
            col--;
        }

        row = duprow;
        col = dupcol;
        while (col >= 0) {
            if (board[row][col] == 'Q') {
                return false;
            }
            col--;
        }

        row = duprow;
        col = dupcol;
        while (col >= 0 && row < board.length) {
            if (board[row][col] == 'Q'){
                return false;
            }
            col--;
            row++;
        }

        return true;
    }

    private static void solve(int col, char[][] board, ArrayList<ArrayList<String>> result) {
        if (col == board.length) {
            result.add(construct1(board));
            return;
        }

        for (int row = 0; row < board.length; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                solve(col + 1, board, result);
                board[row][col] = '.';
            }
        }
    }

    private static ArrayList<String> construct1(char[][] board) {
        ArrayList<String> ans = new ArrayList<String>();
        for (int i = 0; i < board.length; i++) {
            String str = new String(board[i]);
            ans.add(str);
        }
        return ans;
    }

    // Time Complexity: Exponential in nature since we are trying out all ways, to be precise its O(N! * N).
    // Space Complexity: O(N)

    public static ArrayList<ArrayList<String>> solveNQueens2(int n){
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        int[] leftRow = new int[n];
        int[] upperDiagonal = new int[2 * n - 1];
        int[] lowerDiagonal = new int[2 * n - 1];

        solve(0, board, result, leftRow, lowerDiagonal, upperDiagonal);

        return result;
    }

    private static void solve(int col, char[][] board, ArrayList<ArrayList<String>> res, int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal) {
        if (col == board.length) {
            res.add(construct2(board));
            return;
        }

        for (int row = 0; row < board.length; row++) {
            if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0 && upperDiagonal[board.length - 1 + col - row] == 0) {

                board[row][col] = 'Q';
                leftRow[row] = 1;
                lowerDiagonal[row + col] = 1;
                upperDiagonal[board.length - 1 + col - row] = 1;

                solve(col + 1, board, res, leftRow, lowerDiagonal, upperDiagonal);

                board[row][col] = '.';
                leftRow[row] = 0;
                lowerDiagonal[row + col] = 0;
                upperDiagonal[board.length - 1 + col - row] = 0;
            }
        }
    }

    private static ArrayList<String> construct2(char[][] board) {
        ArrayList<String> ans = new ArrayList<String>();
        for (int i = 0; i < board.length; i++) {
            String str = new String(board[i]);
            ans.add(str);
        }
        return ans;
    }
}
