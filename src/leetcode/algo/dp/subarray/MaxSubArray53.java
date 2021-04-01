package leetcode.algo.dp.subarray;

// https://leetcode-cn.com/problems/maximum-subarray/
public class MaxSubArray53 {

    // 暴力技法
    class Loop {
        public int maxSubArray(int[] nums) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int sum = 0;
                for (int j = i; j < nums.length; j++) {
                    sum += nums[j];
                    max = Math.max(max, sum);
                }
            }
            return max;
        }
    }

    // 思路 只要每个阶段的最大和和当前的数比较， 取两者之间较大的那个就好了。
    class DPOptimizaation {
        public int maxSubArray(int[] nums) {
            int pre = 0, maxAns = nums[0];
            for (int x : nums) {
                pre = Math.max(pre + x, x);
                maxAns = Math.max(maxAns, pre);
            }
            return maxAns;
        }

        // int ans = nums[0];
        // int sum = 0;
        // for(int num: nums) {
        //     if(sum > 0) {
        //         sum += num;
        //     } else {
        //         sum = num;
        //     }
        //     ans = Math.max(ans, sum);
        // }
        // return ans;
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

            // int[] dp = new int[nums.length];
            // dp[0] = nums[0];
            // int max = nums[0];
            // for (int i = 1; i < nums.length; i++) {
            //     dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            //     max = Math.max(max, dp[i]);
            // }
            // return max;
        }
    }
}
