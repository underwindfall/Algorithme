package leetcode.algo.dp.matrix;

// https://leetcode-cn.com/problems/maximal-square/
public class MaximalSquare221 {
    // time O(n * m)
    // space O(n * m)
    public int maximalSquare(char[][] matrix) {
        // base condition
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }

    // time
    // space
    class DFS {
        int res = 0;

        public int maximalSquare(char[][] matrix) {
            int row = matrix.length, col = matrix[0].length;
            int[][] visited = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == '1' && visited[i][j] == 0) {
                        dfs(matrix, i, j, visited);
                    }
                }
            }
            return res * res;
        }

        int dfs(char[][] matrix, int i, int j, int[][] visited) {
            if (matrix[i][j] == '0' || visited[i][j] != 0) {
                return visited[i][j];
            }
            int right = 0, down = 0, dia = 0;
            if (j + 1 < matrix[0].length) {
                right = dfs(matrix, i, j + 1, visited);
            }
            if (i + 1 < matrix.length) {
                down = dfs(matrix, i + 1, j, visited);
            }
            if (j + 1 < matrix[0].length && i + 1 < matrix.length) {
                dia = dfs(matrix, i + 1, j + 1, visited);
            }
            visited[i][j] = Math.min(dia, Math.min(down, right)) + 1;
            res = Math.max(res, visited[i][j]);
            return visited[i][j];
        }
    }
}
