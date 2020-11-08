package leetcode.datastructure.array;

public class ZeroMatrixLCCI0108 {
    /**
     * 
     * 思路一
     * 
     * 申请一个相同的M*N的空数组一个个读取
     * 
     * 满足条件就设为0
     * 
     * time: O(M*N)
     * 
     * espace:O(M*N)
     * 
     * 
     * 
     * 思路二 将二维的数组降维 以达到节省空间的需求
     * 
     * time: O(N*M) 
     * 
     * espace:O(M+N)
     * 
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] ROW = new boolean[m];
        boolean[] COL = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    ROW[i] = true;
                    COL[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (ROW[i] || COL[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
