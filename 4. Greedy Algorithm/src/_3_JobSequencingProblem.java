/*
Question: Job Sequencing Problem
Given a set of N jobs where each jobi has a deadline and profit associated with it.
Each job takes 1 unit of time to complete and only one job can be scheduled at a time. We earn the profit associated
with job if and only if the job is completed by its deadline.
Find the number of jobs done and the maximum profit.

Note: Jobs will be given in the form (Jobid, Deadline, Profit) associated with that Job.

Example 1:

Input:
N = 4
Jobs = {(1,4,20),(2,1,10),(3,1,40),(4,1,30)}
Output:
2 60
Explanation:
Job1 and Job3 can be done with maximum profit of 60 (20+40).

Example 2:

Input:
N = 5
Jobs = {(1,2,100),(2,1,19),(3,2,27),(4,1,25),(5,1,15)}
Output:
2 127
Explanation:
2 jobs can be done with maximum profit of 127 (100+27).
 */

import java.util.Arrays;

public class _3_JobSequencingProblem {

    // Time Complexity : O(NlogN)+O(N*M) --> O(NlogN) for sorting the jobs in decreasing order of profit. O(N*M) since we are iterating through all N jobs and for every job we are checking from the last deadline, say M deadlines in the worst case.
    // Space Complexity: O(M) --> O(M) for an array that keeps track on which day which job is performed if M is the maximum deadline available.

    /* given in question

    static class Job {
        int id;
        int profit;
        int deadline;
        public Job(int x, int y, int z){
            this.id = x;
            this.deadline = y;
            this.profit = z;
        }
    }
     */

    //Function to find the maximum profit and the number of jobs done.

    public static int[] JobScheduling(Job arr[], int n) {

        // sort comparator
        Arrays.sort(arr, (a, b) -> (b.profit - a.profit));

        // finding maximum deadline
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i].deadline > max) {
                max = arr[i].deadline;
            }
        }

        // creating array of maximum deadline in size
        int result[] = new int[max + 1];
        // initializing result with -1
        for (int i = 1; i <= max; i++) {
            result[i] = -1;
        }

        int countJobs = 0;
        int jobProfit = 0;

        // iterate for all the job id
        for (int i = 0; i < n; i++) {
            // whenever we get job id, we are trying to perform as late as possible i.e try from last day till first day
            for (int j = arr[i].deadline; j > 0; j--) {

                // if empty day is found, perform job on that day
                if (result[j] == -1) {
                    result[j] = i;

                    countJobs++;
                    jobProfit += arr[i].profit;
                    break;
                }
            }
        }

        int ans[] = new int[2];
        ans[0] = countJobs;
        ans[1] = jobProfit;
        return ans;
    }
}

2 way of comparator
----------------------------------------------------------

    /* given in question

    static class Job {
        int id;
        int profit;
        int deadline;
        public Job(int x, int y, int z){
            this.id = x;
            this.deadline = y;
            this.profit = z;
        }
    }
     */
    class Solution
    {
        class jobComparator implements Comparator<Job>{

            public int compare(Job o1, Job o2) {
                return o2.profit - o1.profit;
            }
        }
        //Function to find the maximum profit and the number of jobs done.
        int[] JobScheduling(Job arr[], int n){
            // sort comparator
            Arrays.sort(arr, new jobComparator());

            // finding maximum deadline
            int max = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i].deadline > max) {
                    max = arr[i].deadline;
                }
            }

            // creating array of maximum deadline in size
            int result[] = new int[max + 1];
            // initializing result with -1
            for (int i = 1; i <= max; i++) {
                result[i] = -1;
            }

            int countJobs = 0;
            int jobProfit = 0;

            // iterate for all the job id
            for (int i = 0; i < n; i++) {
                // whenever we get job id, we are trying to perform as late as possible i.e try from last day till first day
                for (int j = arr[i].deadline; j > 0; j--) {

                    // if empty day is found, perform job on that day
                    if (result[j] == -1) {
                        result[j] = i;

                        countJobs++;
                        jobProfit += arr[i].profit;
                        break;
                    }
                }
            }

            int ans[] = new int[2];
            ans[0] = countJobs;
            ans[1] = jobProfit;
            return ans;
        }
    }

3 way of comparator
--------------------------------------------------------------

    class Solution
    {
        //Function to find the maximum profit and the number of jobs done.
        int[] JobScheduling(Job arr[], int n){
            // sort comparator
            Arrays.sort(arr, new Comparator<Job>() {
                @Override
                public int compare(Job o1, Job o2) {
                    return o2.profit - o1.profit;
                }
            });

            // finding maximum deadline
            int max = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i].deadline > max) {
                    max = arr[i].deadline;
                }
            }

            // creating array of maximum deadline in size
            int result[] = new int[max + 1];
            // initializing result with -1
            for (int i = 1; i <= max; i++) {
                result[i] = -1;
            }

            int countJobs = 0;
            int jobProfit = 0;

            // iterate for all the job id
            for (int i = 0; i < n; i++) {
                // whenever we get job id, we are trying to perform as late as possible i.e try from last day till first day
                for (int j = arr[i].deadline; j > 0; j--) {

                    // if empty day is found, perform job on that day
                    if (result[j] == -1) {
                        result[j] = i;

                        countJobs++;
                        jobProfit += arr[i].profit;
                        break;
                    }
                }
            }

            int ans[] = new int[2];
            ans[0] = countJobs;
            ans[1] = jobProfit;
            return ans;
        }
    }
