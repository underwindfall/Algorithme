package leetcode.algo.dp.maxnumNeigbors;

import java.util.Arrays;

// https://leetcode-cn.com/problems/house-robber-ii/
public class HouseRobberïï213 {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        if (len == 1)
            return nums[0];
        if (len == 2)
            return Math.max(nums[0], nums[1]);
        // 分两部分进行讨论 0～len-1 和1～len
        return Math.max(rob(nums, 0, len - 1), rob(nums, 1, len));
    }

    private int rob(int[] nums, int start, int end) {
        // int sumN = nums[start];
        // int sumNPlusOne = Math.max(nums[start], nums[start + 1]);
        // int money = sumN;
        // for (int i = start + 2; i < end; i++) {
        // money = Math.max(sumNPlusOne, sumN + nums[i]);
        // sumN = sumNPlusOne;
        // sumNPlusOne = money;
        // }
        // return money;

        int len = nums.length;
        if (len == 0)
            return 0;
        if (len == 1)
            return nums[0];
        if (len == 2)
            return Math.max(nums[0], nums[1]);
        int dp[] = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = start + 2; i < end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }

    class Solution {
        public int rob(int[] nums) {
            int len = nums.length;
            if (len == 0)
                return 0;
            if (len == 1)
                return nums[0];
            if (len == 2)
                return Math.max(nums[0], nums[1]);
            // 分两部分进行讨论 0～len-1 和1～len
            return Math.max(rob198(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                    rob198(Arrays.copyOfRange(nums, 1, nums.length)));
        }

        private int rob198(int[] nums) {
            int len = nums.length;
            int dp[] = new int[len];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < len; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[len - 1];
        }
    }
}
