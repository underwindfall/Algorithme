package leetcode.datastructure.queue;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/flood-fill/
public class FloodFill733 {
    // time O(M*N)
    // espace O(M*N)
    class DFS {
        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            // 岛屿问题
            int originColor = image[sr][sc];
            // 如果颜色相同则不处理
            if (originColor != newColor) {
                dfs(image, sr, sc, newColor, originColor);
            }
            return image;
        }

        void dfs(int[][] image, int i, int j, int newColor, int originColor) {
            if (!inArea(image, i, j)) {
                return;
            }
            if (image[i][j] == originColor) {
                image[i][j] = newColor;
                dfs(image, i - 1, j, newColor, originColor);
                dfs(image, i + 1, j, newColor, originColor);
                dfs(image, i, j - 1, newColor, originColor);
                dfs(image, i, j + 1, newColor, originColor);
            }
        }

        boolean inArea(int[][] image, int i, int j) {
            return i >= 0 && i < image.length && j >= 0 && j < image[0].length;
        }
    }

    // time O(M*N)
    // espace O(M*N)
    class BFS {

        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            // 岛屿问题
            int originColor = image[sr][sc];
            if (originColor == newColor) {
                return image;
            }

            int n = image.length, m = image[0].length;
            Queue<int[]> queue = new LinkedList<int[]>();
            queue.offer(new int[] { sr, sc });
            image[sr][sc] = newColor;
            while (!queue.isEmpty()) {
                int sz = queue.size();
                for (int i = 0; i < sz; i++) {
                    int[] paint = queue.poll();
                    int r = paint[0], c = paint[1];
                    if (r + 1 >= 0 && r + 1 < n && c >= 0 && c < m && image[r + 1][c] == originColor) {
                        queue.offer(new int[] { r + 1, c });
                        image[r + 1][c] = newColor;
                    }
                    if (r - 1 >= 0 && r - 1 < n && c >= 0 && c < m && image[r - 1][c] == originColor) {
                        queue.offer(new int[] { r - 1, c });
                        image[r - 1][c] = newColor;
                    }
                    if (r >= 0 && r < n && c + 1 >= 0 && c + 1 < m && image[r][c + 1] == originColor) {
                        queue.offer(new int[] { r, c + 1 });
                        image[r][c + 1] = newColor;
                    }

                    if (r >= 0 && r < n && c - 1 >= 0 && c - 1 < m && image[r][c - 1] == originColor) {
                        queue.offer(new int[] { r, c - 1 });
                        image[r][c - 1] = newColor;
                    }

                }
            }
            return image;
        }
    }
}
