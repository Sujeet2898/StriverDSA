/*
Question: K-th element of two sorted Arrays
Given two sorted arrays arr1 and arr2 of size N and M respectively and an element K. The task is to find the element that
would be at the k’th position of the final sorted array.

Input:
arr1[] = {2, 3, 6, 7, 9}
arr2[] = {1, 4, 8, 10}
k = 5
Output:
6
Explanation:
The final sorted array would be -
1, 2, 3, 4, 6, 7, 8, 9, 10
The 5th element of this array is 6.

Input:
arr1[] = {100, 112, 256, 349, 770}
arr2[] = {72, 86, 113, 119, 265, 445, 892}
k = 7
Output:
256
Explanation:
Final sorted array is - 72, 86, 100, 112,
113, 119, 256, 265, 349, 445, 770, 892
7th element of this array is 256.

Your Task:
You don't need to read input or print anything. Your task is to complete the function kthElement() which takes the arrays
arr1[], arr2[], its size N and M respectively and an integer K as inputs and returns the element at the Kth position.
 */
  
public class _6_KthElementOfTwoSortedArrays {

    // Merge sort Approach
    // Time Complexity: O(k) --> We iterate at total k times. Here K denotes the Kth person in line waiting to be served.
    // Space Complexity: O(1) --> No extra space is used.

    public int kthElement( int arr1[], int arr2[], int n, int m, int k) {
        int i = 0;
        int j = 0;
        int count = 0;
        int ans = 0;

        while (i < n && j < m) {
            if (count == k) {
                break;
            }
            else if (arr1[i] < arr2[j]) {
                ans = arr1[i];
                i++;
            } else {
                ans = arr2[j];
                j++;
            }
            count++;
        }
        if (count != k) {
            if (i != n - 1) {
                ans = arr1[k - count];
            }
            else {
                ans = arr2[k - count];
            }
        }

        return ans;
    }

    // Binary Search Approach
    // Time Complexity: log(min(n1,n2)) --> We are applying binary search in the array with minimum size among the two.
    // Space Complexity: O(1) --> No extra space is used.


    public static int kthelement(int arr1[], int arr2[], int k){
        // ensuring arr2 is longer than arr1
        if(arr2.length < arr1.length){
            return kthelement(arr2, arr1, k);
        }

        int n1 = arr1.length;
        int n2 = arr2.length;
        int low = Math.max(0, k - n2);
        int high = Math.min(k, n1);

        while (low <= high){
            // partition of arr1
            int cut1 = (low + high)/2;
            // partition of arr2.
            int cut2 = k - cut1;

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : arr2[cut2 - 1];

            int right1 = (cut1 == n1) ? Integer.MAX_VALUE : arr1[cut1];
            int right2 = (cut2 == n2) ? Integer.MAX_VALUE : arr2[cut2];

            // median found
            if(left1 <= right2 && left2 <= right1){
                return Math.max(left1, left2);
            }

            // too right on arr1, move left
            else if (left1 > right2){
                high = cut1 - 1;
            }
            // too left on arr1, move right
            else {
                low = cut1 + 1;
            }
        }

        return -1;
    }
}
