package leetcode.algo.dp;

import java.util.Arrays;

// https://leetcode-cn.com/problems/knight-probability-in-chessboard/
public class KnightProbalility688 {

    // timeO(k*n^2)
    // space O(k*n^2)
    class DFSMemo {

        int[][] dirs = new int[][] {
                { -1, 2 },
                { 1, 2 },
                { 2, 1 },
                { 2, -1 },
                { 1, -2 },
                { -1, -2 },
                { -2, -1 },
                { -2, 1 }
        };

        public double knightProbability(int n, int k, int row, int col) {
            double[][][] memo = new double[n][n][k + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Arrays.fill(memo[i][j], -1);
                }
            }
            return dfs(n, row, col, k, memo);
        }

        double dfs(int n, int row, int col, int k, double[][][] memo) {
            if (row < 0 || row >= n || col < 0 || col >= n) {
                return 0;
            }
            if (k == 0) {
                return 1.0;
            }
            if (memo[row][col][k] != -1) {
                return memo[row][col][k];
            }
            double ans = 0;
            for (int[] dir : dirs) {
                ans += dfs(n, row + dir[0], col + dir[1], k - 1, memo);
            }
            memo[row][col][k] = ans / 8;
            return memo[row][col][k];
        }
    }

    // 定义 f[i][j][p]为从位置 (i, j) 出发，使用步数不超过 p 步，最后仍在棋盘内的概率。
    // time O(n^2 * k * 8)
    // space O(n^2 * k)
    class DP {
        int[][] dirs = new int[][] {
                { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { -2, 1 }, { -2, -1 }, { 2, 1 }, { 2, -1 } };

        public double knightProbability(int n, int k, int row, int column) {
            double[][][] f = new double[n][n][k + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    f[i][j][0] = 1;
                }
            }
            for (int p = 1; p <= k; p++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        for (int[] d : dirs) {
                            int nx = i + d[0], ny = j + d[1];
                            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                                continue;
                            f[i][j][p] += f[nx][ny][p - 1] / 8;
                        }
                    }
                }
            }
            return f[row][column][k];
        }
    }
}
