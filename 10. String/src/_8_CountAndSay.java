/*
Question: Count and Say
The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

** countAndSay(1) = "1"
** countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.

To determine how you "say" a digit string, split it into the minimal number of substrings such that each substring contains exactly one unique digit.
Then for each substring, say the number of digits, then say the digit. Finally, concatenate every said digit.

For example, the saying and conversion for digit string "3322251":
Given a positive integer n, return the nth term of the count-and-say sequence.

Input: n = 1
Output: "1"
Explanation: This is the base case.

Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = say "1" = one 1 = "11"
countAndSay(3) = say "11" = two 1's = "21"
countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 */

public class _8_CountAndSay {

    // Time Complexity: O(N*L), where N = nth term in the sequence and L = max length of the string.
    // Reason: Since we traverse n times and each time we’ll iterate for the maximum length of string L
    // Space Complexity: O(N) --> We’re using a linear vector of size N to store all answers.

    public String countAndSay(int n) {
        // We initialize our starting string str with "1".
        String str = "1";

        // main loop
        for(int i = 1; i < n; i++){
            str = countIdx(str);
        }
        return str;
    }

    public String countIdx(String str){
        StringBuilder sb = new StringBuilder();

        //get the first digit
        char ch = str.charAt(0);

        // count of every character is atLeast 1 so take count as 1
        int count = 1;

        // creating a loop to count the number of adjacent characters in string
        for(int i = 1; i < str.length(); i++){
            if(str.charAt(i) == ch){
                count++;
            }
            else{
                // add count and current character to sb
                sb.append(count);
                sb.append(ch);

                ch = str.charAt(i);

                // reset count to 1
                count = 1;
            }
        }
        // outside loop, append counter and the last index character
        sb.append(count);
        sb.append(ch);

        // return sb as string
        return sb.toString();
    }
}
