package swordoffer.backtrack;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
public class MovingCount13 {
    // time O(N*M)
    // espace O(N*M)
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int count = 0;

        dfs(0, 0, k, visited);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    void dfs(int row, int col, int k, boolean[][] visited) {
        if (!inArea(row, col, visited)) {
            return;
        }

        if (visited[row][col]) {
            return;
        }

        if (sum(row) + sum(col) > k) {
            return;
        }

        visited[row][col] = true;
        dfs(row - 1, col, k, visited);
        dfs(row + 1, col, k, visited);
        dfs(row, col - 1, k, visited);
        dfs(row, col + 1, k, visited);
    }

    boolean inArea(int row, int col, boolean[][] visited) {
        return row >= 0 && row < visited.length && col >= 0 && col < visited[0].length;
    }

    int sum(int x) {
        if (x == 100)
            return 1;
        if (x <= 9)
            return x;
        return x / 10 + x % 10;
    }

    // time O(n * m)
    // space O(n * m)
    class BFS {
        class Solution {
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
}
