/*
Question: Palindrome Partitioning
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
A palindrome string is a string that reads the same backward as forward.

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Input: s = "a"
Output: [["a"]]
 */

import java.util.ArrayList;

public class _5_PalindromePartitioning {

/*
Time Complexity: O( (2^n) * k* (n/2) )
Reason: O(2^n) to generate every substring and O(n/2)  to check if the substring generated is a palindrome. O(k) is for
inserting the palindromes in another data structure, where k  is the average length of the palindrome list.

Space Complexity: O(k * x)
Reason: The space complexity can vary depending upon the length of the answer. k is the average length of the list of
palindromes and if we have x such list of palindromes in our final answer.
 */

    public static ArrayList<ArrayList<String>> partition(String str) {
        ArrayList<ArrayList<String>> ansList = new ArrayList<>();
        solve(str, ansList, new ArrayList<>());
        return ansList;
    }

    private static void solve(String str, ArrayList<ArrayList<String>> ansList, ArrayList<String> list){

        if(str.length() == 0){
            ansList.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < str.length(); i++){

            String prefix = str.substring(0, i+1);

            String restOfString = str.substring(i+1);

            if(isPalindrome(prefix)){
                list.add(prefix);
                solve(restOfString, ansList, list);
                list.remove(list.size() - 1);
            }
        }
    }
    private static boolean isPalindrome(String str){
        int i = 0;
        int j = str.length() - 1;

        while(i <= j){
            if(str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
