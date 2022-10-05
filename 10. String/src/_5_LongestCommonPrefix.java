/*
Question: Longest Common Prefix
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Input: strs = ["flower","flow","flight"]
Output: "fl"

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 */

import java.util.Arrays;

public class _5_LongestCommonPrefix {

    // Time Complexity: O(NlogN) --> Sorting + traversing first string.
    // Space Complexity: O(1) --> Since, we are not using any extra space.

    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);

        String first = strs[0];
        String second = strs[strs.length - 1];
        String result = "";


        for(int i = 0; i < first.length(); i++){
            if(first.charAt(i) == second.charAt(i)){
                result += first.charAt(i);
            }
            else break;
        }
        return result;
    }
}
