package leetcode.datastructure.stack;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/01-matrix/
public class UpdateMatrix542 {
    class Solution {
        public int[][] updateMatrix(int[][] matrix) {
             // 首先将所有的 0 都入队，并且将 1 的位置设置成 -1，表示该位置是 未被访问过的 1
            Queue<int[]> q = new LinkedList<int[]>();
            int n = matrix.length, m = matrix[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (matrix[i][j] == 0) {
                        q.offer(new int[] {i, j});
                    } else {
                        matrix[i][j] = -1;
                    }
                }
            }
            bfs(matrix, q);
            return matrix;
        }
    
        void bfs(int[][] matrix, Queue<int[]> q) {
            int n = matrix.length, m = matrix[0].length;
            while(!q.isEmpty()) {
                int sz = q.size();
                for (int i = 0; i < sz; i++) {
                    int[] node = q.poll();
                    int r = node[0], c = node[1];
                    if (r + 1 >= 0 && r + 1 < n && c >= 0 && c < m && matrix[r + 1][c] == -1) {
                        matrix[r + 1][c] = matrix[r][c] + 1;
                        q.offer(new int[] {r + 1, c});
                    }
                    if (r - 1 >= 0 && r - 1 < n && c >= 0 && c < m && matrix[r - 1][c] == -1) {
                        matrix[r - 1][c] = matrix[r][c] + 1;
                        q.offer(new int[] {r - 1, c});
                    }
                    if (r >= 0 && r < n && c + 1 >= 0 && c + 1 < m && matrix[r][c + 1] == -1) {
                        matrix[r][c + 1] = matrix[r][c] + 1;
                        q.offer(new int[] {r, c + 1});
                    }
                    if (r >= 0 && r < n && c - 1 >= 0 && c - 1 < m && matrix[r][c - 1] == -1) {
                        matrix[r][c - 1] = matrix[r][c] + 1;
                        q.offer(new int[] {r, c - 1});
                    }
                }
            }
        }
    }
}