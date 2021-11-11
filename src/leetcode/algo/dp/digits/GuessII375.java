
package leetcode.algo.dp.digits;

// https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
public class GuessII375 {
    // time O(n^3)
    // space O(n^2)
    class DP {
        // dp (i, j) 范围（i，j）内确保胜利的最少金额
        public int getMoneyAmount(int n) {
            int[][] f = new int[n + 1][n + 1];
            for (int i = n - 1; i >= 1; i--) {
                for (int j = i + 1; j <= n; j++) {
                    int minCost = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        int cost = k + Math.max(f[i][k - 1], f[k + 1][j]);
                        minCost = Math.min(minCost, cost);
                    }
                    f[i][j] = minCost;
                }
            }
            return f[1][n];
        }
    }

    // time O(n*m)
    // space O(n*m)
    class DFSMemo {
        int[][] dp;

        public int getMoneyAmount(int n) {
            dp = new int[n + 1][n + 1];
            for (int i = 0; i <= n; ++i)
                for (int j = 0; j <= n; ++j)
                    dp[i][j] = -1;
            return guess(1, n);
        }

        int guess(int start, int end) {
            if (start > end)
                return 0;
            else if (dp[start][end] >= 0)
                return dp[start][end];
            else if (start == end)
                return dp[start][end] = 0;
            int min = -1;
            for (int mid = start; mid <= end; ++mid) {
                int pay = mid + Math.max(guess(start, mid - 1), guess(mid + 1, end));
                if (min == -1 || pay < min) {
                    min = pay;
                }
            }
            return dp[start][end] = min;
        }
    }

}
