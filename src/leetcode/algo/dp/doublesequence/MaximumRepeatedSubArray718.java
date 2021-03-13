package leetcode.algo.dp.doublesequence;

// https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
public class MaximumRepeatedSubArray718 {
    public int findLength(int[] A, int[] B) {
        int a = A.length;
        int b = B.length;
        int[][] dp = new int[a + 1][b + 1];
        int ans = 0;
        for (int i = a - 1; i >= 0; i--) {
            for (int j = b - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = 0;
                }

                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}