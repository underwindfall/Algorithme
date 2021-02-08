package leetcode.algo.dp.maxnumNeigbors;

// https://leetcode-cn.com/problems/delete-and-earn/
public class DeleteAndEarn740 {
    // 转换成打家劫舍问题
    // 简单的想法是 通过转换 将nums 转换成 array 这个array以index代表value，index上的值代表value出现的次数
    // 然后就是打家劫舍问题，因为相同的值不能被取
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
        }

        int[] array = new int[max + 1];
        for (int num : nums) {
            array[num]++;
        }
        return robber(array);
    }

    int robber(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = array[0];
        dp[1] = array[1] * 1;

        for (int i = 2; i < array.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + array[i] * i);
        }
        return dp[array.length - 1];
    }
}
