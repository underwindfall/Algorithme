package leetcode.algo.backtrack;

import java.util.Arrays;

//https://leetcode-cn.com/problems/coloring-a-border/
public class ColorBorder1034 {
    // time O(N*M)
    // espace O(N*M)
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        int row = grid.length, col = grid[0].length;
        // 0 not visited
        // 1 visited
        // 2 should colored
        int[][] visited = new int[row][col];
        for (int[] v : visited) {
            Arrays.fill(v, 0);
        }

        dfs(grid, r0, c0, visited, r0, c0);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] == 2) {
                    grid[i][j] = color;
                }
            }
        }
        return grid;
    }

    void dfs(int[][] grid, int row, int col, int[][] visited, int oldRow, int oldCol) {
        if (!inArea(grid, oldRow, oldCol)) {
            return;
        }

        if (!inArea(grid, row, col)) {
            if (visited[oldRow][oldCol] != 0) {
                visited[oldRow][oldCol] = 2;
            }
            return;
        }

        if (visited[row][col] != 0) {
            return;
        }

        if (grid[oldRow][oldCol] != grid[row][col]) {
            if (oldCol != 0 && oldCol != grid[0].length - 1 && oldRow != 0 && oldRow != grid.length - 1) {
                visited[oldRow][oldCol] = 2;
            }
            return;
        }

        visited[row][col] = 1;

        dfs(grid, row + 1, col, visited, row, col);
        dfs(grid, row - 1, col, visited, row, col);
        dfs(grid, row, col + 1, visited, row, col);
        dfs(grid, row, col - 1, visited, row, col);
    }

    boolean inArea(int[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
}
