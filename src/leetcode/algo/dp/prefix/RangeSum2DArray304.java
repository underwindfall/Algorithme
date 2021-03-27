package leetcode.algo.dp.prefix;

//https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
class RangeSum2DArray304 {

    /**
     * 利用前缀和求解
     * 
     * row1, col1 作为左上起点时候
     * sum[row1, col1,row2, col2] = sum[0,0,row2, col2] - sum[0,0,row1, col2] - sum[0,0,row2, col1] + sum[0,0,row1,col1]
     * 
     */
    class PrefixDp {
        private int[][] dp ;
        public PrefixDp(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0)
                return;
            dp = new int[matrix.length + 1][matrix[0].length  + 1];
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
        }
    }

    /**
     * time O(n*m) espace O(1)
     */
    class BruteForce {
        private int[][] data;

        public BruteForce(int[][] matrix) {
            this.data = matrix;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    sum += data[i][j];
                }
            }
            return sum;
        }
    }
}