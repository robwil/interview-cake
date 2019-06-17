package grokking.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/permutation-in-string/
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 *
 * Example 1:
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 */
public class PermutationInString {
    public boolean findPermutation(String pattern, String str) {
        // Step 0: error checking
        if (pattern.length() > str.length()) {
            return false;
        }

        // Step 1: calculate map of char counts for pattern
        Map<Character, Integer> patternCharCounts = new HashMap<>();
        for (char c : pattern.toCharArray()) {
            patternCharCounts.put(c, patternCharCounts.getOrDefault(c, 0) + 1);
        }
        int totalDistinctChars = patternCharCounts.size();

        // Step 2: create sliding window of size pattern.length()
        char[] chars = str.toCharArray();
        int windowStart = 0;
        int distinctCharMatches = 0;
        for (int i = 0; i < chars.length; i++) {
            Integer count;
            if (i >= pattern.length()) {
                char oldChar = chars[windowStart++];
                count = patternCharCounts.get(oldChar);
                if (count != null) {
                    patternCharCounts.put(oldChar, count + 1);
                    if (count + 1 == 0) {
                        distinctCharMatches++;
                    }
                    if (count == 0) { // count was 0, now it's 1
                        distinctCharMatches--;
                    }
                }
            }
            char newChar = chars[i];
            count = patternCharCounts.get(newChar);
            if (count != null) {
                patternCharCounts.put(newChar, count - 1);
                if (count - 1 == 0) {
                    distinctCharMatches++;
                    if (distinctCharMatches == totalDistinctChars) {
                        return true;
                    }
                }
                if (count == 0) { // it was 0, but now it will be -1
                    distinctCharMatches--;
                }
            }

        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new PermutationInString().findPermutation("abc", "oidbcaf"));
        System.out.println(new PermutationInString().findPermutation("dc", "odicf"));
        System.out.println(new PermutationInString().findPermutation("bcdyabcdx", "bcdxabcdy"));
        System.out.println(new PermutationInString().findPermutation("abc", "aaacb"));
    }
}
