package leetcode.algo.dp.state;

// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
public class BuyStock121 {

    class Dp {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int minPrice = prices[0];
            int[] dp = new int[n];
            for (int i = 1; i < n; i++) {
                minPrice = Math.min(minPrice, prices[i]);
                dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
            }
            return dp[n - 1];
        }
    }

    class ForceLoop {
        public int maxProfit(int[] prices) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < prices.length; i++) {
                for (int j = i + 1; j < prices.length; j++) {
                    max = Math.max(max, prices[j] - prices[i]);
                }
            }
            return max;
        }
    }
}
