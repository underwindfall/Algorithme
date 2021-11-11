package leetcode.algo.dp;

import java.util.Arrays;

// https://leetcode-cn.com/problems/k-inverse-pairs-array/
public class KInversePairs629 {
    // time O(nk^2)
    // space O(nk)
    class DP {
        // dp[i][j] = dp[i-1][j] + dp[i-1][j-1] + ... + dp[i-1][j-i-1]
        int MOD = (int) (1e9) + 7;

        public int kInversePairs(int n, int k) {
            long[][] dp = new long[n + 1][k + 1];
            dp[1][0] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 0; j <= Math.min(k, i * (i - 1) / 2); j++) {
                    for (int x = Math.max(0, j - i + 1); x <= j; x++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][x]) % MOD;
                    }
                }
            }
            return (int) dp[n][k];

        }
    }

    class DFSMemo {
        /**
         * f(n, k) = f(n - 1)(k) + f(n - 1)(k - 1) + f(n - 2)(k - 2) + f(n - 1)(0)
         **/

        /**
         * n = 4, k = 2 (1,2,3,4)
         * 
         * 
         * (3,1,2) => (3,1) , (3, 2) insert (3,1,2,4) (2, 3, 1)=> (2, 1)(3, 1)
         * insert(2,3,1,4)
         ***/
        int MOD = (int) (1e9) + 7;

        // time O(nk )
        // space O(nk)
        public int kInversePairs(int n, int k) {
            // f(3,2) = f(2, 0)
            // f(3, 1 = f(2, 0)
            long[][] memo = new long[n + 1][k + 1];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(memo[i], -1);
            }
            return (int) (dfs(n, k, memo) % MOD);
        }

        long dfs(int n, int k, long[][] memo) {
            if (k > n * (n - 1) / 2) {
                return 0;
            }
            if (n == 1) {
                return k == 0 ? 1 : 0;
            }
            if (memo[n][k] != -1) {
                return memo[n][k];
            }
            long ans = 0;

            // [k - (n - 1), k]
            for (int i = Math.max(0, k - (n - 1)); i <= k; i++) {
                ans += dfs(n - 1, i, memo);
            }
            ans = ans % MOD;
            memo[n][k] = ans;
            return ans;
        }
    }
}