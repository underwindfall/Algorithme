package leetcode.algo.dp.state;

// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/

public class BuyStockCoolDown309 {
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75931/Easiest-JAVA-solution-with-explanations
    // time O(n)
    // space O(1)

    /*
     * buy[i]: Max profit till index i. The series of transaction is ending with a
     * buy.
     * 
     * sell[i]: Max profit till index i. The series of transaction is ending with a
     * sell.
     * 
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2)
            return 0;
        int[] sell = new int[n];
        int[] buy = new int[n];

        buy[0] = -prices[0];
        buy[1] = -Math.min(prices[0], prices[1]);
        sell[1] = Math.max(buy[0] + prices[1], 0);

        for (int i = 2; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
            // 同一支股票 不能如此sell[i] = Math.max(sell[i], sell[i - 2] + prices[i]);
        }
        return sell[n - 1];
    }
}
