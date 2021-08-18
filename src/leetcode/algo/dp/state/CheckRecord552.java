package leetcode.algo.dp.state;

// https://leetcode-cn.com/problems/student-attendance-record-ii/
public class CheckRecord552 {

    // time O(N)
    // espace O(N)
    class DFS {
        int MOD = 1000000007;
        int[][][] memo;

        public int checkRecord(int n) {
            memo = new int[n + 1][2][3];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 3; k++) {
                        memo[i][j][k] = -1;
                    }
                }
            }

            return dfs(n, 0, 0);
        }

        int dfs(int u, int absent, int late) {
            if (absent >= 2)
                return 0;
            if (late >= 3)
                return 0;
            if (u == 0)
                return 1;

            if (memo[u][absent][late] != -1) {
                return memo[u][absent][late];
            }

            int ans = 0;
            //absent
            ans = dfs(u - 1, absent + 1, 0) % MOD;
            //late
            ans = (ans + dfs(u - 1, absent, late + 1)) % MOD;
            //nothing present
            ans = (ans + dfs(u - 1, absent, 0)) % MOD;
            memo[u][absent][late] = ans;
            return ans;
        }
    }

    class DP {
        // ① 0 缺勤，到 n 天连续迟到 0 天。
        // ② 0 缺勤，到 n 天连续迟到 1 天。
        // ③ 0 缺勤，到 n 天连续迟到 2 天。
        // ④ 1 缺勤，到 n 天连续迟到 0 天。
        // ⑤ 1 缺勤，到 n 天连续迟到 1 天。
        // ⑥ 1 缺勤，到 n 天连续迟到 2 天。

        // n -> n - 1
        // ① = ① + ② + ③ (第 n 天未缺勤或迟到)
        // ② = ① (第 n 天迟到)
        // ③ = ② (第 n 天迟到)
        // ④ = ① + ② + ③ (第 n 天缺勤)+ ④ + ⑤ + ⑥ (第 n 天未缺勤或迟到)
        // ⑤ = ④ (第 n 天迟到)
        // ⑥ = ⑤ (第 n 天迟到)
        int mode = 1000000007;
        int[][][] dp;

        public int checkRecord(int n) {
            dp = new int[n + 1][2][3];
            dp[0][0][0] = 1;
            dp[0][0][1] = 1;
            dp[0][1][0] = 1;
            for (int i = 1; i < n; ++i) {
                dp[i][0][0] = ((dp[i - 1][0][0] + dp[i - 1][0][1]) % mode + dp[i - 1][0][2]) % mode;
                dp[i][0][1] = dp[i - 1][0][0];
                dp[i][0][2] = dp[i - 1][0][1];
                dp[i][1][0] = (((dp[i][0][0] + dp[i - 1][1][0]) % mode + dp[i - 1][1][1]) % mode + dp[i - 1][1][2])
                        % mode;
                dp[i][1][1] = dp[i - 1][1][0];
                dp[i][1][2] = dp[i - 1][1][1];

            }

            int ans = 0;
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < 3; ++j) {
                    ans += dp[n - 1][i][j];
                    ans %= mode;
                }
            }
            return ans;
        }
    }
}
