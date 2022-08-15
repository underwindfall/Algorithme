package leetcode.algo.dp.doublesequence;

//https://leetcode-cn.com/problems/longest-common-subsequence/
// 思路 
// 1.子问题 每个i应该不应该继续累计公共相同字符
// 2.dp[i][j]  i -> text1 j -> text2 最大相同字符串
// 如果相同 不用叠加
// 如果不同 应该选取的是之前前一位字符串的最大相同次数 去两者中间的最大值
// 3. if (char(i) == char(j)) dp[i][j] = dp[i - 1][j - 1] + 1
//    else dp[i][j] = max(dp[i][j - 1], dp[i - 1][j])
// text1(:i)  text2(:j)
public class LongestCommonSubsequence1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int M = text1.length();
        int N = text2.length();
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 1; i <= M; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[M][N];
    }
}
