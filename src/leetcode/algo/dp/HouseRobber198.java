package leetcode.algo.dp;

// https://leetcode-cn.com/problems/house-robber/
// time: O(N)
// espace: O(N)
class HouseRobber198 {
    public int rob(int[] nums) {
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
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }

    class Improv {
        public int rob(int[] nums) {
            int len = nums.length;
            if (len == 0)
                return 0;
            if (len == 1)
                return nums[0];
            if (len == 2)
                return Math.max(nums[0], nums[1]);

            int pre2 = nums[0];
            int pre1 = Math.max(nums[0], nums[1]);
            int money = 0;

            for (int i = 2; i < len; i++) {
                money = Math.max(pre2 + nums[i], pre1);
                pre2 = pre1;
                pre1 = money;
            }
            return money;
        }
    }
}