package leetcode.algo.dp.state;

// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
public class BuyStockCoolDown309 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2)
            return 0;
        int[][] dp = new int[n][2];
        // O 卖出
        // 1 买入
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        // 第一天没有冷冻期
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(dp[0][1], dp[0][0] - prices[1]);

        for (int i = 2; i < n; i++) {
            // dp 卖出
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // dp 买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
