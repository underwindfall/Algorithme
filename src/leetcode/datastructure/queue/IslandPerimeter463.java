package leetcode.datastructure.queue;

// https://leetcode-cn.com/problems/island-perimeter
public class IslandPerimeter463 {
    // 题目的思路在于如何判断当前的这个路径的计算方法和水域，陆地的关系。
    // 即：超出界限= +1 海域= +1 其他不算
    // time :O(M*N)
    // espace :O(M*N)
    class Dfs {
        public int islandPerimeter(int[][] grid) {
            int r = grid.length;
            int c = grid[0].length;

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (grid[i][j] == 1) {
                        // 题目限制只有一个岛屿，计算一个即可
                        return dfs(grid, i, j);
                    }
                }
            }
            return 0;
        }

        int dfs(int[][] grid, int r, int c) {
            // 函数因为「坐标 (r, c) 超出网格范围」返回，对应一条边界线的边
            if (!inArea(grid, r, c)) {
                return 1;
            }
            // 函数因为「当前格子是海洋格子」返回，对应一条海的边
            if (grid[r][c] == 0) {
                return 1;
            }
            if (grid[r][c] != 1) {
                return 0;
            }
            grid[r][c] = 2;
            return dfs(grid, r - 1, c) + dfs(grid, r + 1, c) + dfs(grid, r, c - 1) + dfs(grid, r, c + 1);
        }

        boolean inArea(int[][] grid, int r, int c) {
            int m = grid.length;
            int n = grid[0].length;
            return r >= 0 && r < m && c >= 0 && c < n;
        }
    }

    class BFS {
        public int islandPerimeter(int[][] grid) {
            int count = 0;
                   
            return count;
        }
    }

    // time: O(4MN)
    // espace: O(1)
    class Recursive {
        public int islandPerimeter(int[][] grid) {
            // 下个格子的x轴与y轴加的值，右下左上
            int[] dx = { 0, 1, 0, -1 };
            int[] dy = { 1, 0, -1, 0 };
            // n表示高度，m表示宽度
            int n = grid.length, m = grid[0].length;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        int cnt = 0;
                        for (int k = 0; k < 4; k++) {
                            int tx = i + dx[k];
                            int ty = j + dy[k];
                            // tx < 0，已是左边界；tx >= n，已是右边界
                            // ty < 0，已是上边界；ty >= m，已是下边界
                            // grid[tx][ty] == 0，相邻格子是水域
                            if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                                cnt += 1;
                            }
                        }
                        ans += cnt;
                    }
                }
            }
            return ans;
        }
    }
}
