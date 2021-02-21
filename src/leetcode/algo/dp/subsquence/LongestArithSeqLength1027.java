package leetcode.algo.dp.subsquence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/longest-arithmetic-subsequence/
public class LongestArithSeqLength1027 {
    // 定义状态转移方程
    // dp[i][j] 代表的是A[i] 这个数字 以J作为差值的数字， dp[i][j] 其值反映了是目前等差数列的长度
    // 最小的等差数列的长度是2
    // 所以 转移关系是 dp[i+1][j] = Math.max(dp[i][j] + 1, 2);
    public int longestArithSeqLength(int[] A) {
        int len = A.length;
        int ans = 0;
        int[][] dp = new int[len][20001];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int diff = A[j] - A[i];
                int diffIndex = diff + 10000;
                dp[i][diffIndex] = Math.max(dp[j][diffIndex] + 1, 2);
                ans = Math.max(dp[i][diffIndex], ans);
            }
        }
        return ans;
    }

    class Hash {

        public int bestlongestArithSeqLength(int[] A) {
            int len = A.length;
            int ans = 0;
            int[][] dp = new int[len][len];
            Map<Integer, Integer> map = new HashMap<>();

            for (int[] d : dp) {
                Arrays.fill(d, 2);
            }

            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    // 之前的一个数字
                    int target = (A[i] - A[j]) + A[i];
                    if (map.containsKey(target)) {
                        dp[i][j] = dp[map.get(target)][i] + 1;
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
                map.put(A[i], i);
            }
            return ans;
        }

        // 变形
        public int noramllongestArithSeqLength(int[] A) {
            int len = A.length;
            int ans = 0;
            int[][] dp = new int[len][len];
            for (int[] d : dp) {
                Arrays.fill(d, 2);
            }

            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    // 之前的一个数字
                    int target = (A[i] - A[j]) + A[i];
                    for (int k = i - 1; k >= 0; k--) {
                        if (A[k] == target) {
                            dp[i][j] = dp[k][i] + 1;
                            break;
                        }
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
            return ans;
        }
    }

}
