package leetcode.algo.dp;

// https://leetcode-cn.com/problems/longest-valid-parentheses/
public class LongestValidParentheses32 {
    class DP {
        // dp[i] 会面领的几种情况
        // if s[i] == '(' :
        //      dp[i] = 0
        // if s[i] == ')' :
        //      if s[i - 1] == '(' :
        //      dp[i] = dp[i - 2] + 2 #要保证i - 2 >= 0
        //      if s[i - 1] == ')' and s[i - dp[i - 1] - 1] == '(' :
        //      dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2 #要保证i - dp[i - 1] - 2 >= 0
        public int longestValidParentheses(String s) {
            int size = s.length();
            char[] array = s.toCharArray();
            int dp[] = new int[size];
            int ans = 0;
            for (int i = 1; i < size; i++) {
                if (array[i] == '(') {
                    dp[i] = 0;
                } else if (array[i] == ')') {
                    if (array[i - 1] == '(') {
                        if (i - 2 >= 0) {
                            dp[i] = dp[i - 2] + 2;
                        } else {
                            dp[i] = 2;
                        }
                    } else if (array[i - 1] == ')') {
                        if (i - dp[i - 1] - 1 >= 0 && array[i - dp[i - 1] - 1] == '(') {
                            if (i - dp[i - 1] - 2 >= 0) {
                                dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                            } else {
                                dp[i] = dp[i - 1] + 2;
                            }
                        }
                    }
                }
                ans = Math.max(ans, dp[i]);
            }
            return ans;
        }
    }
}
