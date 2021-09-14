package leetcode.algo.bfs;

import java.util.LinkedList;
import java.util.Queue;

// time O(M*N)
// espace O(M*N)
//https://leetcode-cn.com/problems/walls-and-gates/
public class WallsAndGates286 {
    public void wallsAndGates(int[][] rooms) {
        // bfs
        // loop from gate

        int M = rooms.length, N = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (rooms[i][j] == 0) {
                    q.add(new int[] { i, j });
                }
            }
        }

        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                int[] node = q.poll();
                int row = node[0], col = node[1];
                if (inArea(rooms, row + 1, col) && rooms[row + 1][col] == Integer.MAX_VALUE) {
                    rooms[row + 1][col] = rooms[row][col] + 1;
                    q.add(new int[] { row + 1, col });
                }
                if (inArea(rooms, row - 1, col) && rooms[row - 1][col] == Integer.MAX_VALUE) {
                    rooms[row - 1][col] = rooms[row][col] + 1;
                    q.add(new int[] { row - 1, col });
                }
                if (inArea(rooms, row, col + 1) && rooms[row][col + 1] == Integer.MAX_VALUE) {
                    rooms[row][col + 1] = rooms[row][col] + 1;
                    q.add(new int[] { row, col + 1 });
                }
                if (inArea(rooms, row, col - 1) && rooms[row][col - 1] == Integer.MAX_VALUE) {
                    rooms[row][col - 1] = rooms[row][col] + 1;
                    q.add(new int[] { row, col - 1 });
                }
            }
        }
    }

    boolean inArea(int[][] area, int row, int col) {
        return row >= 0 && row < area.length && col >= 0 && col < area[0].length;
    }
}
