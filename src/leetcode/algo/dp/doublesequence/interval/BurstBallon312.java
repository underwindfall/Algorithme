package leetcode.algo.dp.doublesequence.interval;

import java.util.LinkedList;

// https://leetcode-cn.com/problems/burst-balloons/
public class BurstBallon312 {

    // 技巧 选中气球后 计算完分数 重新回退这个气球不影响下一个结果
    class DFS {
        public int maxCoins(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            LinkedList<Integer> arr = new LinkedList<>();
            for (int i : nums) {
                arr.add(i);
            }
            return dfs(arr);
        }

        int dfs(LinkedList<Integer> arr) {
            if (arr.size() == 0) {
                return 0;
            }
            int n = arr.size();
            int res = 0;

            for (int i = 0; i < n; i++) {
                // 处理左右边界
                int left = (i - 1 > 0) ? arr.get(i - 1) : 1;
                int right = (i + 1 < n) ? arr.get(i + 1) : 1;

                // 计算积分
                int total = left * arr.get(i) * right;

                // 选中气球 这个技巧很重要
                int tmp = arr.remove(i);
                total += dfs(arr);
                res = Math.max(res, total);
                arr.add(i, tmp);
            }
            return res;
        }
    }

    //终点在于考虑最后保留的气球 而不是去扎破那个气球
    class RecursiveMemo {
        public int maxCoins(int[] iNums) {
            int[] nums = new int[iNums.length + 2];
            int n = 1;
            for (int x : iNums) {
                nums[n++] = x;
            }
            nums[0] = nums[n++] = 1;
            int[][] memo = new int[n][n];
            return dfs(memo, nums, 0, n - 1);
        }

        int dfs(int[][] memo, int[] nums, int left, int right) {
            if (left + 1 == right)
                return 0;
            if (memo[left][right] > 0)
                return memo[left][right];
            int ans = 0;
            for (int i = left + 1; i < right; i++) {
                ans = Math.max(ans, nums[left] * nums[i] * nums[right] 
                        + dfs(memo, nums, left, i)
                        + dfs(memo, nums, i, right));
            }
            memo[left][right] = ans;
            return ans;
        }
    }

/**
 * (i,j) 0 1  2   3   4   ...   n-2   n-1   n   n+1
 * 0     0 1  2   3   4   ...                   n+1
 * 1       1  2   3   4   ...                   n+1
 * 2          2   3   4   ...                   n+1
 * 3              3   4   ...                   n+1
 * 4                  4                         n+1
 * .                      .                     .
 * .                         .                  .
 * n-2                          n-2   n-1   n   n+1
 * n-1                                          n+1
 */
    class Dp{
        public int maxCoins(int[] nums) {
            int n = nums.length;
            int[][] rec = new int[n + 2][n + 2];
            int[] val = new int[n + 2];
            val[0] = val[n + 1] = 1;
            for (int i = 1; i <= n; i++) {
                val[i] = nums[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 2; j <= n + 1; j++) {
                    for (int k = i + 1; k < j; k++) {
                        int sum = val[i] * val[k] * val[j];
                        sum += rec[i][k] + rec[k][j];
                        rec[i][j] = Math.max(rec[i][j], sum);
                    }
                }
            }
            return rec[0][n + 1];
        }

        // public int maxCoins(int[] iNums) {
        //     int[] nums = new int[iNums.length + 2];
        //     int n = 1;
        //     for (int x : iNums) if (x > 0) nums[n++] = x;
        //     nums[0] = nums[n++] = 1;
        
        
        //     int[][] dp = new int[n][n];
        //     for (int k = 2; k < n; ++k)
        //         for (int left = 0; left < n - k; ++left) {
        //             int right = left + k;
        //             for (int i = left + 1; i < right; ++i)
        //                 dp[left][right] = Math.max(dp[left][right], 
        //                 nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
        //         }
        
        //     return dp[0][n - 1];
        // }
    }
}
