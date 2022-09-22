/*
Question: Permutation Sequence
The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Input: n = 3, k = 3
Output: "213"

Input: n = 4, k = 9
Output: "2314"

Input: n = 3, k = 1
Output: "123"
 */

import java.util.ArrayList;
import java.util.Collections;

public class _6_PermutationSequence {

/*
Brute Force Approach: using recursion
------------------------------------
Time complexity: O(N! * N) +O(N! Log N!)
Reason: The recursion takes O(N!) time because we generate every possible permutation and
another O(N) time is required to make a deep copy and store every sequence in the data structure.
Also, O(N! Log N!) time required to sort the data structure

Space complexity: O(N)
Reason: Result stored in an arraylist, we are auxiliary space taken by recursion
 */

    static void swap(char chArray[], int i, int j) {
        char ch = chArray[i];
        chArray[i] = chArray[j];
        chArray[j] = ch;
    }

    static void permutationHelper(char chArray[], int index, ArrayList<String>result) {
        if (index == chArray.length) {
            String str = new String(chArray);
            result.add(str);
            return;
        }

        for (int i = index; i < chArray.length; i++) {
            swap(chArray, i, index);
            permutationHelper(chArray, index + 1, result);
            swap(chArray, i, index);
        }
    }

    public static String getPermutation1(int n, int k) {
        String str = "";
        ArrayList<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            str += i;
        }
        permutationHelper(str.toCharArray(), 0, result);
        Collections.sort(result);

        return result.get(k);
    }

/*
Time Complexity: O(N) * O(N) = O(N^2)
Reason: We are placing N numbers in N positions. This will take O(N) time. For every number, we are reducing the search
space by removing the element already placed in the previous step. This takes another O(N) time.

Space Complexity: O(N)
Reason: We are storing the numbers in a data structure(here arrayList)
 */

    public static String getPermutation2(int n, int k) {
        int fact = 1;
        ArrayList<Integer> numbers = new ArrayList<>();

        // compute (n-1)! i.e first block is of size (n-1)!
        for (int i = 1; i < n; i++) {
            fact = fact * i;
            numbers.add(i);  // store upto second last number
        }
        numbers.add(n);   // also store last number

        String ans = "";

        // 0 based indexing
        k = k - 1;

        // run infinite loop till it reaches empty size
        while (true) {

            // finding first digit of permutation
            ans = ans + "" + numbers.get(k / fact);

            // remove that digit of first permutation to get new array
            numbers.remove(k / fact);

            // if list is empty, we break out or else kept on doing
            if (numbers.size() == 0) {
                break;
            }

            // finding kth permutation of first permuted num
            k = k % fact;

            // reducing the factorial at every step
            fact = fact / numbers.size();
        }
        return ans;
    }
}
