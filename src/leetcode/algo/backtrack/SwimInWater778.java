package leetcode.algo.backtrack;

//https://leetcode-cn.com/problems/swim-in-rising-water/
public class SwimInWater778 {
    // time O(N^2 * logN)
    // espace O(N^2)
    public int swimInWater(int[][] grid) {
        // N * N
        int N = grid.length;
        int left = Math.max(grid[0][0], grid[N - 1][N - 1]);
        int right = N * N - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;

            boolean[][] visited = new boolean[N][N];
            if (dfs(mid, 0, 0, visited, grid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean dfs(int mid, int i, int j, boolean[][] visited, int[][] grid) {
        // 到达终点
        if (i == grid.length - 1 && j == grid.length - 1) {
            return true;
        }

        if (!isValid(i, j, grid)) {
            return false;
        }

        if (visited[i][j]) {
            return false;
        }

        if (grid[i][j] > mid) {
            return false;
        }

        visited[i][j] = true;

        return dfs(mid, i + 1, j, visited, grid) || dfs(mid, i - 1, j, visited, grid)
                || dfs(mid, i, j + 1, visited, grid) || dfs(mid, i, j - 1, visited, grid);

    }

    boolean isValid(int row, int col, int[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid.length;
    }
}
