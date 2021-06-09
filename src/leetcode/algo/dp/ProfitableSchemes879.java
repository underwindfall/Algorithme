package leetcode.algo.dp;

//https://leetcode-cn.com/problems/profitable-schemes/
public class ProfitableSchemes879 {

    // time O(M*N*min)
    // espace O(M*N*min)
    // n memebers
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        // i, j, k
        // i -> 抢劫 次数
        // j -> 抢劫 团伙人数
        // k -> 获利钱数
        // f[i][j][k] 为考虑前 i 件物品，使用人数不超过 j，所得利润至少为 k 的方案数。
        int MOD = (int) 1e9 + 7;
        int groupLen = group.length;
        long[][][] dp = new long[groupLen + 1][n + 1][minProfit + 1];
        // 不抢劫任何物品，所得利润为0， 同时满足人数限制
        for (int i = 0; i <= n; i++) {
            dp[0][i][0] = 1;
        }
        for (int i = 1; i <= groupLen; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    //不去抢劫第i个商品的情况下
                    dp[i][j][k] = dp[i - 1][j][k];
                    //去抢劫第i个商品的情况下
                    if (j >= members) {
                        int realProfit = Math.max(0, k - earn);
                        dp[i][j][k] = dp[i][j][k]  + dp[i - 1][j - members][realProfit] ;
                        dp[i][j][k] %=MOD;
                    }
                }
            }
        }

        return (int)dp[groupLen][n][minProfit];
    }
}
