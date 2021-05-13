package leetcode.datastructure.queue;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/number-of-islands/
//https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
public class NumberOfIsLands200 {
    // time O(M*N)
    // espace O(M*N)
    class Dfs {
        public int numIslands(char[][] grid) {
            int count = 0;

            int m = grid.length, n = grid[0].length;

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

        private void dfs(char[][] grid, int r, int c) {
            if (!isValid(grid, r, c)) {
                return;
            }
            // 如果这个格子不是岛屿，直接返回
            if (grid[r][c] != '1') {
                return;
            }
            // 标记为遍历过的
            grid[r][c] = '2';
            dfs(grid, r - 1, c);
            dfs(grid, r + 1, c);
            dfs(grid, r, c - 1);
            dfs(grid, r, c + 1);
        }

        boolean isValid(char[][] grid, int r, int c) {
            return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
        }
    }

    // time O(M*N)
    // espace O(min(M,N))
    class BFS {
        public int numIslands(char[][] grid) {
            int m = grid.length, n = grid[0].length;
            int count = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                        // 需要在这里进行变化
                        bfs(grid, i, j);
                    }
                }
            }

            return count;
        }

        void bfs(char[][] grid, int r, int c) {
            Queue<int[]> queue = new LinkedList<>();
            int M = grid.length, N = grid[0].length;
            queue.add(new int[] { r, c });

            while (!queue.isEmpty()) {
                int n = queue.size();

                for (int i = 0; i < n; i++) {
                    int[] node = queue.poll();
                    int row = node[0], col = node[1];
                    if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                        grid[row - 1][col] = '2';
                        queue.add(new int[] { row - 1, col });
                    }

                    if (row + 1 < M && grid[row + 1][col] == '1') {
                        grid[row + 1][col] = '2';
                        queue.add(new int[] { row + 1, col });
                    }

                    if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                        grid[row][col - 1] = '2';
                        queue.add(new int[] { row, col - 1 });
                    }
                    if (col + 1 < N && grid[row][col + 1] == '1') {
                        grid[row][col + 1] = '2';
                        queue.add(new int[] { row, col + 1 });
                    }
                }
            }

        }

        boolean isValid(char[][] grid, int r, int c) {
            return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
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
