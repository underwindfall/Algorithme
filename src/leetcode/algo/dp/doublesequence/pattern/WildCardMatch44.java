package leetcode.algo.dp.doublesequence.pattern;

// https://leetcode-cn.com/problems/wildcard-matching/
public class WildCardMatch44 {
    //s raw string
    //p pattern string
    //1. subproblem 针对i的s字符串 是否匹配p的字符串的问题 (:i) (:j) prefixe
    //2. guessing 
    //          i 
    //          j  是否会匹配在j这个位置
    //          i == j  那没有问题 -> match
    //          i != j && j == ? -> match 
    //          i != j && j == * -> match -> i - 1, j || i, j - 1
    //3. recurrsion
    // dp(i, j) 表示的是 s的第i个字符串 p的第j个字符串
    // if (i-1 == j -1) dp(i,j) = dp(i - 1, j - 1)
    // if (i-1 != j -1 && j -1 ==?) dp(i,j) = dp(i - 1, j - 1)
    // if(i-1 != j -1 && j - 1 =*) dp(i,j) = dp(i - 1, j) || dp(i, j - 1)
    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            if(p.charAt(i - 1) !=  '*') {
                break;
            }
            //因为* 是可以匹配空格的
            dp[0][i] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if (p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }        
}
