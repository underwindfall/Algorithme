package leetcode.algo.dp.subsquence;

import java.util.Arrays;

// https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/
//TODO 线段树写法
public class LongestIncreasingCount673 {
    //思路首先和300 是一致的
    //dp用来代表当前的最大的递增数列的的长度，
    class DP {
        public int findNumberOfLIS(int[] nums) {
            if (nums.length <= 1) {
                return nums.length;
            }
            int[] dp = new int[nums.length];
            int[] count = new int[nums.length];
            int maxCount = 0;
            Arrays.fill(dp, 1);
            Arrays.fill(count, 1);
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        // 生成了新的递增数列,并且i作为末尾底层，但是之前有小部分子问题的递增数列
                        if (dp[j] + 1 > dp[i]) {
                            dp[i] = dp[j] + 1;
                            count[i] = count[j];
                        } else if (dp[j] + 1 == dp[i]) {
                            count[i] += count[j];
                        }
                    }
                    if (dp[i] > maxCount) {
                        maxCount = dp[i];
                    }
                }
            }

            int result = 0;
            for (int i = 0; i < nums.length; i++) {
                if (maxCount == dp[i])
                    result += count[i];
            }
            return result;
        }
    }
}