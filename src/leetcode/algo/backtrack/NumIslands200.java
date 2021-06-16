package leetcode.algo.backtrack;

//https://leetcode-cn.com/problems/number-of-islands/
public class NumIslands200 {
    // time O(M*N)
    // espace O(M*N)
    int count = 0;

    public int numIslands(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    void dfs(char[][] grid, int row, int col) {
        if (!inArea(grid, row, col)) {
            return;
        }

        if (grid[row][col] != '1') {
            return;
        }

        grid[row][col] = '2';
        dfs(grid, row + 1, col);
        dfs(grid, row - 1, col);
        dfs(grid, row, col + 1);
        dfs(grid, row, col - 1);
    }

    boolean inArea(char[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
}
