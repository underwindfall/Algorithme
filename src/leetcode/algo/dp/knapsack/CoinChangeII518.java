package leetcode.algo.dp.knapsack;

//https://leetcode-cn.com/problems/coin-change-2/
public class CoinChangeII518 {
    // dp(i,j)考虑前 i件物品，凑成总和为 j 的方案数量
    // 初始条件 dp(0, i) = 0 dp(0,0) =1
    // 不用第i个硬币 dp(i, j) = dp(i - 1, j)
    // 如使用第i个硬币 dp(i, j) = dp(i - 1, j - coins[i - 1])+ dp(i - 1,  j - 2 * coins[i - 1]) ....+dp(i - 1,  j - n * coins[i - 1])
    //  n * coins[i - 1]  <= j
    // time O(N* coins^2)
    // espace O(N* coins)
    public int change(int amount, int[] coins) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        for (int i = 1; i < amount + 1; i++) {
            dp[0][i] = 0;
        }
        dp[0][0] = 1;

        for (int i = 1; i <= len; i++) {
            int val = coins[i - 1];
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k * val <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * val];
                }
            }
        }

        return dp[len][amount];
    }

}
