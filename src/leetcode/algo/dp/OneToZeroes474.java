package leetcode.algo.dp;

// https://leetcode-cn.com/problems/ones-and-zeroes/
public class OneToZeroes474 {
    // dp
    // time O(k*m*n + sum[len(str[i])])
    // espace O(K*M*N)
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        // 预处理每一个字符包含的0 和 1 数量
        int[][] cnt = new int[len][2];
        for (int i = 0; i < len; i++) {
            String str = strs[i];
            int zero = 0, one = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            cnt[i][0] = zero;
            cnt[i][1] = one;
        }

        // 处理只考虑第一件物品的情况
        int[][][] dp = new int[len][m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[0][i][j] = (i >= cnt[0][0] && j >= cnt[0][1]) ? 1 : 0;
            }
        }

        // 处理考虑其余物品的情况
        for (int k = 1; k < len; k++) {
            int zero = cnt[k][0], one = cnt[k][1];
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    // 不选择第k件物品
                    int a = dp[k - 1][i][j];
                    // 选择第 k 件物品（前提是有足够的 m 和 n 额度可使用
                    int b = (i >= zero && j >= one) ? dp[k - 1][i - zero][j - one] + 1 : 0;
                    dp[k][i][j] = Math.max(a, b);
                }
            }
        }
        return dp[len - 1][m][n];
    }
}
