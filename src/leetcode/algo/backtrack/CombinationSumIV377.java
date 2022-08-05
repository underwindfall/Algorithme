package leetcode.algo.backtrack;

// https://leetcode.cn/problems/combination-sum-iv/
public class CombinationSumIV377 {
    // time O(n^2*M)
    // space O(NM)
    public int combinationSum4(int[] nums, int target) {
        // 定义 f[i][j] 为组合长度为 i，凑成总和为 j 的方案数是多少。
        // dp(i)(j) = sum(dp(i - 1)(j - nums[0]) + ..................dp(i - 1)(j -
        // nums[n]) )

        // 因为 nums[i] 最小值为 1，因此构成答案的最大长度为 target
        int len = target;
        int ans = 0;
        int[][] dp = new int[len + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= target; j++) {
                for (int num : nums) {
                    if (j >= num) {
                        dp[i][j] += dp[i - 1][j - num];
                    }
                }
            }

            ans += dp[i][target];
        }
        return ans;
    }
}
