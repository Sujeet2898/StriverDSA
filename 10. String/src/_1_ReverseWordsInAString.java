/*
Question: Reverse Words in a String
Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only
have a single space separating the words. Do not include any extra spaces.

Input: s = "the sky is blue"
Output: "blue is sky the"

Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 */

public class _1_ReverseWordsInAString {

    // Time Complexity: O(N) --> As for every word we are attaching it in the String.
    // Space Complexity: O(N) --> For storing the reversed string.

    public String reverseWords1(String str){

        //to remove leading and trailing spaces
        str = str.trim();

        String ans = "";

        //splits string into an array of words whenever a single space is detected
        String[] temp = str.split(" ");

        for (int i = temp.length-1; i >= 0; i--){

            // checks if the string has blank space if yes then do not bother to go through the rest continue
            if(temp[i].isEmpty()){
                continue;
            }

            //addition of spaces if we have not reached the last word
            if(i != 0) {
                ans += temp[i] + " ";
            }else {
                ans += temp[i];
            }
        }
        return ans;
    }

    // Same complexity using StringBuilder
    public String reverseWords2(String str){

        //to remove leading and trailing spaces
        str = str.trim();

        StringBuilder ans = new StringBuilder();

        //splits string into an array of words whenever a single space is detected
        String[] temp = str.split(" ");

        for (int i = temp.length-1; i >= 0; i--){

            // checks if the string has blank space if yes then do not bother to go through the rest continue
            if(temp[i].isEmpty()){
                continue;
            }

            //addition of spaces if we have not reached the last word
            if(i != 0) {
                ans.append(temp[i]).append(" ");
            }else {
                ans.append(temp[i]);
            }
        }
        return ans.toString();
    }
}
