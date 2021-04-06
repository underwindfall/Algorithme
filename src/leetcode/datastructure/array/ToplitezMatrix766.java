package leetcode.datastructure.array;

import java.util.HashMap;
import java.util.Map;

//找规律
// https://leetcode-cn.com/problems/toeplitz-matrix/description/
public class ToplitezMatrix766 {

    // 对于对角线上的元素来说，如果当前元素不是第一个出现的元素，那么它前面的元素一定在当前元素的左上角。可以推出，对于位于坐标 (r, c) 上的元素
    // 只需要检查 r == 0 OR c == 0 OR matrix[r-1][c-1] == matrix[r][c] 就可以了。
    // time O(M*N)
    // espace O(1)
    class checkLeftNeigbor {
        public boolean isToeplitzMatrix(int[][] matrix) {
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    if (row > 0 && col > 0 && matrix[row - 1][col - 1] != matrix[row][col]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    // 思路 如果两个元素是对角线的元素则他们的行数-列数的差值相等
    // time O(M*N)
    // espace O(M+N)
    class Diagnaol {
        public boolean isToeplitzMatrix(int[][] matrix) {
            Map<Integer, Integer> groups = new HashMap<>();
            for (int r = 0; r < matrix.length; r++) {
                for (int c = 0; c < matrix[0].length; c++) {
                    if (!groups.containsKey(r - c)) {
                        groups.put(r - c, matrix[r][c]);
                    } else if (groups.get(r - c) != matrix[r][c]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    // 这种方法会很慢
    class Recursive {
        public boolean isToeplitzMatrix(int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                if (!isDiagonalEqual(matrix, i, 0, matrix[i][0])) {
                    return false;
                }
            }
            for (int i = 0; i < matrix[0].length; i++) {
                if (!isDiagonalEqual(matrix, 0, i, matrix[0][i])) {
                    return false;
                }
            }
            return true;
        }

        private boolean isDiagonalEqual(int[][] array, int r, int c, int target) {
            if (r >= 0 && r < array.length && c >= 0 && c < array[0].length) {
                if (array[r][c] != target) {
                    return false;
                } else {
                    return isDiagonalEqual(array, r + 1, c + 1, target);
                }
            }
            return true;
        }
    }
}
