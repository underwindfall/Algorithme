package leetcode.algo.dp.doublesequence.interval;

//https://leetcode-cn.com/problems/minimum-score-triangulation-of-polygon/
public class TriangulationOfPolygon1039 {
    //1.subproblem
    // 子问题是以i，j 为底边的三角形此时他的面积。 每次切的这个三角形的面积最好就好
    // 理由 多边形中 总是存在以 i，j 唯一的三角形的 这个能组成唯一的部分
    //      m
    //     /  \
    //    i -  j
    // m 这个 时候有 从[i-1,j+1] 组合
    // 2.guessing
    // [i, j, m] 就是最小的三角形面积 S = A[i]*A[j]*A[m] 他的其他部分 剩余部分选择(i,m',j)面积
    //3. dp 公式
    //dp[i][j]=min(dp[i][m]+A[i]*A[j]*A[m]+dp[m][j])

    class dp {
        public int minScoreTriangulation(int[] A) {
            int n = A.length;
            int[][] dp = new int[n][n];
            for (int i = n - 2 - 1; i >= 0; i--) {
                for (int j = i + 2; j < n; j++) {
                    // m范围 在i +1, j-1
                    for (int m = i + 1; m < j; m++) {
                        if (dp[i][j] == 0) {
                            dp[i][j] = A[i] * A[j] * A[m] + dp[i][m] + dp[m][j];
                        } else {
                            dp[i][j] = Math.min(dp[i][j], A[i] * A[j] * A[m] + dp[i][m] + dp[m][j]);
                        }
                    }
                }
            }
            return dp[0][n - 1];
        }
    }


    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];

        for (int d = 2; d < n; d++) {
            for (int i = 0; i + d < n; i++) {
                int j = i + d;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; ++k) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
                }
            }
        }

        return dp[0][n - 1];
    }


}
