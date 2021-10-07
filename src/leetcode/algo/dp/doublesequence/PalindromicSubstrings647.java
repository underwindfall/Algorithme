package leetcode.algo.dp.doublesequence;

//https://leetcode-cn.com/problems/palindromic-substrings/
public class PalindromicSubstrings647 {
    //  思路跟LongestCommonString5 很像
    // time O(n^2)
    // space O(n^2)
    class Dp {
        public int countSubstrings(String s) {
            // dp
            int strLen = s.length();
            int ans = strLen;
            boolean[][] dp = new boolean[strLen][strLen];

            for (int r = 1; r < strLen; r++) {
                for (int l = 0; l < r; l++) {
                    if (s.charAt(r) == s.charAt(l) && (r - l <= 2 || dp[l + 1][r - 1])) {
                        ans++;
                        dp[l][r] = true;
                    }
                }
            }

            return ans;
        }
    }

    class ExpandCenter {
        //想清楚 left是一直变化的所以只要满足条件的 num++就会别叠加
        public int countSubstrings(String s) {
            int num = 0;
            int n = s.length();

            // 遍历回文中心点
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= 1; j++) {// j=0,中心是一个点，j=1,中心是两个点
                    int l = i;
                    int r = i + j;
                    while (l >= 0 && r < n && s.charAt(l--) == s.charAt(r++)) {
                        num++;
                    }
                }
            }
            return num;
        }
    }
}
