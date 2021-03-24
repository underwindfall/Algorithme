package leetcode.algo.dp.matrix;

//https://leetcode-cn.com/problems/integer-break/
public class IntegerBreak343 {

    // dp[i] 表示正整数 i 拆分成的整数的最大乘积
    // 前面的代码中的正整数 n 变成了这里的正整数 i，用指针 j 去划分 i，分成了 j 和 i - j。
    // 遍历所有的 j，i−j 可以选择拆或者不拆，不拆就是 i−j ，拆就是 dp[i−j]，其实就是对 i - j 调用的结果（子问题的解)
    class Dp {
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 1;
            for (int i = 3; i <= n; i++) {
                dp[i] = 0;
                // 对于数字 i，它可以分为两份：j 和 i-j，j 的范围是 1 到 i-j
                for (int j = 1; j <= i - j; j++) {
                    //dp[i - j] * j -> 拆分
                    // j * (i - j） 不拆分情况下的值
                    dp[i] = Math.max(dp[i], Math.max(dp[i - j] * j, j * (i - j)));
                }
            }
            return dp[n];
        }
    }

    class Recursive {
        public int integerBreak(int n) {
            int res = 0;
            for (int i = 1; i < n; i++) {
                res = Math.max(res, Math.max(i * (n - i), i * integerBreak(n - i)));
            }
            return res;
        }
    }
}
