package leetcode.algo.bfs;

import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/trapping-rain-water-ii/
public class TrapRainWaterII407 {
    // time O(n*m)
    // space O(n*m)
    public int trapRainWater(int[][] heightMap) {
        // 先看最外围 因为不能存水 然后看每个格子的上下左右 取其中小于当前格子的
        // 然后在向里看一层
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    pq.add(new int[] { i, j, heightMap[i][j] });
                    visited[i][j] = true;
                }
            }
        }
        int res = 0;
        int[][] dir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        while (!pq.isEmpty()) {
            int[] min = pq.poll();
            for (int i = 0; i < dir.length; i++) {
                int x = min[0] + dir[i][0];
                int y = min[1] + dir[i][1];

                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                    int bucketHeight = heightMap[x][y];
                    if (min[2] > bucketHeight) {
                        res += min[2] - bucketHeight;
                    }
                    visited[x][y] = true;
                    pq.add(new int[] { x, y, Math.max(min[2], bucketHeight) });
                }
            }
        }
        return res;
    }
}
