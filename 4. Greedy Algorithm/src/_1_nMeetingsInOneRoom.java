/*
Question: N meetings in one room
There is one meeting room in a firm. There are N meetings in the form of (start[i], end[i]) where start[i] is start time of meeting i and end[i] is finish time of meeting i.
What is the maximum number of meetings that can be accommodated in the meeting room when only one meeting can be held in the meeting room at a particular time?
Note: Start time of one chosen meeting can't be equal to the end time of the other chosen meeting.

Example 1:

Input:
N = 6
start[] = {1,3,0,5,8,5}
end[] =  {2,4,6,7,9,9}
Output:
4
Explanation:
Maximum four meetings can be held with
given start and end timings.
The meetings are - (1, 2),(3, 4), (5,7) and (8,9)

Example 2:

Input:
N = 3
start[] = {10, 12, 20}
end[] = {20, 25, 30}
Output:
1
Explanation:
Only one meetings can be held
with given start and end timings.

Your Task :
You don't need to read inputs or print anything. Complete the function maxMeetings() that takes two arrays start[] and
end[] along with their size N as input parameters and returns the maximum number of meetings that can be held in the meeting room.

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class _1_nMeetingsInOneRoom {

    // Time Complexity : O(N*LogN) --> O(n) +O(n log n) + O(n) ~O(n log n)
    // O(n) to iterate through every position and insert them in a data structure. O(n log n)  to sort the data structure
    // in ascending order of end time. O(n)  to iterate through the positions and check which meeting can be performed.
    // Space Complexity: O(N)

    static class meeting implements Comparable<meeting> {
        int start;
        int end;
        int pos;

        public meeting(int start, int end, int pos) {
            this.start = start;
            this.end = end;
            this.pos = pos;
        }

        public int compareTo(meeting o) {
            return this.end - o.end;
        }
    }

    public static int maxMeetings(int start[], int end[], int n){
        ArrayList<meeting> meet = new ArrayList<>();

        for(int i = 0; i < start.length; i++) {
            meet.add(new meeting(start[i], end[i], i + 1));  // i+1 --> order in which the meetings will be performed
        }

        Collections.sort(meet);

        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(meet.get(0).pos);
        int limit = meet.get(0).end;

        for(int i = 1; i < start.length; i++) {
            if(meet.get(i).start > limit) {
                limit = meet.get(i).end;
                answer.add(meet.get(i).pos);
            }
        }
//        System.out.println("The order in which the meetings will be performed is ");
//        for(int i = 0;i<answer.size(); i++) {
//            System.out.print(answer.get(i) + " ");
//        }

        int count = 0;
        for(int i = 0; i < answer.size(); i++) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[]=new int[n];
        int b[]=new int[n];

        for(int i = 0; i < n; i++){
            a[i]=sc.nextInt();
        }
        for(int i = 0; i < n; i++){
            b[i]=sc.nextInt();
        }
//        maxMeetings(a, b, n);
        System.out.println(maxMeetings(a, b, n));
    }
}
