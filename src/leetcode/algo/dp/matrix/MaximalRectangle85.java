package leetcode.algo.dp.matrix;

import java.util.Stack;

// https://leetcode-cn.com/problems/maximal-rectangle/
public class MaximalRectangle85 {
    class IncreStack {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;
            if (m == 0) {
                return 0;
            }
            int n = matrix[0].length;
            int[][] left = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                    }
                }
            }

            int ret = 0;
            for (int j = 0; j < n; j++) { // 对于每一列，使用基于柱状图的方法
                int[] up = new int[m];
                int[] down = new int[m];

                Stack<Integer> stack = new Stack<Integer>();
                for (int i = 0; i < m; i++) {
                    while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                        stack.pop();
                    }
                    up[i] = stack.isEmpty() ? -1 : stack.peek();
                    stack.push(i);
                }
                stack.clear();
                for (int i = m - 1; i >= 0; i--) {
                    while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                        stack.pop();
                    }
                    down[i] = stack.isEmpty() ? m : stack.peek();
                    stack.push(i);
                }

                for (int i = 0; i < m; i++) {
                    int height = down[i] - up[i] - 1;
                    int area = height * left[i][j];
                    ret = Math.max(ret, area);
                }
            }
            return ret;
        }
    }

    class DP {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;

            if (m == 0) {
                return 0;
            }
            int n = matrix[0].length;
            int res = 0;
            int dp[][][] = new int[m][n][3];

            // 左上角
            if (matrix[0][0] == '1') {
                dp[0][0][0] = 1;
                dp[0][0][1] = 1;
                dp[0][0][2] = 1;
                res = Math.max(res, dp[0][0][0]);
            }

            // 第一行
            for (int i = 1; i < n; i++) {
                if (matrix[0][i] == '1') {
                    dp[0][i][0] = dp[0][i - 1][0] + 1;
                    dp[0][i][1] = 1;
                    dp[0][i][2] = dp[0][i][0];
                    res = Math.max(res, dp[0][i][2]);
                }
            }
            // 第一列
            for (int i = 1; i < m; i++) {
                if (matrix[i][0] == '1') {
                    dp[i][0][0] = 1;
                    dp[i][0][1] = dp[i - 1][0][1] + 1;
                    dp[i][0][2] = dp[i][0][1];
                    res = Math.max(res, dp[i][0][2]);
                }
            }

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        dp[i][j][0] = dp[i][j - 1][0] + 1; // 向左连续1的个数
                        dp[i][j][1] = dp[i - 1][j][1] + 1;// 向上连续1的个数

                        // 计算面积
                        int col_min = dp[i][j][0];
                        int row = dp[i][j][1];

                        for (int k = 1; k <= row; k++) {// 高度递增
                            col_min = Math.min(col_min, dp[i - k + 1][j][0]); // 宽度取最小
                            dp[i][j][2] = Math.max(dp[i][j][2], col_min * k);
                        }
                        res = Math.max(res, dp[i][j][2]);

                    }
                }
            }

            return res;
        }
    }
}
