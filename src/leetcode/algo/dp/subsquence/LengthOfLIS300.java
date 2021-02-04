package leetcode.algo.dp.subsquence;

// https://leetcode-cn.com/problems/longest-increasing-subsequence/
public class LengthOfLIS300 {
    class DP {
        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            dp[0] = 1;
            int maxAns = 1;
            for (int i = 0; i < nums.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                maxAns = Math.max(maxAns, dp[i]);
            }
            return maxAns;
        }
    }

    //重复计算过多所以不适合
    class Recursive {
        public int lengthOfLIS(int[] nums) {
            if (nums == null) {
                return 0;
            }
            return dfs(-1, 0, nums);
        }

        private int dfs(int pre, int cur, int[] nums) {
            if (cur == nums.length) {
                return 0;
            }
            int a = 0;
            int b = 0;
            // pre小于0是初始状态，继续往后判断
            // if条件满足说明是上升子序列，长度要+1
            if (pre < 0 || nums[pre] < nums[cur]) {
                a = dfs(cur, cur + 1, nums) + 1;
            }
            // 如果不满足可能是不满足上升子序列条件
            // 也可能是 满足条件但主动放弃
            b = dfs(pre, cur + 1, nums);
            return Math.max(a, b);
        }

    }
}
