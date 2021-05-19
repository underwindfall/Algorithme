package leetcode.datastructure.queue;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode-cn.com/problems/rotting-oranges/
public class OrangesRotting994 {

    // time O(N*M)
    // espace O(N * M)
    // BFS
    // 0 -> 空
    // 1 -> 新鲜
    // 2 -> 腐烂
    public int orangesRotting(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        int count = 0; // count 代表新鲜橘子数量

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c] == 1) {
                    count++;
                } else if (grid[r][c] == 2) {
                    queue.add(new int[] { r, c });
                }
            }
        }

        int round = 0; // round代表腐烂的轮数，或分钟数
        while (count > 0 && !queue.isEmpty()) {
            round++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] orange = queue.poll();
                int r = orange[0];
                int c = orange[1];
                // 左
                if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                    grid[r - 1][c] = 2;
                    count--;
                    queue.add(new int[] { r - 1, c });
                }
                // 右
                if (r + 1 < M && grid[r + 1][c] == 1) {
                    grid[r + 1][c] = 2;
                    count--;
                    queue.add(new int[] { r + 1, c });
                }
                // 下
                if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                    grid[r][c - 1] = 2;
                    count--;
                    queue.add(new int[] { r, c - 1 });
                }
                // 上
                if (c + 1 < N && grid[r][c + 1] == 1) {
                    grid[r][c + 1] = 2;
                    count--;
                    queue.add(new int[] { r, c + 1 });
                }
            }
        }
        if (count > 0) {
            return -1;
        } else {
            return round;
        }
    }
}
