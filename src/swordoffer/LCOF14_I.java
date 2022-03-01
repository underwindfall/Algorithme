package swordoffer;

// https://leetcode-cn.com/problems/jian-sheng-zi-lcof/
public class LCOF14_I {
    // dp[i] dp[i] 表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积
    // dp[i] = max {max(j * (i - j), j * dp[i - j])}                        // 1 <= j < i
    // time O(n^2)
    // space O(n)
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int currMax = 0;
            for (int j = 1; j < i; j++) {
                currMax = Math.max(currMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = currMax;
        }
        return dp[n];
    }
}
