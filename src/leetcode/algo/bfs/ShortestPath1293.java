package leetcode.algo.bfs;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
public class ShortestPath1293 {
    // time O(N * M)
    // space O(N * M)
    class Solution {
        int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, };

        public int shortestPath(int[][] grid, int k) {
            int m = grid.length, n = grid[0].length;
            if (k >= m + n - 3) {
                return m + n - 2;
            }

            // flag[m][n][k] 表示到达位置 m,n ，且还剩余k个消除障碍物的机会
            boolean[][][] flag = new boolean[m][n][k + 1];
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[] { 0, 0, k });

            flag[0][0][k] = true;
            int step = 0;

            while (!q.isEmpty()) {
                step++;
                int size = q.size();

                while (size-- > 0) {
                    int[] currState = q.poll();
                    int currM = currState[0];
                    int currN = currState[1];
                    int currK = currState[2];

                    for (int[] direction : dirs) {
                        int nextM = currM + direction[0];
                        int nextN = currN + direction[1];
                        // 搜索位置 未越界
                        if ((nextM >= 0 && nextM < m) && (nextN >= 0 && nextN < n)) {
                            // 下一个位置nextm,nextn, 不是障碍物 ,且状态 [nextm][nextn][curk] 没被经历过
                            if (grid[nextM][nextN] == 0 && !flag[nextM][nextN][currK]) {
                                // 找到结果
                                if (nextM == m - 1 && nextN == n - 1) {
                                    return step;
                                } else {
                                    flag[nextM][nextN][currK] = true;
                                    q.add(new int[] { nextM, nextN, currK });
                                }
                            }
                            // 下一个位置 为障碍物，且当前还有移除障碍物的机会 ，
                            // 且 状态[nextm][nextn][curk - 1]没被经历过
                            else if (grid[nextM][nextN] == 1 && currK > 0 && !flag[nextM][nextN][currK - 1]) {
                                flag[nextM][nextN][currK - 1] = true;
                                q.add(new int[] { nextM, nextN, currK - 1 });
                            }
                        }

                    }
                }
            }
            return -1;
        }
    }
}
