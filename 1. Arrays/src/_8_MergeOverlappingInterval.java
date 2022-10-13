/*
Question: Merge Overlapping Interval
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array
of the non-overlapping intervals that cover all the intervals in the input.

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class _8_MergeOverlappingInterval {

    // Time Complexity: O(N * log(N))
    // Space Complexity: O(1)

    public int[][] merge(int[][] intervals) {

        //declaring an array result to store the pairs
        ArrayList<int[]> result = new ArrayList<>();

        //sorting the given interval array based on starting point
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        //defining start and end point
        int start = intervals[0][0];
        int end = intervals[0][1];

        //we will iterate through the 2d array intervals so in each iteration we will get a row[1D array] as i
        for (int[] arr : intervals) {
            //check if end point of 1st pair if greater than the starting point of the 2nd pair or not, basically we check it's in overlapping condition or not

            if (arr[0] <= end) {
                end = Math.max(end, arr[1]);
            }

            //otherwise add it in the result
            else {
                result.add(new int[]{start, end});
                start = arr[0];
                end = arr[1];
            }
        }

        result.add(new int[]{start, end});
        return result.toArray(new int[0][]);
    }

    -------------------------------------------------------------------

    public static class Pair implements Comparable<Pair>{
        int startTime;
        int endTime;

        Pair(int startTime, int endTime){
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Pair o) {
            if(this.startTime != o.startTime){
                return this.startTime - o.startTime;
            }else {
                return this.endTime - o.endTime;
            }
        }
    }

    public static void mergeOverlappingIntervals(int[][] arr) {
        // merge overlapping intervals and print in increasing order of start time

        Pair[] pairs = new Pair[arr.length];
        for (int i = 0; i < arr.length; i++){
            pairs[i] = new Pair(arr[i][0], arr[i][1]);
        }

        Arrays.sort(pairs);
        Stack<Pair> st = new Stack<>();
        for (int i = 0; i < pairs.length; i++){
            if (i == 0){
                st.push(pairs[i]);
            }else {
                Pair top = st.peek();
                if (pairs[i].startTime >= top.endTime){
                    st.push(pairs[i]);
                }else {
                    top.endTime = Math.max(top.endTime, pairs[i].endTime);
                }
            }
        }

        Stack<Pair> result = new Stack<>();
        while (st.size() > 0){
            result.push(st.pop());
        }

        while (result.size() > 0){
            Pair p = result.pop();
            System.out.println(p.startTime + " " + p.endTime);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            arr[j][0] = Integer.parseInt(line.split(" ")[0]);
            arr[j][1] = Integer.parseInt(line.split(" ")[1]);
        }

        mergeOverlappingIntervals(arr);
    }
}

/*
Input:
6
22 28
1 8
25 27
14 19
27 30
5 12

Output:
1 12
14 19
22 30

 */