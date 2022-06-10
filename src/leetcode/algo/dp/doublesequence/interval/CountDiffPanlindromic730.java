package leetcode.algo.dp.doublesequence.interval;

//https://leetcode-cn.com/problems/count-different-palindromic-subsequences/
public class CountDiffPanlindromic730 {
    // https://leetcode.cn/problems/count-different-palindromic-subsequences/solution/tong-ji-butong-by-jiang-hui-4-q5xf/
    // 这里的子问题的分析很巧妙
    // S[i, j] count of different 回文数量
    // dp[i, j] =
    // if s[i] != s[j] : # count("abcd")
    // dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1] # count("abc") + count("bcd")
    // - count("bc")
    // if s[i] == s[j]
    // dp[i + 1][j - 1] * 2 + 2 s[i+1,j-1]中没有字符和s[i]相等 s[i] not in s[i + 1: j - 1]
    // count("acbca")
    // dp[i + 1][j - 1] * 2 + 1 s[i+1,j-1]中有一个字符和s[i]相等 count(s[i + 1: j -1], s[i])
    // == 1 count("bcbcb")
    // dp[i + 1][j - 1] * 2 - dp[l + 1][r - 1] s[i+1,j-1]中有两个及以上字符和s[i]相等 l, r is
    // the first/last pos of s[i] in s[i + 1: j -1] count("a dabcbad a")

    class MemoRecursive {
        int[][] m;
        private static final int kMod = 1000000007;

        public int countPalindromicSubsequences(String S) {
            int n = S.length();
            m = new int[n][n];
            return count(S.toCharArray(), 0, n - 1);
        }

        int count(char[] s, int i, int j) {
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
                if (l > r) {// s[i] not in s[i + 1: j - 1]
                    ans += count(s, i + 1, j - 1) * 2 + 2;
                } else if (l == r) { // count(s[i + 1: j -1], s[i]) == 1
                    ans += count(s, i + 1, j - 1) * 2 + 1;
                } else {// l, r is the first/last pos of s[i] in s[i + 1: j -1]
                    ans += count(s, i + 1, j - 1) * 2 - count(s, l + 1, r - 1);
                }
            } else {
                ans = count(s, i, j - 1) + count(s, i + 1, j) - count(s, i + 1, j - 1);
            }
            m[i][j] = (int) ((ans + kMod) % kMod);
            return m[i][j];
        }
    }

    // time O(n^2)
    // space O(n^2)
    class DP {

        public int countPalindromicSubsequences(String s) {
            int mod = (int) (1e9) + 7;
            int n = s.length();
            int[][] dp = new int[n][n];
            // 一个单字符是一个回文子序列
            for (int i = 0; i < n; i++) {
                dp[i][i] = 1;
            }
            // 从长度为2的子串开始计算
            for (int len = 2; len <= n; len++) {
                // 挨个计算长度为len的子串的回文子序列个数
                for (int i = 0; i + len <= n; i++) {
                    int j = i + len - 1;
                    // 情况(1) 相等
                    if (s.charAt(i) == s.charAt(j)) {
                        int left = i + 1;
                        int right = j - 1;
                        // 找到第一个和s[i]相同的字符
                        while (left <= right && s.charAt(left) != s.charAt(i)) {
                            left++;
                        }
                        // 找到第一个和s[j]相同的字符
                        while (left <= right && s.charAt(right) != s.charAt(j)) {
                            right--;
                        }
                        if (left > right) {
                            // 情况① 没有重复字符
                            dp[i][j] = 2 * dp[i + 1][j - 1] + 2;
                        } else if (left == right) {
                            // 情况② 出现一个重复字符
                            dp[i][j] = 2 * dp[i + 1][j - 1] + 1;
                        } else {
                            // 情况③ 有两个及两个以上
                            dp[i][j] = 2 * dp[i + 1][j - 1] - dp[left + 1][right - 1];
                        }
                    } else {
                        // 情况(2) 不相等
                        dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                    }
                    // 处理超范围结果
                    dp[i][j] = (dp[i][j] >= 0) ? dp[i][j] % mod : dp[i][j] + mod;
                }
            }
            return dp[0][n - 1];
        }
    }
}
