package leetcode.algo.dp;

public class CutRod {
    // 给出一根长度为n（n为整数）的杆，可以将杆切割为任意份长短不同的杆（其中包含完全不进行切割的情况）
    // 每一份的长度都为整数。给出一个整数数组p[]，p[i]表示长度为i的杆的价格，问如何对杆进行切割可以使利润最大
    class Recursive {
        // O(N^2)
        // O(N^2)
        public int cutRod(int n, int[] p) {
            if (n == 0) {
                return 0;
            }
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < p.length; i++) {
                max = Math.max(p[i], cutRod(n - i, p));
            }
            return max;
        }
    }

    // bottom -up
    class DP {
        // 父问题 i 能够获取到的最大收益
        // 子问题 dp[i] <==> 看成是长度为i的木棍切除的最大收益
        // 子问题组合成 dp[i] = p[j] + dp[i - j]
        class BottomUp {
            public int cutRod(int n, int[] p) {
                int[] dp = new int[n + 1];
                for (int i = 1; i <= n; i++) {
                    int max = Integer.MIN_VALUE;
                    for (int j = 1; j < i; j++) {
                        max = Math.max(max, p[j] + dp[i - j]);
                    }
                    dp[i] = max;
                }
                return dp[n];
            }
        }

        // recursive memorization
        class TopDown {
            public int cutRod(int n, int[] p) {
                int[] memo = new int[n + 1];
                for (int i = 0; i < memo.length; i++)
                    memo[i] = Integer.MIN_VALUE;
                return cutRod(n, p, memo);
            }

            int cutRod(int n, int[] p, int[] memo) {
                if (n == 0) {
                    return 0;
                }
                if (memo[n] != Integer.MIN_VALUE) {
                    return memo[n];
                }
                int max = Integer.MIN_VALUE;
                for (int i = 1; i <= n; i++) {
                    max = Math.max(max, p[i] + cutRod(n - i, p, memo));
                }
                memo[n] = max;
                return max;
            }
        }
    }
}
