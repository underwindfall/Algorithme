package leetcode.algo.dp.doublesequence.interval;

import java.util.Arrays;

//https://leetcode-cn.com/problems/minimum-cost-to-merge-stones/
//https://leetcode-cn.com/problems/minimum-cost-to-merge-stones/solution/yi-dong-you-yi-dao-nan-yi-bu-bu-shuo-ming-si-lu-he/
//跟burst ballon 312有关
public class MergeStones1000 {

    class DP {
        public int mergeStones(int[] stones, int k) {
            int n = stones.length;
            if ((n - 1) % (k - 1) != 0)
                return -1;
            int[][] dp = new int[n + 1][n + 1];
            int[] sum = new int[n + 1];
            for (int i = 1; i <= n; ++i)
                sum[i] = sum[i - 1] + stones[i - 1];
            for (int len = k; len <= n; ++len) { // 枚举区间长度
                for (int i = 1; i + len - 1 <= n; ++i) { // 枚举区间起点
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int p = i; p < j; p += k - 1) { // 枚举分界点
                        dp[i][j] = Math.min(dp[i][j], dp[i][p] + dp[p + 1][j]);
                    }
                    if ((j - i) % (k - 1) == 0)
                        dp[i][j] += sum[j] - sum[i - 1];
                }
            }
            return dp[1][n];
        }

        // 链接：https://leetcode-cn.com/problems/minimum-cost-to-merge-stones/solution/yi-dong-you-yi-dao-nan-yi-bu-bu-shuo-ming-si-lu-he/

    }

    class DP3 {
        public int mergeStones(int[] stones, int K) {
            int n = stones.length;
            if (((n - 1) % (K - 1)) != 0)
                return -1;

            int[] prefixSum = new int[n + 1];
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + stones[i];
            }

            // dp(i, j, k) （i，j）中 分成k堆的最小成本
            int[][][] dp = new int[n][n][K + 1]; // 0~K K+1
            for (int[][] row : dp) {
                for (int[] col : row) {
                    Arrays.fill(col, Integer.MAX_VALUE);
                }
            }

            for (int i = 0; i < n; i++) {
                dp[i][i][1] = 0; // i ,i 分成一堆的最小成本是0
            }

            for (int l = 2; l <= n; l++) { // subproblem length
                for (int i = 0; i <= n - l; i++) {// start.
                    int j = i + l - 1; // end
                    for (int k = 2; k <= K; k++) { // 分成几堆 -> 子问题一直在继续
                        for (int m = i; m < j; m = m + K - 1) { // m += K - 1 是一个最小区间 否则分出的(i,m) (m+1,j) 无法组成另外的区间
                            // 第三点：枚举分界点时，step应该是k - 1而不是1。
                            // step为1当然也是正确的，但是却进行了很多无用的计算 导致运行时间增加。为什么step可以是k-1呢？因为我们设计的划分是将左部分区间[i,
                            // p]合并为1堆，那就一定有(p - i) % (k - 1) == 0
                            dp[i][j][k] = Math.min(dp[i][j][k], dp[i][m][1] + dp[m + 1][j][k - 1]); // k - 1 + 1 = k ==
                                                                                                    // K
                        }
                    }
                    // dp(i, j, 1) = dp(i, j, k) k个小区间同时会被合并成一个区间k k == K
                    dp[i][j][1] = dp[i][j][K] + prefixSum[j + 1] - prefixSum[i];
                }
            }
            return dp[0][n - 1][1];

        }
    }
}
