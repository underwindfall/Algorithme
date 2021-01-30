package leetcode.algo.dp.subsquence;

// https://leetcode-cn.com/problems/maximum-subarray/
public class MaxSubArray53 {

    //思路 只要每个阶段的最大和和当前的数比较， 取两者之间较大的那个就好了。
    class DPOptimizaation {
        public int maxSubArray(int[] nums) {
            int pre = 0, maxAns = nums[0];
            for (int x : nums) {
                pre = Math.max(pre + x, x);
                maxAns = Math.max(maxAns, pre);
            }
            return maxAns;
        }
    }

    class DP {
        public int maxSubArray(int[] nums) {
            if (nums.length == 0)
                return 0;
            /*
             * dp[i]定义：存储i索引之前的最大和 初始值：每个dp[i]最小都是当前nums[i]
             */
            int[] dp = new int[nums.length];
            for (int i = 0; i < dp.length; i++) {
                dp[i] = nums[i];
            }
            // res用于找到nums中连续子数组最大和
            int res = dp[0];

            for (int i = 1; i < nums.length; i++) {
                // 状态方程，看到没，状态方程就是for循环内部需要的操作
                dp[i] = Math.max(dp[i], dp[i - 1] + dp[i]);
                res = Math.max(res, dp[i]);
            }
            return res;
        }
    }
}
