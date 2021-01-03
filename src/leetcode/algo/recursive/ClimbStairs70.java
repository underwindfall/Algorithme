package leetcode.algo.recursive;

// https://leetcode-cn.com/problems/climbing-stairs/
public class ClimbStairs70 {
    class Memo {
        public int climbStairs(int n) {
            int memo[] = new int[n + 1];
            return climbStairs(0, n, memo);
        }

        private int climbStairs(int i, int n, int[] memo) {
            if (i > n) {
                return 0;
            }
            if (i == n) {
                return 1;
            }
            if (memo[i] > 0) {
                return memo[i];
            }
            memo[i] = climbStairs(i + 1, n, memo) + climbStairs(i + 2, n, memo);
            return memo[i];
        }
    }

    class Fibonacci {
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            int prev1 = 1;
            int prev2 = 2;
            int third = 0;
            for (int i = 3; i <= n; i++) {
                third = prev1 + prev2;
                prev1 = prev2;
                prev2 = third;
            }
            return third;
        }

        public int climbStairsMemo(int n) {
            Integer[] memo = new Integer[n + 1];
            memo[0] = 0;
            memo[1] = 1;
            return memo(memo, n);
        }

        int memo(Integer[] cache, int n) {
            if (cache[n] != null) {
                return cache[n];
            }
            cache[n] = memo(cache, n - 1) + memo(cache, n - 2);
            return cache[n];
        }

    }
}
