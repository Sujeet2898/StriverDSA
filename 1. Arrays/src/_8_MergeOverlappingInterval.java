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

import java.util.* ;
import java.io.*;
/*******************************************************

 Following is the Interval class structure

 class Interval {
 int start, int finish;

 Interval(int start, int finish) {
 this.start = start;
 this.finish = finish;
 }
 }

 *******************************************************/

import java.util.List;
import java.util.ArrayList;

/*
    Time Complexity: O(N * log(N))
    Space Complexity: O(1)

    Where N is the number of intervals.
*/

public class Solution {

    public static List<Interval> mergeIntervals(Interval[] intervals) {
        int n = intervals.length;
        List<Interval> res = new ArrayList();

        Arrays.sort(intervals, new Comparator<Interval>() {

            public int compare(Interval a, Interval b) {
                if(a.start == b.start) {
                    return a.finish - b.finish;
                }

                return a.start - b.start;
            }

        });

        for(int i = 0; i < n; i++) {
            int curS = intervals[i].start;
            int curE = intervals[i].finish;

            // if result has no interval OR if start of currentInterval is greater than end of lastInterval of result, we should add currentInterval into result
            if(res.size() == 0 || curS > res.get(res.size() - 1).finish) {
                res.add(intervals[i]);
            }
            else {

                // updating end of lastInterval of result and is equal to maximum of 'end of lastInterval of result' & 'end of currentInterval'
                res.get(res.size() - 1).finish = Math.max(res.get(res.size() - 1).finish, curE);  // getting perfect end of interval
            }
        }

        return res;
    }

}

*****************************************************************
                      OR

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class _8_MergeOverlappingInterval {

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