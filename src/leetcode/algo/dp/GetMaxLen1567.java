package leetcode.algo.dp;

//https://leetcode-cn.com/problems/maximum-length-of-subarray-with-positive-product/
public class GetMaxLen1567 {
    // time O(N)
    // espace O(N)
    public int getMaxLen(int[] nums) {
        // dp 0->positive num / 1->negative num
        int[][] dp = new int[nums.length + 1][2];
        dp[0][0] = 0;
        dp[0][1] = 0;
        int res = 0;

        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] == 0) {
                dp[i][0] = 0;
                dp[i][1] = 0;
            } else if (nums[i - 1] > 0) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1] != 0 ? (dp[i - 1][1] + 1) : 0;
            } else {
                // dp(i - 1, 1)
                dp[i][0] = dp[i - 1][1] != 0 ? (dp[i - 1][1] + 1) : 0;
                dp[i][1] = dp[i - 1][0] + 1;
            }
            res = Math.max(res, dp[i][0]);
        }

        return res;
    }

    // time O(N)
    // espace O(1)
    class Simulation {
        // even negative number
        // odd negative number exclude 1
        // positive number
        // zero
        public int getMaxLen(int[] nums) {
            // sum is used to count the number of negative numbers from zeroPosition to
            // current index
            int firstNegative = -1, zeroPosition = -1, cntNegative = 0, max = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0) {
                    cntNegative++;
                    // we only need to know index of first negative number
                    if (firstNegative == -1)
                        firstNegative = i;
                }
                // if current number is 0, we can't use any element from index 0 to i anymore,
                // so update zeroPosition, and reset sum and firstNegative. If it is a game, we
                // should refresh the game when we meet 0.
                if (nums[i] == 0) {
                    cntNegative = 0;
                    firstNegative = -1;
                    zeroPosition = i;
                } else {
                    // consider index of zero
                    if (cntNegative % 2 == 0)
                        max = Math.max(i - zeroPosition, max);
                    // consider index of first negative number
                    else
                        max = Math.max(i - firstNegative, max);
                }
            }
            return max;

        }
    }

}
