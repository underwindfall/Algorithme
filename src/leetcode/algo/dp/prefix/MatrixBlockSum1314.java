package leetcode.algo.dp.prefix;

// https://leetcode-cn.com/problems/matrix-block-sum/
class MatrixBlockSum1314 {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int row = mat.length, col = mat[0].length;
        int[][] prefix = new int[row + 1][col + 1];
        for (int i = 1; i <= row; ++i) {
            for (int j = 1; j <= col; ++j) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        //不讨论prefix多出来的行列的话，下面是计算公式：
        //res[i][j] = pre[i+k][j+k] - pre[i+k][j-k-1] - pre[i-k-1][j-k] + matrix[i-k-1][j-k-1]
        int[][] res = new int[row][col];
        int x0 = 0, y0 = 0, x1 = 0, y1 = 0;//(x0,y0)为(i-k,j-k) (x1,y1)为(i+k,j+k)
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {            
                x0 = Math.max(0, i - K);
                y0 = Math.max(0, j - K);
                x1 = Math.min(row, i + K + 1);
                y1 = Math.min(col, j + K + 1);
                
                res[i][j] = prefix[x1][y1] - prefix[x1][y0] - prefix[x0][y1] + prefix[x0][y0];
            }
        }
        return res;
    }
}