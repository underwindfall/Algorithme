package leetcode.algo.dp.doublesequence;

// https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings/
public class MinimumDeleteSum712 {
    // dp (i, j) (s1 i:) (s2 j:)
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            dp[i][n] = dp[i + 1][n] + s1.codePointAt(i);
        }

        for (int j = n - 1; j >= 0; j--) {
            dp[m][j] = dp[m][j + 1] + s2.codePointAt(j);
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j] + s1.codePointAt(i), dp[i][j + 1] + s2.codePointAt(j));
                }
            }
        }
        return dp[0][0];
    }

    class AnotherDp {
        // LCS 的变种
        // 求最小的删除AsciII的码的值
        // 就是求LCS 最大的AsciII 
        public int minimumDeleteSum(String s1, String s2) {
            int len = s1.length();
            int len2 = s2.length();
            int sum = 0;
            for (int i = 0; i < len; i++)
                sum += s1.charAt(i);
            for (int i = 0; i < len2; i++)
                sum += s2.charAt(i);
            int[][] dp = new int[len + 1][len2 + 1];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len2; j++) {
                    if (s1.charAt(i) == s2.charAt(j)) {
                        dp[i + 1][j + 1] = dp[i][j] + ((int) s1.charAt(i));
                    } else {
                        dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                    }
                }
            }
            return sum - (dp[len][len2] * 2);
        }

    }
}
