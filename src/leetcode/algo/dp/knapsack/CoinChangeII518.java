package leetcode.algo.dp.knapsack;

import java.util.HashMap;
import java.util.Map;

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

    // time O(n * coins^2)
    // space O(coins^2)
    class DFS {
        public int change(int amount, int[] coins) {
            Map<String, Integer> cache = new HashMap<>();
            return change(amount, 0, cache, coins);
        }

        public int change(int amount, int start, Map<String, Integer> cache, int[] coins) {
            if (amount < 0) {
                return 0;
            }

            if (amount == 0) {
                return 1;
            }

            String key = amount + "," + start;
            if (cache.containsKey(key)) {
                return cache.get(key);
            }

            int count = 0;
            for (int i = start; i < coins.length; ++i) {
                count += change(amount - coins[i], i, cache, coins);
            }

            cache.put(key, count);
            return count;
        }
    }
}
