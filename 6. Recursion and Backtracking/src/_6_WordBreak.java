/*
Question: Word Break
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated
sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 */

import java.util.ArrayList;

public class _6_WordBreak {

    // Time Complexity: O(n*s) where s is the length of the largest string in the dictionary and n is the length of the given string.
    // Space Complexity: we are using some extra space

    public boolean wordBreak(String s, ArrayList<String> wordDict) {

        //Step 1 : To have an array to mark if the segment of string s is seen or not
        boolean[] seen = new boolean[s.length() + 1];

        //Step 2 : Iterate through the string s to generate substring and check if that substring is present in dictionary or not
        seen[0] = true;
        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j < i; j++){

                // check if a substring is contained in dictionary then mark the j th position is visited so for next segment in string s we can check seen array and proceed
                if(seen[j] && wordDict.contains(s.substring(j, i))){
                    seen[i] = true;
                }
            }
        }
        return seen[s.length()];
    }
}
