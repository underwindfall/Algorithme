package leetcode.datastructure.queue;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/max-area-of-island/
public class MaxAreaOfIsland695 {
    // 思路 题型一致
    // time O(M*N)
    // espace O(M*N)
    class Dfs {
        public int maxAreaOfIsland(int[][] grid) {
            int maxArea = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        maxArea = Math.max(maxArea, dfs(grid, i, j));
                    }
                }
            }
            return maxArea;
        }

        int dfs(int[][] grid, int row, int col) {
            if (!inArea(grid, row, col)) {
                return 0;
            }
            if (grid[row][col] != 1) {
                return 0;
            }
            grid[row][col] = 2;
            return dfs(grid, row - 1, col) + dfs(grid, row + 1, col) + dfs(grid, row, col + 1) + dfs(grid, row, col - 1)
                    + 1;
        }

        boolean inArea(int[][] grid, int i, int j) {
            return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
        }
    }

    // time O(M*N)
    //espace O(min (M, N))
    class BFS {
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
                        queue.add(new int[] { row, col - 1});
                    }
                    if (col + 1 < grid[0].length && grid[row][col + 1] == 1) {
                        count++;
                        grid[row][col + 1] = 2;
                        queue.add(new int[] { row, col +1 });
                    }

                }
            }
            return count;
        }
    }
}
