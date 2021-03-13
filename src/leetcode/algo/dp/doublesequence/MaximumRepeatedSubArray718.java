package leetcode.algo.dp.doublesequence;

// https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
public class MaximumRepeatedSubArray718 {

    // LCS 的变形 唯一的不同是这里要求连续
    // 如果相同 目前的值 +1
    // 不相同 则化为0
    class DP {
        public int findLength(int[] A, int[] B) {
            int a = A.length;
            int b = B.length;
            int[][] dp = new int[a + 1][b + 1];
            int ans = 0;
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    if (A[i] == B[j]) {
                        dp[i + 1][j + 1] = dp[i][j] + 1;
                    } else {
                        dp[i + 1][j + 1] = 0;
                    }
                    ans = Math.max(ans, dp[i + 1][j + 1]);
                }
            }
            // for (int i = a - 1; i >= 0; i--) {
            // for (int j = b - 1; j >= 0; j--) {
            // if (A[i] == B[j]) {
            // dp[i][j] = dp[i + 1][j + 1] + 1;
            // } else {
            // dp[i][j] = 0;
            // }

            // ans = Math.max(ans, dp[i][j]);
            // }
            // }
            return ans;
        }
    }

    // 思路是通过两个数组滑动比较出两个数组之间的公共数字部分
    class SlidingWindow {
        public int findLength(int[] A, int[] B) {
            int n = A.length, m = B.length;
            int ret = 0;
            for (int i = 0; i < n; i++) {
                int len = Math.min(m, n - i);
                // 从A的i 开始比较一直到最后
                int maxlen = maxLen(A, B, i, 0, len);
                ret = Math.max(ret, maxlen);
            }
            for (int j = 0; j < m; j++) {
                int len = Math.min(n, m - j);
                int maxlen = maxLen(A, B, 0, j, len);
                ret = Math.max(ret, maxlen);
            }
            return ret;
        }

        int maxLen(int[] A, int[] B, int addA, int addB, int len) {
            int ret = 0, k = 0;
            for (int i = 0; i < len; i++) {
                if (A[addA + i] == B[addB + i]) {
                    k++;
                } else {
                    k = 0;
                }
                ret = Math.max(ret, k);
            }
            return ret;
        }
    }
}