package leetcode.algo.dp.matrix;

import java.util.Arrays;

// https://leetcode-cn.com/problems/minimum-path-sum/
public class MinPathSum64 {
    // dp bottom up
    // 1. subproblem (:i) (:j) 子问题 当前最小值
    // 2. guessing 
        // (:i)(:j) -> min((: i + 1)(:j),(:i)(:j + 1)) + grid[i][j]
    // 3. recursion 
        // dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
    // 4. algo
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = grid[m - 1][n - 1];
        // 初始化 最后一行
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = grid[m - 1][i] + dp[m - 1][i + 1];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // 最后一列
                if (j == n - 1) {
                    dp[i][j] = dp[i + 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
                }
            }
        }
        return dp[0][0];
    }

    class Dfs {
        private int M;
        private int N;
        private int[][] memo;

        // dfs + 记忆
        public int minPathSum(int[][] grid) {
            M = grid.length;
            N = grid[0].length;
            memo = new int[M][N];
            for (int i = 0; i < M; i++) {
                Arrays.fill(memo[i], -1);
            }
            return dfs(grid, 0, 0);
        }

        private int dfs(int[][] grid, int r, int c) {
            // 若越界，则认为不可达，距离为无穷大
            if (r < 0 || r >= M || c < 0 || c >= N)
                return Integer.MAX_VALUE;
            if (memo[r][c] > -1)
                return memo[r][c];
            // 若到达终点，终点的贡献值是其本身
            if (r == M - 1 && c == N - 1)
                return grid[M - 1][N - 1];
            // 右边的点到终点最短的路径
            int right = dfs(grid, r, c + 1);
            // 下面的点到终点的最短路径
            int down = dfs(grid, r + 1, c);
            // 取两者的较小值，计算出当前点的最小路径值
            int ans = Math.min(right, down) + grid[r][c];
            memo[r][c] = ans;
            return ans;
        }

    }
}
