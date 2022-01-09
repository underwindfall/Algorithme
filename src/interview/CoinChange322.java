package interview;

import java.util.Arrays;

//https://leetcode-cn.com/problems/coin-change/
public class CoinChange322 {
    // 一开始我的想法是尽可能选用大的coin就能使零钱总数量最小
    // greedy是错误的
    /**
     * 例子比如
     * coins：[1, 4, 5]
     * amount：28
     * 
     * 贪心来计算，结果应该是28 = 5 + 5 + 5 + 5 + 5 + 1 + 1 + 1，8枚
     * 
     * 最小数应该是28 = 5 + 5 + 5 + 5 + 4 + 4，6枚
     * 
     * 
     */
    class DFSGreedy {
        int minCount = Integer.MAX_VALUE;

        public int coinChange(int[] coins, int amount) {
            Arrays.sort(coins);
            int[] memo = new int[amount];
            dfs(coins, amount, 0, coins.length - 1, memo);
            return minCount == Integer.MAX_VALUE ? -1 : minCount;
        }

        // 按金额从大到小，从多到少（排序，用余数一步到位）
        // 预判低于最优解，终止递归（可以返回最优解，不过提升有限，意义不大）
        // 能整除即可返回
        void dfs(int[] coins, int amount, int count, int index, int[] memo) {
            if (index < 0 || count + amount / coins[index] >= minCount)
                return;

            if (memo[amount] != 0) {
                minCount = Math.min(minCount, memo[amount]);
                return;
            }
            if (amount % coins[index] == 0) {
                minCount = Math.min(minCount, count + amount / coins[index]);
                memo[amount] = minCount;
                return;
            }
            for (int i = amount / coins[index]; i >= 0; i--) {
                dfs(coins, amount - i * coins[index], count + i, index - 1, memo);
            }
        }
    }

    // top down
    // time O(s*n)
    // space O(s)
    class DFS {
        public int coinChange(int[] coins, int amount) {
            int[] memo = new int[amount + 1];
            return dfs(coins, amount, memo);
        }

        int dfs(int[] coins, int resMount, int[] memo) {
            // base case , resMount < 0, 说明amount不足以在凑齐coins
            if (resMount < 0) {
                return -1;
            }
            // 零钱0返回0个硬币
            if (resMount == 0) {
                return 0;
            }
            if (memo[resMount] != 0) {
                return memo[resMount];
            }
            // 需要保存最小硬币方案，res的范围可取（最大硬币可能，MAX_VALUE)。此题是1元
            // 所以res可取(amount+1,Integer.MAX_VALUE）
            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                // (3.1)DFS传递减去该枚硬币的金额amount - coin，并返回兑换的硬币数
                int count = dfs(coins, resMount - coin, memo);
                // 当amount-coin即不足以减去某面额硬币时触发basecase返回-1，直接跳过该种硬币可能性
                if (count == -1) {
                    continue;
                }
                res = Math.min(res, 1 + count);
            }
            memo[resMount] = res != Integer.MAX_VALUE ? res : -1;
            return memo[resMount];
        }

    }

    // dp(i, j) minCoins to make up j amount using first i types of coins
    // dp(-1, 0) = 0 dp(-1, j) = inf
    // dp(i,j) = min(dp(i, j), dp(i - 1, j - coin_i) + 1)
    // time O(s * n)
    // space O(s)
    class DP {
        public int coinChange(int[] coins, int amount) {
            if (amount == 0)
                return 0;
            // 初始化DP table，表示的凑成总金额为n所需的最少的硬币个数
            int[] dp = new int[amount + 1];
            dp[0] = 0;
            // (2)遍历每种【状态】即金额，【选择】即保存最少硬币数量
            for (int i = 1; i <= amount; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < coins.length; j++) {
                    if (i - coins[j] >= 0 && dp[i - coins[j]] < min) {
                        min = dp[i - coins[j]] + 1;
                    }
                }
                dp[i] = min;
            }
            return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        }
    }
}
