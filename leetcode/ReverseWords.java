package leetcode;

import java.util.Arrays;

public class ReverseWords {
    private void reverseInPlace(char[] chars, int min, int max) {
        int left = min;
        int right = max;
        while (left < right) {
            char c = chars[left];
            chars[left] = chars[right];
            chars[right] = c;
            left++;
            right--;
        }
    }

    public String reverseWords(String s) {
        if (s.isEmpty()) {
            return "";
        }
        char[] chars = s.toCharArray();
        int wordStart = 0;
        int extraSpaces = 0;
        while (wordStart < chars.length && chars[wordStart] == ' ') {
            wordStart++;
            extraSpaces++;
        }
        // 1st step: reverse each word
        // this is cool => siht si looc
        for (int i = wordStart + 1; i < chars.length; i++) {
            if (chars[i] == ' ') {
                reverseInPlace(chars, wordStart, i - 1);
                wordStart = i + 1;
                while (wordStart < chars.length && chars[wordStart] == ' ') {
                    wordStart++;
                    extraSpaces++;
                    i = wordStart;
                }
                if (wordStart == chars.length) {
                    extraSpaces++;
                }
            }
        }
        if (wordStart < chars.length - 1) {
            // reverse last word
            reverseInPlace(chars, wordStart, chars.length - 1);
        }
        // 2nd step: reverse whole string
        //  siht si looc => cool is this
        reverseInPlace(chars, 0, chars.length - 1);
        System.out.println("extra spaces = " + extraSpaces);

        // 3rd step: (normal question would not require this space thing...)
        // remove spaces except those between words
        char[] output = new char[chars.length - extraSpaces];
        wordStart = 0;
        while (wordStart < chars.length && chars[wordStart] == ' ') {
            wordStart++;
        }
        for (int i = wordStart, j = 0; i < chars.length && j < output.length; i++, j++) {
            output[j] = chars[i];
            if (chars[i] == ' ') {
                // skip any extra spaces after a space
                while (i + 1 < chars.length && chars[i + 1] == ' ') {
                    i++;
                }
            }
        }

        return new String(output);
    }

    public static void main(String[] args) {
        System.out.println(new ReverseWords().reverseWords("this is cool"));
        System.out.println(new ReverseWords().reverseWords("alexander"));
        System.out.println(new ReverseWords().reverseWords(""));
        System.out.println(new ReverseWords().reverseWords("a much longer situation with a lot of words"));
        System.out.println(new ReverseWords().reverseWords("  space   jam  was a pretty   good  movie  "));
        System.out.println(new ReverseWords().reverseWords(" a"));
        System.out.println("[[" + new ReverseWords().reverseWords("  hello world!  " ) + "]]");
    }
}
