package training.array;

/**
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 * 
 *  
 * 
 * 示例:
 * 
 * 输入: [
 * 
 * [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ]
 * 
 * 输出: [1,2,4,7,5,3,6,8,9]
 * 
 * 解释: [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ]
 * 
 */

public class FindDiagonalOrder {

    //time :O(M*N)
    //espace:O(M*N)
    //
    static int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] nums = new int[m * n];
        if (m == 0) {
            return nums;
        }

        if (n == 0) {
            return nums;
        }
        boolean bFlag = true;
        int count = 0;
        for (int i = 0; i < m + n; i++) {
            int pm = bFlag ? m : n;
            int pn = bFlag ? n : m;
            int x = (i < pm) ? i : pm - 1;
            int y = i - x;
            while (x >= 0 && y < pn) {
                nums[count] = bFlag ? matrix[x][y] : matrix[y][x];
                count++;
                x--;
                y++;
            }
            bFlag = !bFlag;
        }
        return nums;
    }

    static int[] findDiagonalOrder1(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m * n];
        int row = 0, col = 0;
        int p = 0;
        while (p < res.length) {
            res[p++] = matrix[row][col];
            if ((row + col) % 2 == 0) {
                if (col == n - 1) {
                    row++;
                } else if (row == 0) {
                    col++;
                } else {
                    col++;
                    row--;
                }

            } else {
                if (row == m - 1) {
                    col++;
                } else if (col == 0) {
                    row++;
                } else {
                    col--;
                    row++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] result = findDiagonalOrder(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });

        for (int i : result) {
            System.out.println(i);
        }
    }
}
