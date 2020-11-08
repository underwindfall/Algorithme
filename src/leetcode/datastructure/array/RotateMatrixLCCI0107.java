package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/rotate-matrix-lcci/
public class RotateMatrixLCCI0107 {

    // 对折
    // 对角线交换
    // time:O(N)
    // espace:O(1)
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 对折
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = tmp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    //另一种思路是通过多余的array来补充填写
}