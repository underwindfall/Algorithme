package leetcode.algo.backtrack;

//https://leetcode-cn.com/problems/number-of-enclaves/
// 思路 和 leetcode 130完全一致
public class NumberEnclaves1020 {
    // time O(row * col)
    // espace  O(row * col)
    public int numEnclaves(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        int row = grid.length, col = grid[0].length;
        for (int j = 0; j < col; j++) {
            // first line
            if (grid[0][j] == 1) {
                dfs(0, j, grid);
            }
            // last line
            if (grid[row - 1][j] == 1) {
                dfs(row - 1, j, grid);
            }
        }

        for (int i = 0; i < row; i++) {
            // first col
            if (grid[i][0] == 1) {
                dfs(i, 0, grid);
            }
            // last col
            if (grid[i][col - 1] == 1) {
                dfs(i, col - 1, grid);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    void dfs(int row, int col, int[][] grid) {
        if (!inArea(row, col, grid)) {
            return;
        }
        if (grid[row][col] != 1) {
            return;
        }

        grid[row][col] = 2;

        dfs(row - 1, col, grid);
        dfs(row + 1, col, grid);
        dfs(row, col - 1, grid);
        dfs(row, col + 1, grid);
    }

    boolean inArea(int row, int col, int[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
}
