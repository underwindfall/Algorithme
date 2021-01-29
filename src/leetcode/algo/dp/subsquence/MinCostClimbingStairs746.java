package leetcode.algo.dp.subsquence;

// https://leetcode-cn.com/problems/min-cost-climbing-stairs/
public class MinCostClimbingStairs746 {
    class Dp {
        public int minCostClimbingStairs(int[] cost) {
            int N = cost.length;
            int[] dp = new int[N + 1];
            dp[0] = 0;
            dp[1] = 0;
            // dp代表着当前上台阶的数量
            // 递进关系是 i 层的台阶 可以是 i - 1 上1层 或者 i - 2 上2层得来的
            // 比较两者间的较小值就是结果
            for (int i = 2; i <= N; i++) {
                dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
            }
            return dp[N];
        }
    }

    class DpRollArray {
        public int minCostClimbingStairs(int[] cost) {
            // 上面的算法致联系到i-1 i-2 的关系，所以可以尝试用滚动数组优化
            int curr = 0, prev = 0;
            int ans = 0;
            for (int i = 2; i <= cost.length; i++) {
                ans = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
                prev = curr;
                curr = ans;
            }
            return ans;
        }
    }
}
