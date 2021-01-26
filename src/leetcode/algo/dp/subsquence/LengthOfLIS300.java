package leetcode.algo.dp.subsquence;

// https://leetcode-cn.com/problems/longest-increasing-subsequence/
public class LengthOfLIS300 {
    class DP {
        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = 1;
            int maxAns = 1;
            for (int i = 0; i < nums.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] < nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                maxAns = Math.max(maxAns, dp[i]);
            }
            return maxAns;
        }
    }
}
