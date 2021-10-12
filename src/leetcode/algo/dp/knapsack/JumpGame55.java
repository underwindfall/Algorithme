package leetcode.algo.dp.knapsack;

// https://leetcode-cn.com/problems/jump-game/
public class JumpGame55 {
    // dp(i) 当前下标（i）数组能够到达的位置
    // time O(n)
    // space o(n)
    class DP {
        public boolean canJump(int[] nums) {
            int n = nums.length;
            if (n == 1)
                return true;
            int[] dp = new int[n];
            dp[0] = nums[0];
            for (int i = 1; i < n; i++) {
                if (dp[i - 1] < i) {// 说明无法到达最后当前位置
                    return false;
                } else if (dp[i - 1] >= n - 1) {// 说明无法到达最后last位置
                    return true;
                } else {
                    dp[i] = Math.max(dp[i - 1], i + nums[i]);
                }
            }
            return false;
        }
    }

    //time O(n)
    //space O(1)
    class Greedy {
        public boolean canJump(int[] nums) {
            int len = nums.length;
            int max = 0;
            for (int i = 0; i <= max; i++) {
                max = Math.max(max, i + nums[i]);
                if (max >= len - 1) return true;
            }
            return false;
        }
    }
}
