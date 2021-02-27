package leetcode.algo.dp.twodimension;

// https://leetcode-cn.com/problems/largest-sum-of-averages/
public class LargestSumOfAverages813 {
    // represent a largest number who use i number divided by k group
    // 分割k-1 次就是分割成了K组
    // dp[i][k] = max(dp[j][k-1]+avg(j+1....i)) ==> the range of j should be like (j
    // >= k-1 && j + 1 < i otherwise cannot divided by k-1 group)
    class DP {
        public double largestSumOfAverages(int[] A, int K) {
            int n = A.length;
            double[][] dp = new double[n + 1][K + 1];

            double[] prefixSum = new double[n + 1];
            for (int i = 1; i <= n; i++) {
                prefixSum[i] = prefixSum[i - 1] + A[i - 1];
            }
            // set down an initial value
            // dp[i][1] used all numbers and calculate the average number of it
            for (int i = 1; i <= n; i++) {
                dp[i][1] = prefixSum[i] / i;
            }

            // 从2开始
            for (int k = 2; k <= K; k++) {
                // i 从k 开始 小于n
                for (int i = k; i <= n; i++) {
                    // dp[i][k] = max(dp[j][k - 1] + avg(j+1, i), dp[i][k])
                    for (int j = (k - 1); j + 1 <= i; j++) {
                        // 计算j+1 的和
                        double avg = (prefixSum[i] - prefixSum[j]) / (i - (j + 1) + 1);
                        dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + avg);
                    }
                }
            }
            return dp[n][K];
        }
    }

    // A divide K group
    // A (K-1) group + A[0] ....
    // min A (K-1) group + A[0] ....
    class DFS {
        public double largestSumOfAverages(int[] A, int K) {
            return dfs(A, 0, K);
        }

        //
        double dfs(int[] A, int startIndex, int group) {
            if (group == 0)
                return 0.0;
            if (group == 1) {
                double sum = 0.0;
                for (int i = 0; i < A.length; i++) {
                    sum += A[i];
                }
                return Double.valueOf(sum / (A.length - startIndex));
            }
            double sum = 0.0;
            double res = 0.0;
            for (int i = startIndex; i <= A.length - startIndex; i++) {
                sum += A[i];
                double avg = sum / (i - startIndex + 1);
                // k-1 group
                double result = dfs(A, startIndex + 1, group - 1);
                res = Math.max(res, avg + result);
            }
            return res;
        }
    }
}
