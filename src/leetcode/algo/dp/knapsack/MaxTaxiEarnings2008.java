package leetcode.algo.dp.knapsack;

import java.util.Arrays;

// https://leetcode-cn.com/problems/maximum-earnings-from-taxi/solution/
public class MaxTaxiEarnings2008 {
    // time O(n + k logK)
    // space O(n)
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> a[0] - b[0]);
        long[] dp = new long[n + 1];
        int j = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
            while (j < rides.length && rides[j][0] == i) {
                int end = rides[j][1], start = rides[j][0], tip = rides[j][2];
                dp[end] = Math.max(dp[end], dp[i] + end - start + tip);
                j++;
            }
        }
        return dp[n];
    }
}
