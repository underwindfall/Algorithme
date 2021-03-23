package leetcode.algo.dp.matrix;

// https://leetcode-cn.com/problems/max-submatrix-lcci/
public class MaxMatrix17_24 {

    class SameSolution363{
        int[] ans = new int[4];
        int r1, r2 = 0;
         public int[] getMaxMatrix(int[][] matrix) {
            int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
            for (int l = 0; l < cols; l++) { // 枚举左边界
                int[] rowSum = new int[rows]; // 左边界改变才算区域的重新开始
                for (int r = l; r < cols; r++) { // 枚举右边界
                    for (int i = 0; i < rows; i++) { // 按每一行累计到 dp
                        rowSum[i] += matrix[i][r];
                    }
                    if (dpmax(rowSum) > max) {
                        max = dpmax(rowSum);
                        ans[1] = l;
                        ans[3] = r;    
                        ans[0] = r1;
                        ans[2] = r2;
                    }
                }
            }
            return ans;
        }   
        
        // 在数组 arr 中，求不超过 k 的最大值
        int dpmax(int[] arr) {
            int max = Integer.MIN_VALUE;
            for (int l = 0; l < arr.length; l++) {
                int sum = 0;
                for (int r = l; r < arr.length; r++) {
                    sum += arr[r];
                    if (sum > max) {
                        max = sum;
                        r1 = l;
                        r2 = r;
                    }
                    
                }
            }
            return max;
        }
    }
    // 之前做过类似53这样的题目，求一维的最大子数组和
    // 面对着而为的时候这个题目会显的有点手足无措 想办法 二维 -》 变一维
    // 求子矩阵的和
    // 那可以理解是子矩阵的和 其实等于每列的数字相加的和 组成一个一维矩阵 那就是转换成53
    public int[] getMaxMatrix(int[][] matrix) {
        int[] ans = new int[4];// 保存最大子矩阵的左上角和右下角的行列坐标
        int N = matrix.length;
        int M = matrix[0].length;
        int[] b = new int[M];// 记录当前i~j行组成大矩阵的每一列的和，将二维转化为一维
        int sum = 0;// 相当于dp[i],dp_i
        int maxsum = Integer.MIN_VALUE;// 记录最大值
        int bestr1 = 0;
        int bestc1 = 0;// 暂时记录左上角，相当于begin

        for (int i = 0; i < N; i++) { // 以i为上边，从上而下扫描
            for (int t = 0; t < M; t++) {
                b[t] = 0; // 每次更换子矩形上边，就要清空b，重新计算每列的和
            }
            for (int j = i; j < N; j++) { // 子矩阵的下边，从i到N-1，不断增加子矩阵的高
                // 一下就相当于求一次最大子序列和
                sum = 0;// 从头开始求dp
                for (int k = 0; k < M; k++) {
                    b[k] += matrix[j][k];
                    // 我们只是不断增加其高，也就是下移矩阵下边，所有这个矩阵每列的和只需要加上新加的哪一行的元素
                    // 因为我们求dp[i]的时候只需要dp[i-1]和nums[i],所有在我们不断更新b数组时就可以求出当前位置的dp_i
                    if (sum > 0) {
                        sum += b[k];
                    } else {
                        sum = b[k];
                        bestr1 = i;// 自立门户，暂时保存其左上角
                        bestc1 = k;
                    }
                    if (sum > maxsum) {
                        maxsum = sum;
                        ans[0] = bestr1;// 更新答案
                        ans[1] = bestc1;
                        ans[2] = j;
                        ans[3] = k;
                    }
                }
            }
        }
        return ans;
    }
}
