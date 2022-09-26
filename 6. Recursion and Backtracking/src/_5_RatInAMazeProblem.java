/*
Question: Rat in a Maze Problem
Consider a rat placed at (0, 0) in a square matrix of order N * N. It has to reach the destination at (N - 1, N - 1). Find all possible paths that the rat can take to reach from source to destination. The directions in which the rat can move are 'U'(up), 'D'(down), 'L' (left), 'R' (right). Value 0 at a cell in the matrix represents that it is blocked and rat cannot move to it while value 1 at a cell in the matrix represents that rat can be travel through it.
Note: In a path, no cell can be visited more than one time. If the source cell is 0, the rat cannot move to any other cell.

Input:
N = 4
m[][] = {{1, 0, 0, 0},
         {1, 1, 0, 1},
         {1, 1, 0, 0},
         {0, 1, 1, 1}}
Output: DDRDRR DRDDRR
Explanation:
The rat can reach the destination at (3, 3) from (0, 0) by two paths - DRDDRR and DDRDRR, when printed in sorted order we get DDRDRR DRDDRR.

Input:
N = 2
m[][] = {{1, 0},
         {1, 0}}
Output: -1
Explanation:
No path exists and destination cell is blocked.
Your Task:
You don't need to read input or print anything. Complete the function printPath() which takes N and 2D array m[ ][ ] as
input parameters and returns the list of paths in lexicographically increasing order.
Note: In case of no path, return an empty list. The driver will output "-1" automatically.
 */

import java.util.ArrayList;

public class _5_RatInAMazeProblem {

    // Time Complexity: O(4^(n^2)), because on every cell we need to try 4 different directions.
    // Space Complexity: O(n^2) ,Maximum Depth of the recursion tree(auxiliary space).

    public static ArrayList<String> findPath1(int[][] matrix, int n) {  // n is the order of matrix
        int visited[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = 0;
            }
        }

        ArrayList<String> ans = new ArrayList<>();
        if (matrix[0][0] == 1) {
            solve(0, 0, matrix, n, ans, "", visited);
        }
        return ans;
    }

    private static void solve(int i, int j, int matrix[][], int n, ArrayList<String> ans, String move, int visited[][]) {

        // last cell
        if (i == n - 1 && j == n - 1) {
            ans.add(move);
            return;
        }

        // downward
        if (i + 1 < n && visited[i + 1][j] == 0 && matrix[i + 1][j] == 1) {
            visited[i][j] = 1;
            solve(i + 1, j, matrix, n, ans, move + 'D', visited);
            visited[i][j] = 0;
        }

        // left
        if (j - 1 >= 0 && visited[i][j - 1] == 0 && matrix[i][j - 1] == 1) {
            visited[i][j] = 1;
            solve(i, j - 1, matrix, n, ans, move + 'L', visited);
            visited[i][j] = 0;
        }

        // right
        if (j + 1 < n && visited[i][j + 1] == 0 && matrix[i][j + 1] == 1) {
            visited[i][j] = 1;
            solve(i, j + 1, matrix, n, ans, move + 'R', visited);
            visited[i][j] = 0;
        }

        // upward
        if (i - 1 >= 0 && visited[i - 1][j] == 0 && matrix[i - 1][j] == 1) {
            visited[i][j] = 1;
            solve(i - 1, j, matrix, n, ans, move + 'U', visited);
            visited[i][j] = 0;
        }
    }

    // Time Complexity: O(4^(n^2)), because on every cell we need to try 4 different directions.
    // Space Complexity: O(n^2) ,Maximum Depth of the recursion tree(auxiliary space).
    
    public static ArrayList<String> findPath2(int[][] matrix, int n) {
        int visited[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = 0;
            }
        }

        //          D   L  R   U
        int di[] = {+1, 0, 0, -1};
        int dj[] = {0, -1, 1, 0};

        ArrayList<String> ans = new ArrayList<>();
        if (matrix[0][0] == 1) {
            solve(0, 0, matrix, n, ans, "", visited, di, dj);
        }
        return ans;
    }

    private static void solve(int i, int j, int matrix[][], int n, ArrayList<String> ans, String move, int visited[][], int di[], int dj[]) {
        if (i == n - 1 && j == n - 1) {
            ans.add(move);
            return;
        }

        String dir = "DLRU";
        for (int index = 0; index < 4; index++) {
            int nexti = i + di[index];
            int nextj = j + dj[index];

            if (nexti >= 0 && nextj >= 0 && nexti < n && nextj < n && visited[nexti][nextj] == 0 && matrix[nexti][nextj] == 1) {

                visited[i][j] = 1;
                solve(nexti, nextj, matrix, n, ans, move + dir.charAt(index), visited, di, dj);
                visited[i][j] = 0;

            }
        }
    }
}
