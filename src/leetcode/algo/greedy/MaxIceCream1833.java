package leetcode.algo.greedy;

import java.util.Arrays;

//https://leetcode-cn.com/problems/maximum-ice-cream-bars/
public class MaxIceCream1833 {
    // dp 超时
    class DP {
        // dp(i, j) i 第 i 只雪糕 还剩j 块钱

        public int maxIceCream(int[] costs, int coins) {
            int[][] dp = new int[costs.length][coins + 1];
            for (int i = 1; i < coins + 1; i++) {
                if (i >= costs[0])
                    dp[0][i] = 1;
            }
            for (int i = 1; i < costs.length; i++) {
                for (int j = 1; j <= coins; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= costs[i])
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - costs[i]] + 1);
                }
            }
            return dp[costs.length - 1][coins];

        }
    }

    // 贪心
    // 如果用coins 的钱 买costs 中k 个雪糕 ，将其中一只雪糕代替，则超过
    // 假设购买最便宜的雪糕，在总价格不超过 coins 的情况下最多可以购买 k 支雪糕。如果将 k
    // 支最便宜的雪糕中的任意一支雪糕替换成另一支雪糕，则替换后的雪糕的价格大于或等于替换前的雪糕的价格，因此替换后的总价格大于或等于替换前的总价格，允许购买的雪糕数量不可能超过
    // k。因此可以买到的雪糕的最大数量为 k.
    // time O(N * logN)
    // espace O(logN)
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int amount = 0;
        for (int cost : costs) {
            if (coins >= cost) {
                coins -= cost;
                amount++;
            } else {
                break;
            }
        }
        return amount;
    }

}
