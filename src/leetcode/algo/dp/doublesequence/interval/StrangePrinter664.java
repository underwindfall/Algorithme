package leetcode.algo.dp.doublesequence.interval;

//https://leetcode-cn.com/problems/strange-printer/
public class StrangePrinter664 {
    // top down
    class RecurisveMEMO {
        private int memo[][];

        public int strangePrinter(String s) {
            int n = s.length();
            memo = new int[n][n];
            return turn(s.toCharArray(), 0, n - 1);
        }

        int turn(char[] s, int i, int j) {
            // Empty string
            if (i > j)
                return 0;
            // Solved
            if (memo[i][j] > 0)
                return memo[i][j];

            // 基本的情况 不在意i=j与否
            int ans = turn(s, i, j - 1) + 1;
            for (int k = i; k < j; k++) {
                if (s[k] == s[j]) {
                    ans = Math.min(ans, turn(s, i, k) + turn(s, k + 1, j - 1));
                }
            }
            memo[i][j] = ans;
            return memo[i][j];
        }
    }

    // bottom up
    // 1. s(i) != s(j)
    // dp(i, j) = d (i, j - 1) + 1 -> 不相同的时候就是j单独打印 然后打印j-1之前的
    // 2. s(i) == s(j)
    // dp(i, j) = min(dp(i, k) + dp( k + 1, j - 1))
    class DP {
        public int strangePrinter(String s) {
            int n = s.length();
            if (n == 0)
                return 0;
            int[][] dp = new int[n + 1][n + 1];
            for (int i = 0; i < n; i++) {
                dp[i][i] = 1;
            }
            for (int len = 2; len <= n; len++) {
                for (int i = 0; i < n - (len - 1); i++) {
                    int j = i + len - 1;
                    dp[i][j] = dp[i + 1][j] + 1;
                    for (int k = i + 1; k <= j; k++) {
                        if (s.charAt(i) == s.charAt(k)) {
                            dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + dp[k + 1][j]);
                        }
                    }
                }
            }
            return dp[0][n - 1];
        }
    }
}
