package leetcode.algo.dp.doublesequence;

//https://leetcode-cn.com/problems/longest-palindromic-substring/
public class LongestCommonString5 {

    // P(i, j) = P(i+1,j-1) && S(i) == S(j)
    // 特殊情况 P(i, i) = true
    // 特殊情况 P(i, i+1) = S(i) == S(i+1)
    class Dp {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            int strLen = s.length();
            int maxStart = 0; // 最长回文串的起点
            int maxEnd = 0; // 最长回文串的终点
            int maxLen = 1; // 最长回文串的长度
            boolean[][] dp = new boolean[strLen][strLen];
            for (int r = 1; r < strLen; r++) {
                for (int l = 0; l < r; l++) {
                    if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                        dp[l][r] = true;
                        if (r - l + 1 > maxLen) {
                            maxLen = r - l + 1;
                            maxStart = l;
                            maxEnd = r;
                        }
                    }
                }
            }

            return s.substring(maxStart, maxEnd + 1);

        }
    }

    // time O(N^2)
    // espace O(1)
    class ExpandCenter {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1)
                return "";
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                // 针对奇数个
                int len1 = expandAroundCenter(s, i, i);
                // 针对偶数个
                int len2 = expandAroundCenter(s, i, i + 1);
                int len = Math.max(len1, len2);
                if (len > end - start) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        // 从字符串定义的位置出发向左向右延伸排查
        int expandAroundCenter(String s, int left, int right) {
            int L = left, R = right;
            while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
                L--;
                R++;
            }
            return R - L - 1;
        }
    }
}
