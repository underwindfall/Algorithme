package leetcode.algo.dp.subsquence;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode-cn.com/problems/minimum-deletions-to-make-string-balanced/
public class MinimumDeletions1653 {
    // time O(N)
    // espace O(N)
    // 要找字符串中a的最后一个A 之前有多少b都要被弄出来
    class StackSolution {
        public int minimumDeletions(String s) {
            Deque<Character> char_stack = new ArrayDeque<>();

            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'b') {
                    char_stack.addLast(s.charAt(i));
                } else {
                    if (!char_stack.isEmpty()) {
                        cnt++;
                        char_stack.removeLast();
                    }
                }
            }
            return cnt;
        }
    }

    // time O(n)
    // espace O(1)
    class DP {
        // dp(i) s[0:i] 中保持字符串平衡的最少删除次数
        // 1. s[i] == b s[0:i−1]是平衡的，s[0:i]就是平衡 dp(i) = dp(i - 1)
        // 2. s[i] == a
        // 2.1 保留s[i] 删除s[0:i−1]所有的b s[0: i - 1]      prev chars must be a...a
        // 2.2 删除s[i] dp(i) = dp(i - 1) +1     prev chars must be a...ab...b
        public int minimumDeletions(String s) {
            int n = s.length(), count_b = 0;
            int[] dp = new int[n + 1];

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == 'b') {
                    dp[i + 1] = dp[i];
                    count_b++;
                } else {
                    dp[i + 1] = Math.min(dp[i] + 1, count_b);
                }
            }
            return dp[n];
        }
    }
}
