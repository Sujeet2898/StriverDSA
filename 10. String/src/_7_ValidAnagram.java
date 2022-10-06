/*
Question: Valid Anagram
Given two strings s and t, return true if t is an anagram of s, and false otherwise.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Input: s = "anagram", t = "nagaram"
Output: true

Input: s = "rat", t = "car"
Output: false
 */

import java.util.Arrays;
import java.util.HashMap;

public class _7_ValidAnagram {

    // Brute Force approach
    // Time Complexity: O(O(N * log(N))) --> Since we are sorting the strings which have time complexity O(N * log(N)).
    // Space Complexity: O(1) --> Since we not using any extra space

    public boolean isAnagram1(String s, String t) {
        // Get lengths of both strings.
        int n1 = s.length();
        int n2 = t.length();

        // If length of both strings is not same then they cannot be anagram.
        if (n1 != n2) {
            return false;
        }

        // Convert input string to char array.
        char[] tempArray1 = s.toCharArray();
        char[] tempArray2 = t.toCharArray();

        // Sort tempArray.
        Arrays.sort(tempArray1);
        Arrays.sort(tempArray2);

        // Compare sorted strings.
        for (int i = 0; i < n1; i++) {
            if (tempArray1[i] != tempArray2[i]) {
                return false;
            }
        }
        return true;
    }

    // Using Hashing
    // Time Complexity: O(N) --> Since we are using a Hash Array to store the frequency of characters. We are traversing the string once so the overall time complexity will be O(N).
    // Space Complexity: O(Number of different characters) --> Since the size of the hash array will be equal to the number of different characters in the strings.

    public boolean isAnagram2(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++){
            char ch = t.charAt(i);

            if (map.containsKey(ch) == false){
                return false;
            }else if (map.get(ch) == 1){
                map.remove(ch);
            }else {
                map.put(ch, map.get(ch) - 1);
            }
        }
        return map.size() == 0;
    }
}
