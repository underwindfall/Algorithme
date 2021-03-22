package leetcode.algo.dp.matrix;

import java.util.List;

// https://leetcode-cn.com/problems/triangle/
public class Triangle120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 1.subproblem 第i行j列的三角值 所积累的最小值
        // 2.guessing (:i) (:j) 假设有了，min((:i - 1)(:j) ,(:i - 1)(:j+1))+(i,j)
        // 3.recurrsion dp(i, j) = min(dp(i + 1, j), dp(i + 1, j + 1 )) + value(i, j)
        // 4.特殊情况 dp(i, j)

        // bottom up
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    class Dfs {
        public int minimumTotal(List<List<Integer>> triangle) {
            return dfs(triangle, 0, 0);
        }

        int dfs(List<List<Integer>> triangle, int i, int j) {
            if (i == triangle.size()) {
                return 0;
            }
            return Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
        }
    }
}
