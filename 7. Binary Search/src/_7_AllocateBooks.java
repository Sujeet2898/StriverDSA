/*
Question: Allocate Books
Given an array of integers A of size N and an integer B.
College library has N bags,the ith book has A[i] number of pages.
You have to allocate books to B number of students so that maximum number of pages alloted to a student is minimum.
A book will be allocated to exactly one student.
Each student has to be allocated at least one book.
Allotment should be in contiguous order, for example: A student cannot be allocated book 1 and book 3, skipping book 2.
Calculate and return that minimum possible number.
NOTE: Return -1 if a valid assignment is not possible.

Input 1:
    A = [12, 34, 67, 90]
    B = 2
Output 1:
    113
Explanation 1:
    There are 2 number of students. Books can be distributed in following fashion :
        1) [12] and [34, 67, 90] : Max number of pages is allocated to student 2 with 34 + 67 + 90 = 191 pages
        2) [12, 34] and [67, 90] : Max number of pages is allocated to student 2 with 67 + 90 = 157 pages
        3) [12, 34, 67] and [90] : Max number of pages is allocated to student 1 with 12 + 34 + 67 = 113 pages
           Of the 3 cases, Option 3 has the minimum pages = 113.

Input 2:
    A = [5, 17, 100, 11]
    B = 4
Output 2:
    100
 */

import java.util.ArrayList;

public class _7_AllocateBooks {  

    // Binary Search Approach
    // Time Complexity: O(NlogN) --> Binary search takes O(log N). For every search, we are checking if an allocation is possible or not. Checking for allocation takes O(N).
    // Space Complexity: O(1) --> No extra space is used.

    public static int books(int[] books, int students) {
        if (students > books.length) {
            return -1;
        }
        int low = books[0];
        int high = 0;  // sum

        for (int i = 0; i < books.length; i++) {
            high += books[i];  // sum
            low = Math.min(low, books[i]);
        }
        while (low <= high) {
            int mid = (low + high)/2;
            if (isPossible(books, mid, students)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static boolean isPossible(int[] books, int mid, int students) {
        int count = 0;
        int sumAllocated = 0;
        for (int i = 0; i < books.length; i++) {
            if (sumAllocated + books[i] > mid) {
                count++;
                sumAllocated = books[i];

                if (sumAllocated > mid) {
                    return false;
                }
            } else {
                sumAllocated += books[i];
            }
        }
        if (count < students){
            return true;
        }
        return false;
    }
}
