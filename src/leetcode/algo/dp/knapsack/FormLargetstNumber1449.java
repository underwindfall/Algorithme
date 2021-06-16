package leetcode.algo.dp.knapsack;

import java.util.Arrays;

//https://leetcode-cn.com/problems/form-largest-integer-with-digits-that-add-up-to-target/
public class FormLargetstNumber1449 {
    // dp(target) := largest number to print with cost == target.
    // dp(target) = max(dp(target – d) + cost[d])
    // time O(N^2)
    // espace O(N^2)
    class DP {
        public String largestNumber(int[] cost, int target) {
            String[] dp = new String[target + 1];
            Arrays.fill(dp, "");
            for (int i = 1; i <= target; i++) {
                dp[i] = "0";
                for (int j = 0; j < cost.length; j++) {
                    if (cost[j] <= i) {
                        String ret = dp[i - cost[j]];
                        if (!ret.equals("0")) {
                            ret = (j + 1) + ret;
                            if (compare(ret, dp[i]) > 0) {
                                dp[i] = ret;
                            }
                        }
                    }
                }
            }
            return dp[target];
        }

        int compare(String s1, String s2) {
            return s1.length() == s2.length() ? s1.compareTo(s2) : s1.length() - s2.length();
        }
    }

    // time O(N^2)
    // espace O(N^2)
    // target-cost[i] 和 res 组成更大的数组
    class MemoRecursive {
        public String largestNumber(int[] cost, int target) {
            String[] memo = new String[target + 1];
            Arrays.fill(memo, "");
            return dfs(cost, target, memo);
        }

        String dfs(int[] cost, int target, String[] memo) {
            if (!memo[target].equals("")) {
                return memo[target];
            }
            if (target == 0) {
                return "";
            }
            memo[target] = "0";
            for (int i = 0; i < cost.length; i++) {
                if (cost[i] <= target) {
                    String ret = dfs(cost, target - cost[i], memo);
                    if (!ret.equals("0")) {
                        // i 才是真正的拼接数字
                        ret = (i + 1) + ret;
                    }
                    if (compare(ret, memo[target]) > 0) {
                        memo[target] = ret;
                    }
                }
            }
            return memo[target];
        }

        int compare(String s1, String s2) {
            return s1.length() == s2.length() ? s1.compareTo(s2) : s1.length() - s2.length();
        }
    }
}
