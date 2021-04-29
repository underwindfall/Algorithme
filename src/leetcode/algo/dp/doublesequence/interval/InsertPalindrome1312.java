package leetcode.algo.dp.doublesequence.interval;

// https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
public class InsertPalindrome1312 {
    public int minInsertions(String s) {
        //1. sub problem s[i, j] 是不是回文
        // 2. guesssing 
        // s(i) == s(j) -> dp(i)(j) = dp(i + 1)(j - 1)
        // s(i) != s(j) -> dp(i)(j) = min(dp(i + 1)(j) + 1, dp(i)(j - 1) + 1)

        int n = s.length();

        int dp[][] = new int[n][n];

        /**for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }**/

        for (int len = 2; len <= n; len++ ) {
            for (int i = 0, j = len - 1; j < n; i++,j++){
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j] + 1, dp[i][j - 1] + 1);
                }
            }
        }

        return dp[0][n - 1];
    }
}
