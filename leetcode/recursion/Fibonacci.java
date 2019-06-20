package leetcode.recursion;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    // public int fib(int N) {
    //     if (N == 0 || N == 1) return N;
    //     return fib(N, new int[N-1]);
    // }
    // private int fib(int N, int[] memo) {
    //     if (N == 0) {
    //         return N;
    //     }
    //     if (N == 1) {
    //         memo[N-1] = 1;
    //         return N;
    //     }
    //     int Fn1;
    //     if (memo[N-2] != 0) {
    //         Fn1 = memo[N-2];
    //     } else {
    //         Fn1 = fib(N-1, memo);
    //     }
    //     int Fn2;
    //     if (N-2 == 0) {
    //         Fn2 = 0;
    //     } else if (memo[N-3] != 0) {
    //         Fn2 = memo[N-3];
    //     } else {
    //         Fn2 = fib(N-2, memo);
    //     }
    //     return Fn1 + Fn2;
    // }

    private Map<Integer, Integer> cache = new HashMap<>();

    public int fib(int N) {
        Integer result = cache.get(N);
        if (result != null) {
            return result;
        }
        if (N == 0 || N == 1) {
            cache.put(N, N);
            return N;
        }
        int val = fib(N-1) + fib(N-2);
        cache.put(N, val);
        return val;
    }

    public static void main(String[] args) {
        Fibonacci o = new Fibonacci();
        for (int i = 0; i < 10; i++) {
            System.out.println("fib " + i + " = " + o.fib(i));
        }
    }
}
