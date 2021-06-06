package leetcode.algo.dp.prefix;

//// https://leetcode-cn.com/problems/target-sum/
public class FindTargetSumWays494 {
    // time O(N*SUM)
    // espace O(N*SUM)
    // prefix sum理论
    // 其实就是
    // DP 问题首先找到公式
    // 这个可以解释为 i 是当前的在数组重的index j是sum值 dp[i][j]是当前节点的target 为j的解

    // dp(i,j) 代表 前i个数，组成和sum为 j的个数解
    //   ...........0.......0...
    //   ............\...../....
    //   ...............1......
    // dp(i + 1, j + 1) = dp(i, j - nums[i]) + dp(i, j + nums[i])
    // https://leetcode-cn.com/problems/target-sum/solution/dong-tai-gui-hua-si-kao-quan-guo-cheng-by-keepal/
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (final int num : nums)
            sum += num;
        if (sum < S)
            return 0;
        final int kOffset = sum;
        final int kMaxN = sum * 2 + 1;
        int[][] ways = new int[nums.length + 1][sum + kOffset + 1];
        // qian0 个元素 构成0的个数
        ways[0][kOffset] = 1;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = nums[i]; j < kMaxN - nums[i]; j++) {
                if (ways[i][j] != 0) {
                    ways[i + 1][j + nums[i]] += ways[i][j];
                    ways[i + 1][j - nums[i]] += ways[i][j];
                }
            }
        }
        return ways[nums.length - 1][sum + S];
    }
}
