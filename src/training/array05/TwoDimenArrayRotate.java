package training.array05;

//https://leetcode-cn.com/problems/rotate-matrix-lcci/solution/xuan-zhuan-ju-zhen-by-leetcode-solution/
/* 旋转矩阵
给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。

不占用额外内存空间能否做到？

 

示例 1:

给定 matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
示例 2:

给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
 */

public class TwoDimenArrayRotate {
    // espace: O(N^2)
    // time: O(N^2)
    public static int[][] rotate(int[][] matrix) {
        int N = matrix.length;
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = matrix[N - 1 - j][i];
            }
        }
        return result;
    }

    public static int[][] rotate1(int[][] matrix) {
        return matrix;
    }

    public static void main(String[] args) {
        int[][] result = rotate(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });

        for (int i = 0; i < result.length; i++) {
            StringBuilder stringBuilder = new StringBuilder("");
            for (int j = 0; j < result[i].length; j++) {
                stringBuilder.append(result[i][j] + ",");
            }
            System.out.println(stringBuilder + "\n");
        }
    }
}
