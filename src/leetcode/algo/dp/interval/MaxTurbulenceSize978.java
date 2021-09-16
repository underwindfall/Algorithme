package leetcode.algo.dp.interval;

//https://leetcode-cn.com/problems/longest-turbulent-subarray/
public class MaxTurbulenceSize978 {
    // time O(n)
    // space O(1)
    class DoubleIndex {
        public int maxTurbulenceSize(int[] arr) {
            int n = arr.length;
            int ret = 1;
            int left = 0, right = 0;
            while (right < n - 1) {
                if (left == right) {
                    if (arr[left] == arr[left + 1]) {
                        left++;
                    }
                    right++;
                } else {
                    if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                        right++;
                    } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                        right++;
                    } else {
                        left = right;
                    }
                }
                ret = Math.max(ret, right - left + 1);
            }
            return ret;
        }
    }

    // time O(n)
    // space O(n)
    // dp[i][0] 以arr[i] 结尾，arr[i - 1] > arr[i] 最大长度 ===== arr[i - 2] < arr[i - 1] >
    // arr[i] ==== dp[i][0] = dp[i - 1][1] + 1
    // dp[i][1] 以arr[i] 结尾，arr[i - 1] < arr[i] 最大长度 ===== arr[i - 2] > arr[i - 1] <
    // arr[i] ==== dp[i][1] = dp[i - 1][0] + 1
    // dp(0, 0) = dp(0, 1) = 1
    class DP {
        public int maxTurbulenceSize(int[] arr) {
            int n = arr.length;
            int[][] dp = new int[n][2];
            dp[0][0] = dp[0][1] = 1;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    dp[i][0] = dp[i - 1][1] + 1;
                } else {
                    dp[i][1] = dp[i - 1][0] + 1;
                }
            }

            int res = 1;
            for (int i = 0; i < n; i++) {
                res = Math.max(res, dp[i][0]);
                res = Math.max(res, dp[i][1]);
            }
            return res;
        }
    }
}
