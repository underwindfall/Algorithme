package leetcode.datastructure.stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/01-matrix/
public class UpdateMatrix542 {

    class DPOptimization {

        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        public int[][] updateMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            // 初始化动态规划的数组，所有的距离值都设置为一个很大的数
            int[][] dist = new int[m][n];
            for (int i = 0; i < m; ++i) {
                Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
            }
            // 如果 (i, j) 的元素为 0，那么距离为 0
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == 0) {
                        dist[i][j] = 0;
                    }
                }
            }
            // 只有 水平向左移动 和 竖直向上移动，注意动态规划的计算顺序
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i - 1 >= 0) {
                        dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                    }
                    if (j - 1 >= 0) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                    }
                }
            }
            // 只有 水平向右移动 和 竖直向下移动，注意动态规划的计算顺序
            for (int i = m - 1; i >= 0; --i) {
                for (int j = n - 1; j >= 0; --j) {
                    if (i + 1 < m) {
                        dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                    }
                    if (j + 1 < n) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                    }
                }
            }
            return dist;
        }
    
    }

    // 1.subProblem
    //0 _ _ _ 0
    //_ _ _ _ _
    //_ _ 1 _ _
    //_ _ _ _ _
    //0 _ _ _ 0
    // 子问题 （i,j) 都有4中选择情况 
    // 1）向左 + 向下
    // 2）向右 + 向下
    // 3）向右 + 向上
    // 4）向左 + 向上

    // 2.guessing
    // dp(i, j) 代表 （i，j）到 0 的最短距离
    // 3.recurrence
    // dp(i, j) = Min(min(dp(i - 1, j), dp(i, j - 1)) + 1,
    //                min(dp(i + 1, j), dp(i, j + 1)) + 1,
    //                min(dp(i - 1, j - 1), dp(i, j + 1)) + 1,
    //                min(dp(i + 1, j - 1), dp(i, j - 1)) + 1,
    //time O(M*N)
    //espace O(1)
    class DP {
        public int[][] updateMatrix(int[][] matrix) {
            int M = matrix.length, N = matrix[0].length;
            //初始化动态规划的数组，所有的距离值都是MAX_VALUE
            int[][] dp = new int[M][N];
            for (int[] row : dp) {
                Arrays.fill(row, Integer.MAX_VALUE / 2);
            }
             // 如果 (i, j) 的元素为 0，那么距离为 0
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][j] == 0) {
                        dp[i][j] = 0;
                    }
                }
            }
            // 只有 水平向左移动 和 竖直向上移动 (i-i,j) (i, j - 1)
             for (int i = 0; i < M; i++) {
                 for (int j = 0; j < N; j++) {
                     if (i - 1 >= 0) {
                         dp[i][j] = Math.min(dp[i][j],dp[i - 1][j] + 1);
                     }
                     if (j - 1 >= 0) {
                         dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                     }
                 }
             }
            // 只有 水平向左移动 和 竖直向下移动
             for (int i = M - 1; i >= 0; i--) {
                for (int j = 0; j < N; j++) {
                    if (i + 1 < M) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                    }
                    if (j - 1 >= 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    }
                }
            }

             // 只有 水平向右移动 和 竖直向上移动，注意动态规划的计算顺序
             for (int i = 0; i < M; ++i) {
                 for (int j = N - 1; j >= 0; --j) {
                     if (i - 1 >= 0) {
                         dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                     }
                     if (j + 1 < N) {
                         dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                     }
                 }
             }
             // 只有 水平向右移动 和 竖直向下移动，注意动态规划的计算顺序
             for (int i = M - 1; i >= 0; --i) {
                 for (int j = N - 1; j >= 0; --j) {
                     if (i + 1 < M) {
                         dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                     }
                     if (j + 1 < N) {
                         dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                     }
                 }
             }
            return dp;
        }
    }

    // time
    // espace
    class Solution {
        public int[][] updateMatrix(int[][] matrix) {
            // 首先将所有的 0 都入队，并且将 1 的位置设置成 -1，表示该位置是 未被访问过的 1
            Queue<int[]> q = new LinkedList<int[]>();
            int n = matrix.length, m = matrix[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (matrix[i][j] == 0) {
                        q.offer(new int[] { i, j });
                    } else {
                        matrix[i][j] = -1;
                    }
                }
            }
            bfs(matrix, q);
            return matrix;
        }

        void bfs(int[][] matrix, Queue<int[]> q) {
            int n = matrix.length, m = matrix[0].length;
            while (!q.isEmpty()) {
                int sz = q.size();
                for (int i = 0; i < sz; i++) {
                    int[] node = q.poll();
                    int r = node[0], c = node[1];
                    if (r + 1 >= 0 && r + 1 < n && c >= 0 && c < m && matrix[r + 1][c] == -1) {
                        matrix[r + 1][c] = matrix[r][c] + 1;
                        q.offer(new int[] { r + 1, c });
                    }
                    if (r - 1 >= 0 && r - 1 < n && c >= 0 && c < m && matrix[r - 1][c] == -1) {
                        matrix[r - 1][c] = matrix[r][c] + 1;
                        q.offer(new int[] { r - 1, c });
                    }
                    if (r >= 0 && r < n && c + 1 >= 0 && c + 1 < m && matrix[r][c + 1] == -1) {
                        matrix[r][c + 1] = matrix[r][c] + 1;
                        q.offer(new int[] { r, c + 1 });
                    }
                    if (r >= 0 && r < n && c - 1 >= 0 && c - 1 < m && matrix[r][c - 1] == -1) {
                        matrix[r][c - 1] = matrix[r][c] + 1;
                        q.offer(new int[] { r, c - 1 });
                    }
                }
            }
        }
    }
}