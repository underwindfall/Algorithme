package leetcode.algo.dp.doublesequence;

//https://leetcode-cn.com/problems/palindromic-substrings/
public class PalindromicSubstrings647 {
    //  思路跟LongestCommonString5 很像
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

    
}
