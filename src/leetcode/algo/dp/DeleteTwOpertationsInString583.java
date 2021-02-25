package leetcode.algo.dp;

// https://leetcode-cn.com/problems/delete-operation-for-two-strings/
public class DeleteTwOpertationsInString583 {
    // 思路
    // S1 S2 如果存在着相同的字符串 word1 + word2 - 2* LCS就是穿出字符穿的最小操作
    // 难点转换成 寻找相同的字符串
    // 使用递归思路
    // 如果word1 word2 最后意味字符串相同 那就 lcs + 1 然后去比较 word1 word2 最后一切的前一位
    // 若不相同，有两种情况
    // 1。 word1 最后一位的前一位 + word2 最后一位
    // 2. word2最后一位的前一位 + word1 最后一位
    class LCS {
        public int minDistance(String word1, String word2) {
            return word1.length() + word2.length() - 2 * lcs(word1, word2, word1.length(), word2.length());
        }

        int lcs(String string1, String string2, int m, int n) {
            if (m == 0 || n == 0) {
                return 0;
            }
            if (string1.charAt(m - 1) == string2.charAt(n - 1)) {
                return 1 + lcs(string1, string2, m - 1, n - 1);
            } else {
                return Math.max(lcs(string1, string2, m - 1, n), lcs(string1, string2, m, n - 1));
            }
        }
    }

    // top down
    // 父问题出发 通过寻找子问题的解
    class MemorizationRecurisve {
        public int minDistance(String word1, String word2) {
            int[][] memo = new int[word1.length() + 1][word2.length() + 1];
            return word1.length() + word2.length() - 2 * lcs(word1, word2, word1.length(), word2.length(), memo);
        }

        int lcs(String string1, String string2, int m, int n, int[][] memo) {
            if (m == 0 || n == 0) {
                return 0;
            }
            if (memo[m][n] > 0) {
                return memo[m][n];
            }
            if (string1.charAt(m - 1) == string2.charAt(n - 1)) {
                memo[m][n] = 1 + lcs(string1, string2, m - 1, n - 1, memo);
            } else {
                memo[m][n] = Math.max(lcs(string1, string2, m - 1, n, memo), lcs(string1, string2, m, n - 1, memo));
            }
            return memo[m][n];
        }
    }

    // bottom up
    // 子问题出发 然后寻找父问题的解
    // dp[i][j] 代表的是 s1 前 i 个字符和 s2 前 j 个字符中最长公共子序列
    // 子问题是dp[i][j] 计算关系
    // 1. s1[i - 1] == s2[j - 1] <==> dp[i][j] = dp[i - 1][j - 1] + 1
    // 2. s1[i - 1] != s2[j - 1] <==> dp[i][j] = max (dp[i - 1][j], dp[i][j -1])
    class DP {
        public int minDistance(String word1, String word2) {
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            for (int i = 0; i <= word1.length(); i++) {
                for (int j = 0; j <= word2.length(); j++) {
                    if (i == 0 || j == 0)
                        continue;
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return word1.length() + word2.length() - 2 * dp[word1.length()][word2.length()];
        }
    }
}
