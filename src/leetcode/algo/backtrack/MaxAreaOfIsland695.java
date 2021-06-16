package leetcode.algo.backtrack;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/max-area-of-island/
public class MaxAreaOfIsland695 {

    // time O(row * col)
    //espace O(row * col)
    class DFS {

        // dfs
        public int maxAreaOfIsland(int[][] grid) {
            int M = grid.length, N = grid[0].length;
            int max = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] == 1) {
                        max = Math.max(max, dfs(grid, i, j));
                    }
                }
            }
            return max;
        }

        int dfs(int[][] grid, int row, int col) {
            if (!isValid(grid, row, col)) {
                return 0;
            }

            if (grid[row][col] != 1) {
                return 0;
            }

            grid[row][col] = 2;

            return dfs(grid, row - 1, col) + dfs(grid, row + 1, col) + dfs(grid, row, col - 1) + dfs(grid, row, col + 1)
                    + 1;
        }

        boolean isValid(int[][] grid, int row, int col) {
            return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
        }
    }

  // time O(row * col)
    //espace O(row * col)
    class BFS {
        // bfs
        public int maxAreaOfIsland(int[][] grid) {
            int max = 0;
            int M = grid.length, N = grid[0].length;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] == 1) {
                        max = Math.max(max, bfs(grid, i, j));
                    }
                }
            }
            return max;
        }

        int bfs(int[][] grid, int r, int c) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[] { r, c });
            grid[r][c] = 2;
            int count = 1;
            while (!queue.isEmpty()) {
                int n = queue.size();
                for (int i = 0; i < n; i++) {
                    int[] node = queue.poll();
                    int row = node[0], col = node[1];

                    if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                        count++;
                        grid[row - 1][col] = 2;
                        queue.add(new int[] { row - 1, col });
                    }

                    if (row + 1 < grid.length && grid[row + 1][col] == 1) {
                        count++;
                        grid[row + 1][col] = 2;
                        queue.add(new int[] { row + 1, col });
                    }

                    if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                        count++;
                        grid[row][col - 1] = 2;
                        queue.add(new int[] { row, col - 1 });
                    }
                    if (col + 1 < grid[0].length && grid[row][col + 1] == 1) {
                        count++;
                        grid[row][col + 1] = 2;
                        queue.add(new int[] { row, col + 1 });
                    }

                }
            }
            return count;
        }
    }

}
