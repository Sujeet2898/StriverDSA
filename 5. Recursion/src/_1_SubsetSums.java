/*
Question: Subset Sums
Given a list arr of N integers, print sums of all subsets in it.

Example 1:

Input:
N = 2
arr[] = {2, 3}
Output:
0 2 3 5
Explanation:
When no elements is taken then Sum = 0.
When only 2 is taken then Sum = 2.
When only 3 is taken then Sum = 3.
When element 2 and 3 are taken then
Sum = 2+3 = 5.

Example 2:

Input:
N = 3
arr = {5, 2, 1}
Output:
0 1 2 3 5 6 7 8
 */

import java.util.ArrayList;
import java.util.Collections;

public class _1_SubsetSums {

    // Time Complexity: O(2^n)+O(2^n log(2^n))  --> Each index has two ways. You can either pick it up or not pick it.
    // So for n index, time complexity is O(2^n) and for sorting it will take (2^n log(2^n)).
    // Space Complexity: O(2^n)  --> O(2^n) for storing subset sums, since 2^n subsets can be generated for an array of size n.

    public static void subsetSumsHelper(int index, int sum, ArrayList<Integer> arr, int N, ArrayList<Integer> sumSubset) {
        if (index == N) {  // N = arr.size()
            sumSubset.add(sum);
            return;
        }

        // pick the element
        subsetSumsHelper(index + 1, sum + arr.get(index), arr, N, sumSubset);

        // Do-not pick the element
        subsetSumsHelper(index + 1, sum, arr, N, sumSubset);
    }

    public static ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N) {

        ArrayList<Integer> sumSubset = new ArrayList<>();
        subsetSumsHelper(0, 0, arr, N, sumSubset);
        Collections.sort(sumSubset);
        return sumSubset;
    }

}
