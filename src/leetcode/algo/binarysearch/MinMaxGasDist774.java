package leetcode.algo.binarysearch;

import java.util.Arrays;

// https://leetcode-cn.com/problems/minimize-max-distance-to-gas-station/
public class MinMaxGasDist774 {

    // time O(NlogW)
    // space O(1)
    class Binarysearch {
        public double minmaxGasDist(int[] st, int K) {
            int count, N = st.length;
            double left = 0, right = st[N - 1] - st[0], mid;

            while (left + 1e-6 < right) {
                mid = (left + right) / 2;
                count = 0;
                for (int i = 0; i < N - 1; ++i)
                    count += Math.ceil((st[i + 1] - st[i]) / mid) - 1;
                if (count > K)
                    left = mid;
                else
                    right = mid;
            }
            return right;
        }
    }

    // time O(Nk^2)
    // space O(Nk)
    class DP {
        // dp(n, k)增加K个加油站，前n个加油站之间最小的最大距离
        public double minmaxGasDist(int[] stations, int K) {
            int N = stations.length;
            double[] deltas = new double[N - 1];
            for (int i = 0; i < N - 1; i++) {
                deltas[i] = stations[i + 1] - stations[i];
            }

            double[][] dp = new double[N - 1][K + 1];
            // dp(i, j) = answer for deltas[:i+1] when adding j gas stations
            for (int i = 0; i <= K; i++) {
                dp[0][i] = deltas[0] / (i + 1);
            }
            for (int p = 1; p < N - 1; p++) {
                for (int k = 0; k <= K; k++) {
                    double bns = 99999999;
                    for (int x = 0; x <= k; x++) {
                        bns = Math.min(bns, Math.max(deltas[p] / (x + 1), dp[p - 1][k - x]));
                    }
                    dp[p][k] = bns;
                }
            }
            // ?????
            return dp[N - 2][K];
        }
    }

    // time O(NK)
    // space O(N)
    class BruteForce {
        public double minmaxGasDist(int[] stations, int K) {
            int N = stations.length;
            double[] deltas = new double[N - 1];
            for (int i = 0; i < N - 1; ++i)
                deltas[i] = stations[i + 1] - stations[i];

            int[] count = new int[N - 1];
            Arrays.fill(count, 1);

            for (int k = 0; k < K; ++k) {
                // Find interval with largest part
                int best = 0;
                for (int i = 0; i < N - 1; ++i)
                    if (deltas[i] / count[i] > deltas[best] / count[best])
                        best = i;

                // Add gas station to best interval
                count[best]++;
            }

            double ans = 0;
            for (int i = 0; i < N - 1; ++i)
                ans = Math.max(ans, deltas[i] / count[i]);
            return ans;
        }

    }
}
