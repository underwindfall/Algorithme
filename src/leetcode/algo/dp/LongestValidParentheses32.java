package leetcode.algo.dp;

import java.util.Stack;

// https://leetcode-cn.com/problems/longest-valid-parentheses/
public class LongestValidParentheses32 {
    // https://leetcode.cn/problems/longest-valid-parentheses/solution/shou-hua-tu-jie-zhan-de-xiang-xi-si-lu-by-hyj8/
    // time O(N)
    //espace O(N)
    class StackSolution {
        public int longestValidParentheses(String s) {
            char[] arr = s.toCharArray();
            Stack<Integer> stack = new Stack<>();
            int max = 0;
            int start = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == '(') {
                    stack.push(i);
                } else {
                    if (stack.isEmpty()) {
                        start = i + 1;
                    } else {
                        stack.pop();
                        if (stack.isEmpty()) {
                            max = Math.max(max, i - start + 1);
                        } else {
                            max = Math.max(max, i - stack.peek());
                        }
                    }
                }
            }
            return max;
        }
    }

    // time O(N)
    // espace O(N)
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
            int ans = 0;
            int[] dp = new int[s.length()];
            for (int i = 1; i < s.length(); i++) {
                // 以 ( 结尾的字符我们并不记录
                if (s.charAt(i) == '(') {
                    dp[i] = 0;
                } else {
                    if (s.charAt(i) == ')') {
                        // i 和 i - 1 匹配成 ()
                        if (s.charAt(i - 1) == '(') {
                            if (i - 2 >= 0) {
                                // 好计算 因为前面将以 ( 结尾的字符记录0
                                dp[i] = dp[i - 2] + 2;
                            } else {
                                dp[i] = 2;
                            }
                        }
                        // 情况特殊 可能是 (.....)) ((.......))
                        else if (s.charAt(i - 1) == ')') {
                            // i 匹配对象就是 i - dp(i - 1) - 1
                            if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                                // ((.......)) 情况
                                // 需要考虑前一位i - dp(i - 1) - 1 - 1 情况
                                // && s.charAt(i - dp[i - 1] - 1 - 1) == '('
                                if (i - dp[i - 1] - 1 - 1 >= 0) {
                                    dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 1 - 1] + 2;
                                } else {
                                    // (.....() 情况
                                    dp[i] = dp[i - 1] + 2;
                                }
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
