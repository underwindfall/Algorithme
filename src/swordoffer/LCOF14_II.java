package swordoffer;

import java.math.BigInteger;
import java.util.Arrays;

// https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/solution/jian-dan-li-jie-dong-tai-gui-hua-xun-hua-4g3o/
public class LCOF14_II {
    // dp[i] dp[i] 表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积
    // dp[i] = max {max(j * (i - j), j * dp[i - j])} // 1 <= j < i
    // time O(n^2)
    // space O(n)
    static int MOD = 1000000007;

    public int cuttingRope(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        Arrays.fill(dp, BigInteger.valueOf(0));
        for (int i = 2; i <= n; i++) {
            BigInteger currMax = BigInteger.valueOf(0);
            for (int j = 1; j < i; j++) {
                currMax = currMax.max(BigInteger.valueOf(j * (i - j)).max(BigInteger.valueOf(j).multiply(dp[i - j])));
            }
            dp[i] = currMax;
        }
        return dp[n].mod(BigInteger.valueOf(MOD)).intValue();
    }

}
