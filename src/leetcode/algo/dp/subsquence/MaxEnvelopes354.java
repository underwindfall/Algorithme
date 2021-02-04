package leetcode.algo.dp.subsquence;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode-cn.com/problems/russian-doll-envelopes/
public class MaxEnvelopes354 {
    // 这道题目的难点在于如何利用已知在求LIS基础上转换思想考虑这道题目
    // 方法是降维
    // 让数组 x 首先按照升序排列，这样至少x每个都是不用担心的升序排列， x相同的话 y按照降序排列，这样至少每下个信封都比上个大
    // 只要找出y上的LIS就好了
    class DP {
        public int maxEnvelopes(int[][] envelopes) {
            Arrays.sort(envelopes, new Comparator<int[]>() {
                public int compare(int[] arr1, int[] arr2) {
                    if (arr1[0] == arr2[0]) {
                        return arr2[1] - arr1[1];
                    } else {
                        return arr1[0] - arr2[0];
                    }
                }
            });
            // 对高度数组寻找 LIS
            int n = envelopes.length;
            int[] height = new int[n];
            for (int i = 0; i < n; i++) {
                height[i] = envelopes[i][1];
            }
            return LIS(height);
        }

        int LIS(int[] height) {
            if (height.length == 0) {
                return 0;
            }
            int dp[] = new int[height.length];
            dp[0] = 1;
            int maxAns = 1;
            for (int i = 1; i < height.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (height[i] > height[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                maxAns = Math.max(maxAns, dp[i]);
            }
            return maxAns;
        }
    }
}
