package swordoffer;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
public class LCOF13 {
    // time O(n * m * 4)
    // space O(n * m)
    class DFS {
        public int movingCount(int m, int n, int k) {
            int count = 0;
            boolean[][] visited = new boolean[m][n];
            dfs(m, n, 0, 0, k, visited);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) {
                        count++;
                    }
                }
            }
            return count;
        }

        void dfs(int m, int n, int row, int col, int k, boolean[][] visited) {
            if (row < 0 || row >= m || col < 0 || col >= n) {
                return;
            }
            if (visited[row][col]) {
                return;
            }
            if (count(row) + count(col) > k) {
                return;
            }
            if (row == m - 1 && col == n - 1) {
                visited[row][col] = true;
                return;
            }
            visited[row][col] = true;
            dfs(m, n, row + 1, col, k, visited);
            dfs(m, n, row - 1, col, k, visited);
            dfs(m, n, row, col + 1, k, visited);
            dfs(m, n, row, col - 1, k, visited);
        }

        int count(int n) {
            int sum = 0;
            while (n != 0) {
                sum += n % 10;
                n /= 10;
            }
            return sum;
        }
    }

    // time O(n * m * 4)
    // espace O(n * m)
    class BFS {
        public int movingCount(int m, int n, int k) {
            if (k == 0) {
                return 1;
            }

            Queue<int[]> q = new LinkedList<>();

            boolean[][] vis = new boolean[m][n];

            q.offer(new int[] { 0, 0 });

            int ans = 0;

            while (!q.isEmpty()) {
                int[] cell = q.poll();
                int x = cell[0], y = cell[1];
                if (!inArea(x, y, vis) || vis[x][y] || sum(x) + sum(y) > k) {
                    continue;
                }

                vis[x][y] = true;
                q.offer(new int[] { x - 1, y });
                q.offer(new int[] { x + 1, y });
                q.offer(new int[] { x, y + 1 });
                q.offer(new int[] { x, y - 1 });
                ans++;
            }
            return ans;
        }

        boolean inArea(int row, int col, boolean[][] vis) {
            return row >= 0 && row < vis.length && col >= 0 && col < vis[0].length;
        }

        int sum(int x) {
            int res = 0;
            while (x != 0) {
                res += x % 10;
                x /= 10;
            }
            return res;
        }
    }
}
