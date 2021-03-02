package leetcode.algo.dp.twodimension;

// https://leetcode-cn.com/problems/toss-strange-coins/
public class ProbabilityOfHeads1230 {
    //dp[i][j] 代表 取了i枚硬币，j 枚正面 的概率
    public double probabilityOfHeads(double[] prob, int target) {
        double[][] dp = new double[prob.length + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= prob.length; i++) {
            dp[i][0] = dp[i - 1][0] * (1 - prob[i - 1]);
        }
        for (int i = 1; i <= prob.length; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j - 1] * prob[i - 1] + dp[i - 1][j] * (1 - prob[i - 1]);
            }
        }
        return dp[prob.length][target];
    }
}
