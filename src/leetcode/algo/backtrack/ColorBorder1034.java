package leetcode.algo.backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

//https://leetcode-cn.com/problems/coloring-a-border/
public class ColorBorder1034 {
    // time O(N*M)
    // espace O(N*M)
    private static final int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

    class DFS {
        public int[][] colorBorder(int[][] grid, int row, int col, int color) {
            int m = grid.length, n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            List<int[]> borders = new ArrayList<>();
            int startColor = grid[row][col];
            visited[row][col] = true;
            dfs(grid, row, col, visited, borders, startColor);
            for (int i = 0; i < borders.size(); i++) {
                int x = borders.get(i)[0], y = borders.get(i)[1];
                grid[x][y] = color;
            }
            return grid;
        }

        void dfs(int[][] grid, int row, int col, boolean[][] visited, List<int[]> borders, int startColor) {
            boolean isBorder = false;

            for (int[] dir : dirs) {
                int newR = dir[0] + row, newC = dir[1] + col;
                if (!(isValid(grid, newR, newC) && grid[newR][newC] == startColor)) {
                    isBorder = true;
                    continue;
                }
                if (visited[newR][newC])
                    continue;

                visited[newR][newC] = true;
                dfs(grid, newR, newC, visited, borders, startColor);

            }

            if (isBorder) {
                borders.add(new int[] { row, col });
            }
        }

        boolean isValid(int[][] grid, int r, int c) {
            return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
        }
    }

    // https://leetcode.cn/problems/coloring-a-border/solution/gong-shui-san-xie-tu-lun-sou-suo-zhuan-t-snvw/
    // time O(n * m)
    // space O(n * m)
    class BFS {
        public int[][] colorBorder(int[][] grid, int row, int col, int color) {
            int m = grid.length, n = grid[0].length;
            int[][] ans = new int[m][n];
            int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
            Deque<int[]> d = new ArrayDeque<>();
            d.addLast(new int[] { row, col });
            while (!d.isEmpty()) {
                int[] poll = d.pollFirst();
                int x = poll[0], y = poll[1], cnt = 0;
                for (int[] di : dirs) {
                    int nx = x + di[0], ny = y + di[1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                        continue;
                    if (grid[x][y] != grid[nx][ny])
                        continue;
                    else
                        cnt++;
                    if (ans[nx][ny] != 0)
                        continue;
                    d.addLast(new int[] { nx, ny });
                }
                ans[x][y] = cnt == 4 ? grid[x][y] : color;
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (ans[i][j] == 0)
                        ans[i][j] = grid[i][j];
                }
            }
            return ans;
        }

    }

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
