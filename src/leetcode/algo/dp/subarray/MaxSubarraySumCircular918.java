package leetcode.algo.dp.subarray;

// https://leetcode-cn.com/problems/maximum-sum-circular-subarray/
public class MaxSubarraySumCircular918 {
    // 思路
    // 理解题目 A 中含有个可以任意变化A的子数组，该数组的计数方式从头到尾，可循环，但每个元素只能遍历一次
    // case 1 子数组没有循环编辑结束，这个时候求的是他最大子序和
    // case 2 子数组循环，那就是求他中间最小的子序和，因为就算方式 sum - 子序和 就是最大的和
    public int maxSubarraySumCircular(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        if (A.length == 1) {
            return A[0];
        }
        int sum = 0;
        for (int num : A) {
            sum += num;
        }
        int max = maxSubArray(A);
        int min = minSubArray(A);
        return Math.max(max, sum - min);
    }

    int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    int minSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int min = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            dp[i] = Math.min(dp[i - 1] + nums[i], nums[i]);
            min = Math.min(min, dp[i]);
        }
        return min;
    }
}
