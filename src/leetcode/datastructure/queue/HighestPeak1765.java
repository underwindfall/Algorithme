package leetcode.datastructure.queue;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode-cn.com/problems/HighestPeak1765
//BFS see Leetcode 286
public class HighestPeak1765 {
    public int[][] highestPeak(int[][] isWater) {
        int M = isWater.length;
        int N = isWater[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] result = new int[M][N];

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (isWater[r][c] == 1) {
                    queue.add(new int[] { r, c });
                }
            }
        }

        while (!queue.isEmpty()) {
            int n = queue.size();

            for (int i = 0; i < n; i++) {
                int[] land = queue.poll();
                int r = land[0], c = land[1];
                if (r - 1 >= 0 && r - 1 < M && isWater[r - 1][c] == 0) {
                    result[r - 1][c] = result[r][c] + 1;
                    isWater[r - 1][c] = 1; // 天然的记忆化visited数组 染色法 的思想
                    queue.add(new int[] { r - 1, c });
                }

                if (r + 1 >= 0 && r + 1 < M && isWater[r + 1][c] == 0) {
                    result[r + 1][c] = result[r][c] + 1;
                    isWater[r + 1][c] = 1; // 天然的记忆化visited数组 染色法 的思想
                    queue.add(new int[] { r + 1, c });
                }

                if (c - 1 >= 0 && c - 1 < M && isWater[r][c - 1] == 0) {
                    result[r][c - 1] = result[r][c] + 1;
                    isWater[r][c - 1] = 1; // 天然的记忆化visited数组 染色法 的思想
                    queue.add(new int[] { r, c - 1 });
                }

                if (c + 1 < N && c + 1 >= 0 && isWater[r][c + 1] == 0) {
                    result[r][c + 1] = result[r][c] + 1;
                    isWater[r][c + 1] = 1; // 天然的记忆化visited数组 染色法 的思想
                    queue.add(new int[] { r, c + 1 });
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        HighestPeak1765 highestPeak1765 = new HighestPeak1765();
        highestPeak1765.highestPeak(new int[][] { { 0, 1 }, { 0, 0 } });
    }
}
