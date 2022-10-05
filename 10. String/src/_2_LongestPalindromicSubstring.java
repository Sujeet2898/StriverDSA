/*
Question: Longest Palindromic Substring
Given a string s, return the longest palindromic substring in s.
A string is called a palindrome string if the reverse of that string is the same as the original string.

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Input: s = "cbbd"
Output: "bb"
 */

public class _2_LongestPalindromicSubstring {

    // Brute force approach
    // Time Complexity: O(N^3) --> We are creating every substring which takes N ^ 2 time. Checking whether the substring is palindromic takes O(length of substring) time, which can be maximum O(N). Thus, the total time complexity is O(N ^ 3).
    // Space Complexity: O(N) --> We are storing substring in a variable string.

    public String longestPalindrome1(String s) {
        String ans = "";
        for(int i = 0; i < s.length(); i++){
            for(int j = s.length() - 1; j > i; j--){
                String temp = s.substring(i, j+1);
                if(temp.length() > ans.length() && palindrome(temp)) {
                    ans = temp;
                    break;
                }
            }
        }
        return (ans.length() > 0) ? ans : s.charAt(0) + "";
    }

    boolean palindrome(String s) {
        int i = 0;
        int j = s.length()-1;
        while(j > i) {
            if(s.charAt(i)==s.charAt(j)){
                i++;
                j--;
            }else {
                return false;
            }
        }
        return true;
    }

    // Using Dynamic Programming
    // Time Complexity: O(N^2) --> We are traversing the ‘DP’ array once.
    // Space Complexity: O(N^2) --> We are using a ‘DP’ array of N*N dimensions.

    public String longestPalindrome2(String s){
        boolean[][] dp = new boolean[s.length()][s.length()];
        int length = 0;
        int start = 0;

        for (int g = 0; g < s.length(); g++){  // g --> diagonal gap
            for (int i = 0, j = g; j < s.length(); i++, j++){
                // gap is 0
                if(g == 0){
                    dp[i][j] = true;
                }
                // gap is 1
                else if(g == 1){
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = false;
                    }
                }
                // gap is more than 1
                else {
                    if(s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == true) {
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = false;
                    }
                }

                if (dp[i][j]){
                    length = g + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + length);
    }
}
