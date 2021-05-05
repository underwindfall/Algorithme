package leetcode.algo.dp.doublesequence.interval;

import java.util.Arrays;

//https://leetcode-cn.com/problems/stone-game/
public class StoneGame877 {
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
