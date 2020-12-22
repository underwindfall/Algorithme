package leetcode.datastructure.queue;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/number-of-islands/
public class NumberOfIsLands200 {
    // time O(M*N)
    // espace O(M*N)
    class Dfs {
        public int numIslands(char[][] grid) {
            int count = 0;
            int m = grid.length;
            int n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        dfs(grid, i, j);
                        count++;
                    }
                }
            }
            return count;
        }

        private void dfs(char[][] grid, int i, int j) {
            int m = grid.length;
            int n = grid[0].length;
            if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
                return;
            }
            grid[i][j] = '0';
            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
        }
    }

    class Bfs {
        public int numIslands(char[][] grid) {
            int count = 0;
            int m = grid.length;
            int n = grid[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        bfs(grid, i, j);
                        count++;
                    }
                }
            }
            return count;
        }

        private void bfs(char[][] grid, int i, int j) {
            int m = grid.length;
            int n = grid[0].length;
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[] { i, j });
            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                int row = point[0];
                int col = point[1];
                if (row >= 0 && row < m && col >= 0 && col < n && grid[row][col] == '1') {
                    grid[row][col] = '0';
                    queue.add(new int[] { row - 1, col });
                    queue.add(new int[] { row + 1, col });
                    queue.add(new int[] { row, col + 1 });
                    queue.add(new int[] { row, col - 1 });
                }
            }
        }
    }
}
