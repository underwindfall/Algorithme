package leetcode.datastructure.queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//https://leetcode-cn.com/problems/as-far-from-land-as-possible/
//和LC1765类似
public class AsFarLandAsPossible1162 {

    // time O(M*N)
    // espace O(M*N)
    // 思路
    // 1.subproblem 对于每个海洋区域 (x, y)(x,y)，离它最近的陆地区域到它的路径要么从上方或者左方来，要么从右方或者下方来
    // 2.guessing考虑做两次动态规划，第一次从左上到右下，第二次从右下到左上，记 f(x, y)f(x,y) 为 (x, y)(x,y)
    // 距离最近的陆地区域的曼哈顿距离，则我们可以推出这样的转移方程
    // 3. 左上
    // dp(i, j) = min(dp(i - 1, j), dp(i, j - 1)) + 1
    // 右下
    // dp(i, j) = min(dp(i + 1, j), dp(i, j + 1)) + 1
    class DP {
        public int maxDistance(int[][] grid) {
            final int INF = 1000000;
            int n = grid.length;
            int[][] f = new int[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    f[i][j] = grid[i][j] == 1 ? 0 : INF;
                }
            }

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == 1) {
                        continue;
                    }
                    if (i - 1 >= 0) {
                        f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
                    }
                    if (j - 1 >= 0) {
                        f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
                    }
                }
            }

            for (int i = n - 1; i >= 0; --i) {
                for (int j = n - 1; j >= 0; --j) {
                    if (grid[i][j] == 1) {
                        continue;
                    }
                    if (i + 1 < n) {
                        f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
                    }
                    if (j + 1 < n) {
                        f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
                    }
                }
            }

            int ans = -1;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == 0) {
                        ans = Math.max(ans, f[i][j]);
                    }
                }
            }

            if (ans == INF) {
                return -1;
            } else {
                return ans;
            }
        }

    }

    /**
     * 虚拟源点
     */

    /**
     * 海洋到陆地的最大距离 <====> 陆地到附近海洋的最远距离
     * 因为我们只要先把所有的陆地都入队，然后从各个陆地同时开始一层一层的向海洋扩散，那么最后扩散到的海洋就是最远的海洋！
     * 并且这个海洋肯定是被离他最近的陆地给扩散到的！
     */
    // espace O(M*N)
    // time O(M*N)
    class BFS {
        public int maxDistance(int[][] grid) {
            int M = grid.length;
            int N = grid[0].length;
            int result = -1;
            Queue<int[]> queue = new LinkedList<>();

            for (int r = 0; r < M; r++) {
                for (int c = 0; c < N; c++) {
                    if (grid[r][c] == 1) {
                        queue.add(new int[] { r, c });
                    }
                }
            }

            // 如果没有陆地或者海洋，返回-1
            if (queue.isEmpty() || queue.size() == M * N) {
                return -1;
            }

            while (!queue.isEmpty()) {
                int size = queue.size();
                result++;
                for (int i = 0; i < size; i++) {
                    int[] node = queue.poll();
                    int row = node[0], col = node[1];
                    if (isValid(grid, row + 1, col) && grid[row + 1][col] == 0) {
                        grid[row + 1][col] = 2;
                        queue.add(new int[] { row + 1, col });
                    }
                    if (isValid(grid, row - 1, col) && grid[row - 1][col] == 0) {
                        grid[row - 1][col] = 2;
                        queue.add(new int[] { row - 1, col });
                    }
                    if (isValid(grid, row, col + 1) && grid[row][col + 1] == 0) {
                        grid[row][col + 1] = 2;
                        queue.add(new int[] { row, col + 1 });
                    }
                    if (isValid(grid, row, col - 1) && grid[row][col - 1] == 0) {
                        grid[row][col - 1] = 2;
                        queue.add(new int[] { row, col - 1 });
                    }
                }
            }

            return result;
        }

        boolean isValid(int[][] matrix, int row, int col) {
            return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
        }
    }

    /**
     * 从海洋出发
     * // time O(n^4)
     * //space O(n^2)
     */
    class BFSFromSea {
        public int maxDistance(int[][] grid) {
            int n = grid.length;
            int ans = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        ans = Math.max(ans, bfs(i, j, grid));
                    }
                }
            }
            return ans;
        }

        int bfs(int row, int col, int[][] grid) {
            int n = grid.length;
            int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
            Queue<int[]> q = new LinkedList<>();
            Map<Integer, Integer> map = new HashMap<>();
            q.offer(new int[] { row, col });
            map.put(n * row + col, 0);
            while (!q.isEmpty()) {
                int[] poll = q.poll();
                int dx = poll[0], dy = poll[1];
                int step = map.get(dx * n + dy);
                if (grid[dx][dy] == 1) {
                    return step;
                }
                for (int[] dir : dirs) {
                    int nx = dx + dir[0], ny = dy + dir[1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                        continue;
                    int key = nx * n + ny;
                    if (map.containsKey(key))
                        continue;
                    q.offer(new int[] { nx, ny });
                    map.put(key, step + 1);
                }
            }
            return -1;
        }
    }
}
