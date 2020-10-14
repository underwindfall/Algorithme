package training.array05;

/* 
对角线遍历
给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。

 

示例:

输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

输出:  [1,2,4,7,5,3,6,8,9]
 */
public class DiagonalOrderArray {
    public static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int m = matrix.length, n = matrix[0].length;
        int[] result = new int[m * n];

        return result;
    }

    public static void main(String[] args) {
        int[] result = findDiagonalOrder(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
        for (int i : result) {
            System.out.println(i + ",");
        }
    }
}
