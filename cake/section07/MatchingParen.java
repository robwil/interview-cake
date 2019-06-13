package cake.section07;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;

public class MatchingParen {

    public static int getClosingParen(String sentence, int openingParenIndex) throws Exception {
//        Deque<Character> stack = new ArrayDeque<>();
//        int n = sentence.length();
//        for (int i = openingParenIndex + 1; i < n; i++) {
//            char c = sentence.charAt(i);
//            if (c == ')') {
//                if (stack.isEmpty()) {
//                    return i;
//                }
//                stack.pop();
//            } else if (c == '(') {
//                stack.push('(');
//            }
//        }
//        // should never happen
//        throw new Exception("didn't find closing parenthesis");

        int n = sentence.length();
        int opened = 0;
        for (int i = openingParenIndex + 1; i < n; i++) {
            char c = sentence.charAt(i);
            if (c == ')') {
                if (opened == 0) {
                    return i;
                }
                opened--;
            } else if (c == '(') {
                opened++;
            }
        }
        // should never happen
        throw new Exception("didn't find closing parenthesis");
    }


    // tests

    @Test
    public void allOpenersThenClosersTest() throws Exception {
        final int expected = 7;
        final int actual = getClosingParen("((((()))))", 2);
        assertEquals(expected, actual);
    }

    @Test
    public void mixedOpenersAndClosersTest() throws Exception {
        final int expected = 10;
        final int actual = getClosingParen("()()((()()))", 5);
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void noMatchingCloserTest() throws Exception {
        getClosingParen("()(()", 2);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MatchingParen.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}