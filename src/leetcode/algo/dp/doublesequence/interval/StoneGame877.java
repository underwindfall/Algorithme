package leetcode.algo.dp.doublesequence.interval;

import java.util.Arrays;

//https://leetcode-cn.com/problems/stone-game/
public class StoneGame877 {

    // dp(i, j) 定义为 （i，j） 区间内能够获取的最大净胜分数 他是一切的核心
    // dp(i, j ) = max(nums[i] - dp(i + 1, j), nums[j] - dp(i, j - 1) )
    class DP {
        public boolean stoneGame(int[] piles) {
            int n = piles.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                dp[i][i] = piles[i];
            }

            // 对角线遍历表
            for (int length = 1; length < n; length++) {
                for (int i = 0; i + length < n; i++) {
                    int j = i + length;
                    dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
                }
            }
            return dp[0][n - 1] > 0;
        }
    }

    class DP2 {
        // dp[i][j] 定义：区间 piles[i..j] 内先手可以获得的相对分数

        //从底下向上遍历
        public boolean stoneGame(int[] piles) {
            int len = piles.length;
            int[][] dp = new int[len][len];
            for (int i = 0; i < len; i++) {
                dp[i][i] = piles[i];
            }

            for (int i = len - 2; i >= 0; i--) {
                for (int j = i + 1; j < len; j++) {
                    dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
                }
            }
            return dp[0][len - 1] > 0;
        }

    }

    // 找规律
    // 通过罗列的方式找到了规律 可以进行记忆话递归
    // https://leetcode-cn.com/problems/stone-game/solution/ji-yi-hua-di-gui-dong-tai-gui-hua-shu-xue-jie-java/
    class MemoRecursive {
        public boolean stoneGame(int[] piles) {
            int n = piles.length;
            int[][] memo = new int[n][n];
            for (int[] row : memo) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
            return dfs(piles, 0, n - 1, memo) > 0;
        }

        // 计算的是绝对差值
        int dfs(int[] piles, int left, int right, int[][] memo) {
            if (left == right) {
                return piles[left];
            }
            if (memo[left][right] != Integer.MIN_VALUE) {
                return memo[left][right];
            }
            int chooseLeft = piles[left] - dfs(piles, left + 1, right, memo);
            int chooseRight = piles[right] - dfs(piles, left, right - 1, memo);

            // max 每次大家都是取相比下一层之差更大的
            int ans = Math.max(chooseLeft, chooseRight);
            memo[left][right] = ans;
            return ans;
        }
    }

}
