package leetcode.algo.dp.prefix;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target/
public class Submatrices1074 {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;
        int res = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                // matrix 变为i，j 前缀和 依行为累计的前缀和
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < cols; i++) {
            for (int j = i; j < cols; j++) {
                count.clear();
                count.put(0, 1);
                int cur = 0;
                for (int k = 0; k < rows; k++) {
                    // i == 0
                    // | x |  |      | x | x |
                    // | j |  | ==>  | x | j |
                    // | x |  |      | x | x |
                    
                    // i== 1
                    // | x |  |      |  | x |
                    // | i | j | ==> |  | j |
                    // | x |  |      |  | x |

                    cur += matrix[k][j] - (i > 0 ? matrix[k][i - 1] : 0);
                    res += count.getOrDefault(cur - target, 0);
                    count.put(cur, count.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;
    }
}
