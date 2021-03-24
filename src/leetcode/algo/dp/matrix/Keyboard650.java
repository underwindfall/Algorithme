package leetcode.algo.dp.matrix;

import java.util.Arrays;

// https://leetcode-cn.com/problems/2-keys-keyboard/
public class Keyboard650 {
    // 状态：粘贴板数量clip、界面数量screen
    // base case：clip=0、screen=1
    // dp定义: 某数量 clip、screen 时所需的最小操作数
    // 状态转移方程: dp[screen][clip] = dp[screen-clip][clip] + 1
    public int minSteps(int n) {
        // dp(i, j) -> 当前A的个数是 i, 复制A的个数j 当前的操作次数是dp value
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 1001);
        }

        // 最初, 我们只有一个字符 'A'。
        dp[1][0] = 0;
        for (int i = 1; i <= n; i++) {
            int minNum = dp[i][0]; // 纪录第二层循环的最小值 下面会用到
            for (int j = 1; j <= i; j++) {
                if (i > j) {
                    dp[i][j] = dp[i - j][j] + 1; // i个A 要在i-j个A的基础上粘贴j个A
                    // System.out.println("" + dp[i][j]);
                    minNum = Math.min(minNum, dp[i][j]);
                }
                if (i == j) // 当i=j的时候 代表复制所有的"A" 肯定是在最小操作数的基础上复制，所以前面要纪录最小值
                    dp[i][j] = minNum + 1;
            }
        }
        return dp[n][n] - 1;
    }

    public static void main(String[] args) {
        System.out.println(new Keyboard650().minSteps(30));
    }
}
