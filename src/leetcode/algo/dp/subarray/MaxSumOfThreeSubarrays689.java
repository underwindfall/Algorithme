package leetcode.algo.dp.subarray;

// https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
public class MaxSumOfThreeSubarrays689 {

    // time O(n)
    // space O(n)
    class DP {
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int n = nums.length;
            int[] sum = new int[n + 1];
            for (int i = 1; i <= n; ++i)
                sum[i] = sum[i - 1] + nums[i - 1];
            // f[i][j] 表示[0, i)范围内，截取j段互不重叠的子数组的最大和
            int[][] f = new int[n + 1][4];
            for (int i = k; i <= n; ++i) {
                int s = sum[i] - sum[i - k];
                for (int j = 1; j <= 3; ++j) {
                    // 1. 直接从左边截取j段：f[i-1][j]
                    // 2. 当前结尾截取1段，左边截取j-1段：f[i-k][j-1] + s
                    f[i][j] = Math.max(f[i - 1][j], f[i - k][j - 1] + s);
                }
            }
            int[] ans = new int[3];
            int i = n;
            while (f[i - 1][3] >= f[i][3])
                --i;
            ans[2] = i -= k;
            while (f[i - 1][2] >= f[i][2])
                --i;
            ans[1] = i -= k;
            while (f[i - 1][1] >= f[i][1])
                --i;
            ans[0] = i - k;
            return ans;
        }
    }

    // time O(n)
    // space O(1)
    class SlidingWindow {
        public int[] maxSumOfOneSubarray(int[] nums, int k) {
            int[] ans = new int[1];
            int sum1 = 0, maxSum1 = 0;
            for (int i = 0; i < nums.length; ++i) {
                sum1 += nums[i];
                if (i >= k - 1) {
                    if (sum1 > maxSum1) {
                        maxSum1 = sum1;
                        ans[0] = i - k + 1;
                    }
                    sum1 -= nums[i - k + 1];
                }
            }
            return ans;
        }

        public int[] maxSumOfTwoSubarrays(int[] nums, int k) {
            int[] ans = new int[2];
            int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
            int sum2 = 0, maxSum12 = 0;
            for (int i = k; i < nums.length; ++i) {
                sum1 += nums[i - k];
                sum2 += nums[i];
                if (i >= k * 2 - 1) {
                    if (sum1 > maxSum1) {
                        maxSum1 = sum1;
                        maxSum1Idx = i - k * 2 + 1;
                    }
                    if (maxSum1 + sum2 > maxSum12) {
                        maxSum12 = maxSum1 + sum2;
                        ans[0] = maxSum1Idx;
                        ans[1] = i - k + 1;
                    }
                    sum1 -= nums[i - k * 2 + 1];
                    sum2 -= nums[i - k + 1];
                }
            }
            return ans;
        }

        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int[] ans = new int[3];
            int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
            int sum2 = 0, maxSum12 = 0, maxSum12Idx1 = 0, maxSum12Idx2 = 0;
            int sum3 = 0, maxTotal = 0;
            for (int i = k * 2; i < nums.length; ++i) {
                sum1 += nums[i - k * 2];
                sum2 += nums[i - k];
                sum3 += nums[i];
                if (i >= k * 3 - 1) {
                    if (sum1 > maxSum1) {
                        maxSum1 = sum1;
                        maxSum1Idx = i - k * 3 + 1;
                    }
                    if (maxSum1 + sum2 > maxSum12) {
                        maxSum12 = maxSum1 + sum2;
                        maxSum12Idx1 = maxSum1Idx;
                        maxSum12Idx2 = i - k * 2 + 1;
                    }
                    if (maxSum12 + sum3 > maxTotal) {
                        maxTotal = maxSum12 + sum3;
                        ans[0] = maxSum12Idx1;
                        ans[1] = maxSum12Idx2;
                        ans[2] = i - k + 1;
                    }
                    sum1 -= nums[i - k * 3 + 1];
                    sum2 -= nums[i - k * 2 + 1];
                    sum3 -= nums[i - k + 1];
                }
            }
            return ans;
        }
    }
}
