package leetcode.algo.backtrack;

//https://leetcode-cn.com/problems/number-of-closed-islands/
public class CloseIsLands1254 {
    // time O(M*N)
    // espace O(M*N)
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int res = 0;
        //跳过最外围一层
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (grid[i][j] == 0) {
                    if (dfs(grid, i, j)) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private boolean dfs(int[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (i < 0 || j < 0 || i >= rows || j >= cols) {
            return false;
        }
        //遍历一圈后发现都是海说明是孤岛
        if (grid[i][j] == 1) {
            return true;
        }
        grid[i][j] = 1;
        boolean up = dfs(grid, i - 1, j);
        boolean down = dfs(grid, i + 1, j);
        boolean left = dfs(grid, i, j - 1);
        boolean right = dfs(grid, i, j + 1);
        if (up && down && left && right) {
            return true;
        }
        return false;
    }

}
