package leetcode.algo.dp.twodimension;

import java.util.Arrays;

// https://leetcode-cn.com/problems/allocate-mailboxes/
public class Mailboxes1478 {
    // 假设只有一个box， 那必定是 i～j 中位数 最小
    // 当有k个时候
    // 如果 k - 1 的box 都放在 前 j - 1 个房子里面 j
    // 则 最后一个box 的范围是 j - 1, i 此时范围最大
    // 当最后一个box 只负责 i时， 此时情况最小
    // dp[i][j] 示i个数，j个邮局 最小的distance , rect(k, i) 从 k 到 i用一个邮箱的最小费用
    // dp[i][j] = min(dp[k - 1][j - 1] + rec[k, i], dp[i][j])
    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);

        // 计算一个box 下 每个房子到这个中位数的距离
        int n = houses.length;
        int[][] rec = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // 求中位数
                int mid = (j - i) / 2 + i;
                for (int m = i; m <= j; m++) {
                    // i - j 之间的数字 是 中间每个house 和中位数的相加和
                    rec[i][j] += Math.abs(houses[m] - houses[mid]);
                }
            }
        }

        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 初始值 只有1个邮局的情况下 就是 从0 到i 的最小费用
        for (int i = 0; i < n; i++) {
            dp[i][1] = rec[0][i];
        }

        for (int i = 0; i < n; i++) {
            // j 是邮局
            // 是2 因为1 已被复植
            // 不能超过k 小于 i + 1 的原因是
            for (int j = 2; j <= k && j <= i + 1; j++) {
                for (int m = j - 1; m <= i; m++) {
                    dp[i][j] = Math.min(dp[m - 1][j - 1] + rec[m][i], dp[i][j]);
                }
            }
        }
        return dp[n - 1][k];
    }
}
