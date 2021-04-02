package leetcode.algo.dp.subarray;

// https://leetcode-cn.com/problems/maximum-product-subarray/
public class MaximumProductSubArray152 {

    class NegativePositveDp {
        public int maxProduct(int[] nums) {
            int max = Integer.MIN_VALUE, imax = 1, imin = 1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0) {
                    int tmp = imax;
                    imax = imin;
                    imin = tmp;
                }
                imax = Math.max(imax * nums[i], nums[i]);
                imin = Math.min(imin * nums[i], nums[i]);

                max = Math.max(max, imax);
            }
            return max;
        }
    }

    class DP {

        public int maxProduct(int[] nums) {
            // 因为存在负的值
            // 所以两者的实际关系
            // 当当前数是负数，那就让这个数字*min最小的
            // 当当前数是正数，那就让这个数字*max最大的
            // maxDP[i + 1] = max(maxDP[i] * A[i + 1], A[i + 1],minDP[i] * A[i + 1])
            // minDP[i + 1] = min(minDP[i] * A[i + 1], A[i + 1],maxDP[i] * A[i + 1])
            // dp[i + 1] = max(dp[i], maxDP[i + 1])
            int length = nums.length;
            int[] maxF = new int[length];
            int[] minF = new int[length];
            maxF[0] = nums[0];
            minF[0] = nums[0];
            for (int i = 1; i < length; ++i) {
                maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
                minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
            }
            int ans = maxF[0];
            for (int i = 1; i < length; ++i) {
                ans = Math.max(ans, maxF[i]);
            }
            return ans;
        }
    }

    class Loop {
        public int maxProduct(int[] nums) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int res = nums[i];
                int maxRes = res;
                for (int j = i + 1; j < nums.length; j++) {
                    res = res * nums[j];
                    maxRes = Math.max(maxRes, res);
                }
                max = Math.max(max, maxRes);
            }
            return max;
        }
    }

}
