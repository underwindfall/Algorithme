package training.array;

/* 
https://leetcode-cn.com/problems/zero-matrix-lcci/solution/
零矩阵
编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。

 

示例 1：

输入：
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出：
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
示例 2：

输入：
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出：
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
] */


public class SetZeroArray {
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
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

    public static void main(String[] args) {
        int[][] origin = new int[][] { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        setZeroes(origin);
        for (int i = 0; i < origin.length; i++) {
            StringBuilder stringBuilder = new StringBuilder("");
            for (int j = 0; j < origin[i].length; j++) {
                stringBuilder.append(origin[i][j] + ",");
            }
            System.out.println(stringBuilder + "\n");
        }
    }
}
