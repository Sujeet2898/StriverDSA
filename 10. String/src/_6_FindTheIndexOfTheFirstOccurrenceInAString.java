/*
Question: Find the Index of the First Occurrence in a String
Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.

Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.
 */

public class _6_FindTheIndexOfTheFirstOccurrenceInAString {

    public int strStr1(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    // Time Complexity: O(N)
    // Space Complexity: O(1)

    public int strStr2(String haystack, String needle) {
        if(needle.length() > haystack.length()){
            return -1;
        }
        int i = 0;
        int j = needle.length()-1;

        while(j < haystack.length()){
            String substr = haystack.substring(i, j + 1);
            if(substr.equals(needle)){
                return i;
            }
            i++;
            j++;
        }
        return -1;
    }

    // Time Complexity: O(N+M) --> Since we are traversing the string ‘str’ and ‘ptr’ at most once the time complexity will be O(N + M).
    // Space Complexity: O(1) --> Since, we are not using any extra space.

    public int strStr3(String haystack, String needle) {
        int M = needle.length();
        int N = haystack.length();

        if(M > N){
            return -1;
        }

        int i, j;
        int p = 0; // hash value for needle
        int t = 0; // hash value for haystack
        int h = 1;
        int d= 256; // number of characters in the input alphabet
        int q= 31; //a prime number

        char[] pat = needle.toCharArray();
        char[] txt = haystack.toCharArray();

        // The value of h would be "pow(d, M-1) % q"
        for (i = 0; i < M - 1; i++)
            h = (h * d) % q;

        // Calculate the hash value of pattern and first window of text
        for (i = 0; i < M; i++) {
            p = (d * p + pat[i]) % q;
            t = (d * t + txt[i]) % q;
        }

        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++) {

            // Check the hash values of current window of text and pattern. If the hash values match then only check for characters on by one
            if ( p == t ) {
                /* Check for characters one by one */
                for (j = 0; j < M; j++) {
                    if (txt[i + j] != pat[j])
                        break;
                }

                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
                if (j == M)
                    return i;
            }

            // Calculate hash value for next window of text: Remove leading digit, add trailing digit
            if ( i < N - M ) {
                t = (d * (t - txt[i] * h) + txt[i + M]) % q;

                // We might get negative value of t, converting it to positive
                if (t < 0)
                    t = (t + q);
            }
        }
        return -1;
    }
}
