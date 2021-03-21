package leetcode.algo.dp.doublesequence;

// https://leetcode-cn.com/problems/distinct-subsequences/
public class NumDistinct115 {

    public int numDistinct(String s, String t) {
        // dp[i][j] 代表 T 前 i 字符串可以由 S j 字符串组成最多个数.
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int j = 0; j < s.length() + 1; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < t.length() + 1; i++) {
            for (int j = 1; j < s.length() + 1; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    // prefix s的前j组合 可以被匹配到位置当中，也可以不被匹配到位置当中
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[t.length()][s.length()];
    }
}
