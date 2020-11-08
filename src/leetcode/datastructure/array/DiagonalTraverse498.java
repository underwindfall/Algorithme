package leetcode.datastructure.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode-cn.com/problems/diagonal-traverse
public class DiagonalTraverse498 {
    // 每次都从反斜对角线读取数组
    // 奇数是数次不变
    // 偶数的时候需要把数组翻转过来
    //time O((M+N)*M) = > O(M*N)
    //espace O(M) max(m,n) = M
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        int M = matrix.length;
        int N = matrix[0].length;
        int k = 0;
        int[] ans = new int[M * N];

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < N + M - 1; i++) {
            result.clear();
            int m = i < N ? 0 : i - N + 1;
            int n = i < N ? i : N - 1;

            while (m < M && n > -1) {
                result.add(matrix[m][n]);
                ++m;
                --n;
            }

            if (i % 2 == 0) {
                Collections.reverse(result);
            }

            for (int a = 0; a < result.size(); a++) {
                ans[k++] = result.get(a);
            }
        }
        return ans;
    }
}
