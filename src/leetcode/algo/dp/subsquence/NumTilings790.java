package leetcode.algo.dp.subsquence;

// https://leetcode-cn.com/problems/domino-and-tromino-tiling/
public class NumTilings790 {
    // 这个题目的的目的很明确
    // 做法很烂
    // 自己的做法是通过找规律的方式求解
    //详细方式如下
    //https://raw.githubusercontent.com/underwindfall/blogAssets/master/algo/dp/image.png
    // f(n) = 2 * f(n - 1) + f(n - 3)
    public int numTilings(int N) {
        int mod = 1000000007;
        // 这个规律是归纳法推出的不知道是否正确
        // dp(n) = 2*dp(n-1) + dp(n - 3)
        int[] dp = new int[N + 3];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for (int i = 4; i <= N; i++) {
            dp[i] = (2 * (dp[i - 1] % mod) % mod + dp[i - 3] % mod) % mod;
        }
        return dp[N] % mod;
    }
}
