package leetcode.algo.dp.doublesequence;

// https://leetcode-cn.com/problems/longest-palindromic-subsequence/
//思路
//1.
public class LongestPalindromeSubseq516 {

    // 1.subProblem
    // 子问题 S[....i+1,...j-1,.....] 中是个回文子串，那S[i,j]是不是个子串 怎么判断
    // 2.guessing
    //  s[i+1..j-1] 如果是回文串  要求出s[i,j]是不是回文子串
    //      - 若 s[i] == s[j] 
    //          那 最长回文子序列 = s[i+1..j-1] + 2
    //      - 若 s[i] != s[j] 
    //          需要比较出 s[i+1, j] 和 s[i, j - 1] 那个组成的子串 回文序列更长
    // 3.recurrence
    // if (s[i] == s[j]) 它俩一定在最长回文子序列中
    //          dp[i][j] = dp[i + 1][j - 1] + 2;
    // else
    //         dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
    class DpReverse {
        public int longestPalindromeSubseq(String s) {
            int strLen = s.length();
            int[][] dp = new int[strLen][strLen];
            for (int i = 0; i < strLen; i++) {
                dp[i][i] = 1;
            }
            // 反着遍历保证正确的状态转移
            for (int i = strLen - 1; i >= 0; i--) {
                for (int j = i + 1; j < strLen; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[0][strLen - 1];
        }
    }

    class DpDignoal {
        public int longestPalindromeSubseq(String s) {
            int n = s.length();
            int[][] dp = new int[n][n];
            for (int l = 1; l <= n; l++) {
                for (int i = 0; i <= n - l; i++) {
                    int j = i + l - 1;
                    if (i == j) {
                        dp[i][j] = 1;
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    if (s.charAt(i) == s.charAt(j))
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                }
            }
            return dp[0][n - 1];
        }
    }
}
