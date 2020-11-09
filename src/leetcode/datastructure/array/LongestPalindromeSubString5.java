package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/longest-palindromic-substring/
public class LongestPalindromeSubString5 {
    // 动态规划
    // 如果P(i,j)存在回文串情况是
    // String的开始和结尾是同样的字符 + 其中出去开头结尾的子字节是也是回文串
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j + 1 < n; j++) {
                int k = j + 1;
                if (i == 0) {
                    dp[j][k] = true;
                } else if (i == 1) {
                    dp[j][k] = (s.charAt(j) == s.charAt(k));
                } else {
                    dp[j][k] = (s.charAt(j) == s.charAt(k)) && dp[j + 1][k - 1];
                }

                if (dp[j][k] && i + 1 > ans.length()) {
                    ans = s.substring(j, j + 1 + i);
                }
            }
        }
        return ans;
    }

    public String longestExpandCenterPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }
}
