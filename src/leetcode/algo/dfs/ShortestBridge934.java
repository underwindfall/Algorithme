package leetcode.algo.dfs;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/shortest-bridge/
public class ShortestBridge934 {
    // time O(n * m)
    // space O(n * m)
    // 使用深度优先搜索，对其中一个小岛进行标记，将 1 标记为 2，在这个过程中，也要记得把这个小岛的边界保存下来，边界 [x, y] 满足以下条件：
    // 1.x == 0 || y == 0 表示整个图的左边界和上边界。
    // 2.x == n - 1 || y == m - 1 表示整个图的右边界和下边界。
    //3.grid[x-1][y]==0||grid[x+1][y]==0||grid[x][y-1]==0||grid[x][y+1]==0 表示当前小岛的海域边界。
    
    //然后使用广度优先搜索，从之前标记的小岛边界寻找另一个小岛，只要在某一次广度优先搜索过程中找到了 grid[x][y]==1 那么说明就是找到了另一个小岛，直接返回距离就好了。

    // 用来转换方向
    private static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int shortestBridge(int[][] grid) {
        // 记录下来整个图的尺寸
        int n = grid.length, m = grid[0].length;
        // 标记哪些位置已经访问过了
        boolean[][] visited = new boolean[n][m];
        // 标记是否找到第一个小岛了
        boolean findIsland = false;
        // 记录第一个小岛的边界
        Queue<int[]> board = new LinkedList<>();
        // 去标记第一个小岛，将原来的 1 改成 2
        for (int x = 0; x < n && !findIsland; ++x) {
            for (int y = 0; y < m && !findIsland; ++y) {
                if (!visited[x][y] && grid[x][y] == 1) {
                    mark(grid, visited, x, y, 2, board);
                    findIsland = true;
                }
            }
        }
        // 记录距离
        int ans = 0;
        while (!board.isEmpty()) {
            // 记录当前需要遍历的边界数量
            int size = board.size();
            for (int i = 0; i < size; ++i) {
                // 取出来下一个要检查的坐标
                int[] next = board.poll();
                for (int j = 0; j < 4; ++j) {
                    // 对这个坐标进行上下左右检查
                    assert next != null;
                    int nx = next[0] + dirs[j][0];
                    int ny = next[1] + dirs[j][1];
                    // 如果这个新坐标合法，而且没有访问过，就对其进行检查
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                        visited[nx][ny] = true; // 首先标记成访问过了
                        // 如果接触到了第二个小岛，那么直接返回距离计数器 ans
                        if (grid[nx][ny] == 1) return ans;
                        // 否则的话，就放到边界队列中
                        else board.add(new int[]{nx, ny});
                    }
                }
            }
            // 边界计数器加一
            ++ans;
        }
        return ans;
    }

    /**
     * 对 grid 中的一个小岛进行标记
     * @param grid      小岛描述图
     * @param visited   标记某个坐标是否访问过了
     * @param x         出发点 [x, y]
     * @param y         出发点 [x, y]
     * @param color     要标记成的颜色
     * @param board     用来保存边界的队列
     */
    private void mark(int[][] grid, boolean[][] visited, int x, int y, int color, Queue<int[]> board) {
        grid[x][y] = color;
        // 判断是不是边界，是的话就直接放到队列中
        if (x == 0 || y == 0 || x == grid.length - 1 || y == grid[0].length - 1 ||
                grid[x + 1][y] == 0 || grid[x - 1][y] == 0 || grid[x][y + 1] == 0 || grid[x][y - 1] == 0) {
            board.add(new int[]{x, y});
        }
        // 对 [x, y] 上下左右四个方向进行拓展检查
        for (int i = 0; i < 4; ++i) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            // 对小岛进行扩展查找
            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && !visited[nx][ny] && grid[nx][ny] == 1) {
                visited[nx][ny] = true;
                mark(grid, visited, nx, ny, color, board);
            }
        }
    }
}
