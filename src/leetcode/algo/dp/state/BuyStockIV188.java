package leetcode.algo.dp.state;

import java.util.Arrays;

// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
public class BuyStockIV188 {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        // 每天只能操作一次
        k = Math.min(k, n / 2);
        // buy 第i天进行了j次交易+ j次交易是买入
        int[][] buy = new int[n][k + 1];
        // sell 第i天进行了j次交易 + j次交易是卖出
        int[][] sell = new int[n][k + 1];

        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        for (int i = 0; i <= k; i++) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < n; i++) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; j++) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }

    class DP {
        public int maxProfit(int k, int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int n = prices.length;

            // 定义三维数组，第i天、交易了多少次、当前的买卖状态
            int[][][] dp = new int[n][k + 1][2];
            // 初始化第一天，这里的dp[0][k][1]可以不用管，后面也不会用到
            for (int i = 0; i <= k; ++i) {
                dp[0][i][0] = 0; // status 0 卖出
                dp[0][i][1] = -prices[0];// status 1 买入
            }
            for (int i = 1; i < n; ++i) {
                for (int j = 1; j <= k; ++j) {
                    // 处理第k次买入
                    dp[i][j - 1][1] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j - 1][0] - prices[i]);
                    // 处理第k次卖出
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i]);
                }
            }
            return dp[n - 1][k][0];
        }
    }
}
