package leetcode.algo.dp.doublesequence.interval;

//https://leetcode-cn.com/problems/count-different-palindromic-subsequences/
public class CountDiffPanlindromic730 {
    //这里的子问题的分析很巧妙
    //S[i, j] count of different 回文数量
    // dp[i, j] = 
    //  if s[i] != s[j] :                                                   # count("abcd")
    //      dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1]                  # count("abc") + count("bcd") - count("bc")
    //  if s[i] == s[j]
    //      dp[i + 1][j - 1] * 2 + 2                                        s[i] not in s[i + 1: j - 1]    count("acbca")
    //      dp[i + 1][j - 1] * 2 + 1                                        count(s[i + 1: j -1], s[i]) == 1       count("bcbcb")
    //      dp[i + 1][j - 1] * 2 - dp[l + 1][r - 1]                         l, r is the first/last pos of s[i] in s[i + 1: j -1] count("bcab")                                 

     class MemoRecursive {
        int[][] m;
        private static final int kMod = 1000000007;
        
        public int countPalindromicSubsequences(String S) {
            int n = S.length();
            m = new int[n][n];
            return count(S.toCharArray(), 0, n - 1);
        }

        int count(char[]s , int i, int j) {
            if (i > j)
                return 0;
            if (i == j)
                return 1;
            if (m[i][j] > 1)
                return m[i][j];
            long ans = 0;

            if (s[i] == s[j]) {
                int l = i + 1;
                int r = j - 1;
                while (l <= r && s[l] != s[i])
                    l++;
                while (l <= r && s[r] != s[i])
                    r--;
                if (l > r) {//s[i] not in s[i + 1: j - 1]    
                    ans += count(s, i + 1, j - 1) * 2 + 2;
                } else if ( l == r) { //count(s[i + 1: j -1], s[i]) == 1
                    ans += count(s, i + 1, j - 1) * 2 + 1;
                } else {//l, r is the first/last pos of s[i] in s[i + 1: j -1]
                    ans += count(s, i + 1, j - 1) * 2 - count(s, l + 1, r - 1);
                }
            } else {
                ans = count(s, i, j - 1) + count(s, i + 1, j) - count(s, i + 1, j - 1);
            }
            m[i][j] = (int) ((ans + kMod) % kMod);
            return m[i][j];
        }
    }

    class DP {
        
        private static final int kMod = 1000000007;

        public int countPalindromicSubsequences(String S) {
            int n = S.length();
            long dp[][] = new long[n][n];
            for (int i = 0; i < n; i++) {
                //只有一个字符串的解
                dp[i][i] = 1;
            }

            for (int len = 1; len <= n; len++) {
                for (int i = 0; i < n - len; i++) {
                    int j = i + len;
                    if (S.charAt(i) == S.charAt(j)) {
                        int l = i + 1;
                        int r = j - 1;
                        while (l <= r && S.charAt(l) != S.charAt(i)) ++l;
                        while (l <= r && S.charAt(r) != S.charAt(i)) --r;                    
                        if (l == r) dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                        else if (l > r) dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                        else dp[i][j] = dp[i + 1][j - 1] * 2 - dp[l + 1][r - 1];
                    } else {
                        dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1]; 
                    }
                    dp[i][j] = (long)((dp[i][j] + kMod) % kMod);
                }
            }
            return (int)dp[0][n - 1];
        }
    }
}
