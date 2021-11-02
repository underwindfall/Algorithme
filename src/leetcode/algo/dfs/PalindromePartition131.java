package leetcode.algo.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

// https://leetcode-cn.com/problems/palindrome-partitioning/
public class PalindromePartition131 {
    //time O(2^n * n) 
    //space O(2^n * n) 
    class DFSDP {

        public List<List<String>> partition(String s) {
            int len = s.length();
            List<List<String>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }

            char[] charArray = s.toCharArray();
            // 预处理
            // 状态：dp[i][j] 表示 s[i][j] 是否是回文
            boolean[][] dp = new boolean[len][len];
            // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
            for (int right = 0; right < len; right++) {
                // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
                for (int left = 0; left <= right; left++) {
                    if (charArray[left] == charArray[right] && (right - left <= 2 || dp[left + 1][right - 1])) {
                        dp[left][right] = true;
                    }
                }
            }

            Deque<String> stack = new ArrayDeque<>();
            dfs(s, 0, len, dp, stack, res);
            return res;
        }

        private void dfs(String s, int index, int len, boolean[][] dp, Deque<String> path, List<List<String>> res) {
            if (index == len) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = index; i < len; i++) {
                if (dp[index][i]) {
                    path.addLast(s.substring(index, i + 1));
                    dfs(s, i + 1, len, dp, path, res);
                    path.removeLast();
                }
            }
        }

    }

    // time O(n * 2^n)
    // space O(n * 2^n)
    class DFS {
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            Stack<String> stack = new Stack<>();
            dfs(s, 0, s.length(), stack, res);
            return res;
        }

        void dfs(String s, int index, int len, Stack<String> path, List<List<String>> res) {
            if (index == len) {
                res.add(new ArrayList<>(path));
            }
            for (int i = index; i < len; i++) {
                if (!checkPalindrome(s, index, i)) {
                    continue;
                }
                String input = s.substring(index, i + 1);
                path.push(input);
                dfs(s, i + 1, len, path, res);
                path.pop();
            }
        }

        boolean checkPalindrome(String s, int left, int right) {
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }
}
