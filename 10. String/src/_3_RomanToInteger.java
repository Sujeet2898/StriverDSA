/*
Question: Roman to Integer
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example,
2 is written as II in Roman numeral, just two ones added together.
12 is written as XII, which is simply X + II.
The number 27 is written as XXVII, which is XX + V + II.
Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer.

Input: s = "III"
Output: 3
Explanation: III = 3.

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */

import java.util.HashMap;

public class _3_RomanToInteger {

    // Time Complexity: O(N) --> We traverse the string once to find the integer value. N is length of string.
    // Space Complexity: O(1) --> The HashMap used is of constant size, hence constant extra space is used.

    public int romanToInt(String s){

        // Hash map for finding integer value for roman numerals.
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = map.get(s.charAt(s.length() - 1));

        // Traversing from secondRight to left, there are two cases:
        for (int i = s.length() - 2; i >= 0; i--){

            // case1: Subtract if we get a char with higher value than previous char
            if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))){
                result -= map.get(s.charAt(i));
            }
            // case2: Add if we either get same value char or we get lesser value char
            else {
                result += map.get(s.charAt(i));
            }
        }
        return result;
    }
}
