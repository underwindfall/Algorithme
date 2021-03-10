package leetcode.algo.dp.state;

// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
public class BuyStockII122 {
    //dp[i][0] 第i天 手上没股票的最大获利数
        //dp[i][0] 递进关系 
                //dp[i - 1][0] 第i - 1天 手上没股票
                //dp[i - 1][1] 第i - 1天 手上有股票 在第i天卖掉了
        //dp[i][1] 第i天 手上有股票的最大获利数
                //dp[i - 1][1] 第i - 1天 手上有股票
                //dp[i - 1][0] 第i - 1天 手上没股票 在第i天买入了
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    class DPOptimization {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int dp0 = 0, dp1 = -prices[0];
            for (int i = 1; i < n; ++i) {
                int newDp0 = Math.max(dp0, dp1 + prices[i]);
                int newDp1 = Math.max(dp1, dp0 - prices[i]);
                dp0 = newDp0;
                dp1 = newDp1;
            }
            return dp0;
        }
    }
}
