package leetcode.algo.dp.matrix;

// https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/
public class MaxSumSubmatrix363 {
    // 固定 左右 列的情况下 不断增加行数 判断出 当前小矩阵的值是否大于k
    // l,r -> row++ -> 相当于在连续的数段上求解问题 ----> 二分法
    class DPBinarySearch {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;

            for (int l = 0; l < cols; l++) { // 枚举左边界
                int[] rowSum = new int[rows]; // 左边界改变才算区域的重新开始
                for (int r = l; r < cols; r++) { // 枚举右边界
                    for (int i = 0; i < rows; i++) { // 按每一行累计到 dp
                        rowSum[i] += matrix[i][r];
                    }
                    max = Math.max(max, dpmax(rowSum, k));
                    if (max == k) return k; // 尽量提前
                }
            }
            return max;
        }   
        
        // 在数组 arr 中，求不超过 k 的最大值
        int dpmax(int[] arr, int k) {
            int max = Integer.MIN_VALUE;
            for (int l = 0; l < arr.length; l++) {
                int sum = 0;
                for (int r = l; r < arr.length; r++) {
                    sum += arr[r];
                    if (sum > max && sum <= k) max = sum;
                }
            }
            return max;
        }
        
    }

    //思路
    //子问题 i2,j2 右下角节点的值 可被i1,j1,i2 - 1,j2 + 矩阵i1,j1,i2,j2 - 1 - 矩阵i1,j1,i2 - 1,j2 - 1+matrix[i2 - 1][j2 - 1]
    //dp(i1,j1,i2,j2) = dp(i1,j1,i2 - 1,j2) + dp(i1,j1,i2,j2 - 1) - dp(i1,j1,i2 - 1,j2 - 1) + matrix[i2 - 1][j2 - 1]
    // dp(i1,j1) = dp(i2-1,j2) + dp(i2,j2-1)-dp(i2-1)(j2-1)+matrix(i2-1)(j2-1)
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
        for (int i1 = 1; i1 <= rows; i1++) {
            for (int j1 = 1; j1 <= cols; j1++) {
                int[][] dp = new int[rows + 1][cols + 1]; // renew // from (i1,j1) to (i2,j2)
                dp[i1][j1] = matrix[i1 - 1][j1 - 1];
                for (int i2 = i1; i2 <= rows; i2++) {
                    for (int j2 = j1; j2 <= cols; j2++) {
                        dp[i2][j2] = dp[i2 - 1][j2] + dp[i2][j2 - 1] - dp[i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                        if (dp[i2][j2] <= k && dp[i2][j2] > max)
                            max = dp[i2][j2];
                    }
                }
            }
        }
        return max;
    }

}
