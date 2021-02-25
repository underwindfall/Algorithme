package leetcode.algo.dp;

// https://leetcode-cn.com/problems/palindrome-partitioning-ii/
public class MinCut132 {
    // s(0, i) 是回文时候 0 一刀不切
    // s(0, i) 不是回文
    // s (0, j) 是回文， 然后 s(j+1, i) 变成回文
    // dp[i] = min(dp[j] + 1, dp[i])
    public int minCut(String s) {
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i] = i;
            if (isPanlindrome(s.substring(0, i + 1))) {
                dp[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (isPanlindrome(s.substring(j + 1, i + 1))) {
                        dp[i] = Math.min(dp[j] + 1, dp[i]);
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }

    boolean isPanlindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}
