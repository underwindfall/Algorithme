package leetcode.algo.dp.doublesequence.interval;

import java.util.Arrays;

//https://leetcode-cn.com/problems/predict-the-winner/
//跟Leetcode 877基本上一摸一样
public class PredictWinner486 {

    // dp(i, j) 定义为 （i，j） 区间内能够获取的最大净胜分数 他是一切的核心
    // dp(i, j ) = max(nums[i] - dp(i + 1, j), nums[j] - dp(i, j - 1) )
    class DP {
        public boolean PredictTheWinner(int[] nums) {
            int n = nums.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                dp[i][i] = nums[i];
            }

            // 对角线遍历表

            for (int i = n - 2; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
                }
            }
            return dp[0][n - 1] >= 0;
        }
    }

    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        return dfs(nums, 0, n - 1, memo) >= 0;
    }

    // 计算的是绝对差值
    int dfs(int[] piles, int left, int right, int[][] memo) {
        if (left == right) {
            return piles[left];
        }
        if (memo[left][right] != Integer.MIN_VALUE) {
            return memo[left][right];
        }
        int chooseLeft = piles[left] - dfs(piles, left + 1, right, memo);
        int chooseRight = piles[right] - dfs(piles, left, right - 1, memo);

        // max 每次大家都是取相比下一层之差更大的
        int ans = Math.max(chooseLeft, chooseRight);
        memo[left][right] = ans;
        return ans;
    }
}
