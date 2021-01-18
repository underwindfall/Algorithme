package leetcode.algo.binarysearch;

import java.util.Arrays;

// https://leetcode-cn.com/problems/split-array-largest-sum/
public class SplitArray410 {
    class DP {
        public int splitArray(int[] nums, int m) {
            int n = nums.length;
            int[] sum = new int[n];
            int[][] mem = new int[n][m + 1];
            Arrays.stream(mem).forEach(a -> Arrays.fill(a, Integer.MAX_VALUE));
            sum[0] = nums[0];
            for (int i = 1; i < n; i++) {
                sum[i] = sum[i - 1] + nums[i];
            }
            return splitArray(mem, sum, nums, n - 1, m);
        }

        int splitArray(int[][] mem, int[] sum, int[] nums, int k, int m) {
            if (m == 1)
                return sum[k];
            if (m > k + 1)
                return Integer.MAX_VALUE;
            if (mem[k][m] != Integer.MAX_VALUE)
                return mem[k][m];
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                ans = Math.min(ans, Math.max(splitArray(mem, sum, nums, i, m - 1), sum[k] - sum[i]));
            }
            mem[k][m] = ans;
            return ans;
        }
    }

    class DPOptimsation {
        public int splitArray(int[] nums, int m) {
            int len = nums.length;
            // 前缀和，preSum[i] = sum[0..i)
            int[] preSum = new int[len + 1];
            preSum[0] = 0;
            for (int i = 0; i < len; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }

            // 区间 [i..j] 的和 preSum(j + 1) - preSum(i)
            int[][] dp = new int[len][m + 1];
            // 初始化：由于要找最小值，初值赋值成为一个不可能达到的很大的值
            for (int i = 0; i < len; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            // 分割数为 1 ，即不分割的情况，所有的前缀和就是依次的状态值
            for (int i = 0; i < len; i++) {
                dp[i][1] = preSum[i + 1];
            }

            // 从分割数为 2 开始递推
            for (int k = 2; k <= m; k++) {
                // 还未计算出的 i 是从 j 的最小值的下一位开始，因此是 k - 1
                for (int i = k - 1; i < len; i++) {
                    // j 表示第 k - 1 个区间的最后一个元素额下标，最小值为 k - 2，最大值为 len - 2（最后一个区间至少有 1 个元素）
                    for (int j = k - 2; j < i; j++) {
                        dp[i][k] = Math.min(dp[i][k], Math.max(dp[j][k - 1], preSum[i + 1] - preSum[j + 1]));
                    }
                }
            }
            return dp[len - 1][m];
        }

    }

    public class Binarysearch {

        public int splitArray(int[] nums, int m) {
            int max = 0;
            int sum = 0;

            // 计算「子数组各自的和的最大值」的上下界
            for (int num : nums) {
                max = Math.max(max, num);
                sum += num;
            }

            // 使用「二分查找」确定一个恰当的「子数组各自的和的最大值」，
            // 使得它对应的「子数组的分割数」恰好等于 m
            int left = max;
            int right = sum;
            while (left < right) {
                int mid = left + (right - left) / 2;

                int splits = split(nums, mid);
                if (splits > m) {
                    // 如果分割数太多，说明「子数组各自的和的最大值」太小，此时需要将「子数组各自的和的最大值」调大
                    // 下一轮搜索的区间是 [mid + 1, right]
                    left = mid + 1;
                } else {
                    // 下一轮搜索的区间是上一轮的反面区间 [left, mid]
                    right = mid;
                }
            }
            return left;
        }

        /***
         *
         * @param nums           原始数组
         * @param maxIntervalSum 子数组各自的和的最大值
         * @return 满足不超过「子数组各自的和的最大值」的分割数
         */
        private int split(int[] nums, int maxIntervalSum) {
            // 至少是一个分割
            int splits = 1;
            // 当前区间的和
            int curIntervalSum = 0;
            for (int num : nums) {
                // 尝试加上当前遍历的这个数，如果加上去超过了「子数组各自的和的最大值」，就不加这个数，另起炉灶
                if (curIntervalSum + num > maxIntervalSum) {
                    curIntervalSum = 0;
                    splits++;
                }
                curIntervalSum += num;
            }
            return splits;
        }
    }

}
