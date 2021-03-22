package leetcode.algo.dp.matrix;

// https://leetcode-cn.com/problems/minimum-falling-path-sum/
public class MinFallingPathSum931 {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        // 初始化：对于首行而言，每个位置的「最小成本」就是其「矩阵值」
        for (int i = 0; i < n; i++) dp[0][i] = matrix[0][i];
         // 从第二行开始，根据题目给定的条件进行转移
         for (int i = 1; i < n; i++) {
             for (int j = 0; j < n; j++) {
                int val = matrix[i][j];
                // 下
                dp[i][j] = dp[i - 1][j] + val;
                // 斜下
                if (j - 1 >= 0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + val);
                // 向右
                if (j + 1 <  n) dp[i][j] = Math.min(dp[i][j], dp[i-1][j+1] + val);
             }
         }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) ans = Math.min(ans, dp[n-1][i]);
        return ans;
    }
}
