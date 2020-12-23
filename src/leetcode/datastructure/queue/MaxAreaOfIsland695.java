package leetcode.datastructure.queue;

// https://leetcode-cn.com/problems/max-area-of-island/
public class MaxAreaOfIsland695 {
    //思路 题型一致
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
}
