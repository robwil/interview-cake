package leetcode.recursion;

import java.util.HashMap;
import java.util.Map;

public class MyPower {
    // Initial attempt, recursive
    public double myPowRecursive(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / x * myPowRecursive(x, n + 1);
        }
        return x * myPowRecursive(x, n - 1);
    }

    // Iterative attempt
    public double myPowIterative(double x, int n) {
        double result = 1;
        if (n == 0) {
            return result;
        }
        if (n < 0) {
            while (n < 0) {
                result /= x;
                n++;
            }
            return result;
        }
        while (n > 0) {
            result *= x;
            n--;
        }
        return result;
    }

    // Still too slow, so let's try a divide and conquer algo
    private Map<Integer, Double> memo = new HashMap<>();

    public double myPow(double x, int n) {
        memo = new HashMap<>();
        return myPowHelper(x, n);
    }

    public double myPowHelper(double x, int n) {
        Double cacheResult = memo.get(n);
        if (cacheResult != null) {
            return cacheResult;
        }
        if (x == 1) {
            return x;
        }
        if (n < 0) {
            if (n == -2147483648) {
                // handle for int overflow case
                return 1 / x * myPowHelper(1 / x, 2147483647);
            }
            return myPowHelper(1 / x, -n);
        }
        if (n == 0) {
            memo.put(0, 1.0);
            return 1;
        }
        if (n == 1) {
            memo.put(1, x);
            return x;
        }
        double halfResult = myPow(x, n / 2);
        memo.put(n / 2, halfResult);
        double result = halfResult * halfResult;
        if (n % 2 == 1) {
            result *= x;
        }
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        MyPower p = new MyPower();
        System.out.println("pow(2,2) = " + p.myPow(2, -2));
        System.out.println("pow(2,2) = " + p.myPow(2, 2));
        System.out.println("pow(2,2) = " + p.myPow(2, 3));
        System.out.println("pow(2,10) = " + p.myPow(2, 10));
        System.out.println("pow(2,11) = " + p.myPow(2, 11));
        System.out.println("pow(2,2147483647) = " + p.myPow(2, 2147483647));
        System.out.println("pow(2,-2147483648) = " + p.myPow(2, -2147483648));
    }
}