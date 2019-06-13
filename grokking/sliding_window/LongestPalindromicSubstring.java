package grokking.sliding_window;

public class LongestPalindromicSubstring {
    private boolean validPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
    public String longestPalindrome(String s) {
        // sorting does not help; that makes it harder
        // generating all sub-arrays is impractical because 2^N is prohibitive for potential length of 1000

        // another approach is to use a sliding window of incrementing length K, searching for palindromes that way
        for (int k = s.length() - 1; k >= 0; k--) {
            int left = 0;
            int right = left + k;
            while (right <= s.length() - 1) {
                if (validPalindrome(s, left, right)) {
                    return s.substring(left, right+1);
                }
                left++;
                right++;
            }
        }
        // should never happen, except for empty string
        return "";
    }

    public static void main(String[] args) {
        System.out.println("longest substring of \"babad\" = " + new LongestPalindromicSubstring().longestPalindrome("babad"));
    }
}
