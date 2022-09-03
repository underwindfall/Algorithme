package leetcode.algo.dp;

import java.util.Arrays;

// https://leetcode.cn/problems/maximum-length-of-pair-chain/
public class FindLongestChain646 {
    // 定义 f[i] 为以 pairs[i] 为结尾的最长数对链长度，所有 f[i] 中的最大值为答案
    // time O(N LogN)
    // space O(N)
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int n = pairs.length;
        int ans = 1;

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = dp[j] + 1;
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // time O(N LogN)
    // space O(1)
    class Greedy {
        public int findLongestChain(int[][] pairs) {
            Arrays.sort(pairs, (a, b) -> a[1] - b[1]);

            int ans = 0;
            int last = Integer.MIN_VALUE;
            for (int[] p : pairs) {
                if (last < p[0]) {
                    ans++;
                    last = p[1];
                }
            }
            return ans;
        }
    }
}
