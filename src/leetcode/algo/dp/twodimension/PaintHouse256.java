package leetcode.algo.dp.twodimension;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/paint-house/
public class PaintHouse256 {

    // bottom up
    class DP {
        public int minCost(int[][] costs) {
            // 子问题
            // guess
            // 子问题的关系
            // dp 当前的子问题是 每个房子的刷油漆后最小的价格
            // dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j + 1]) + costs[i][j]
            if (costs.length == 0)
                return 0;
            int[][] dp = new int[costs.length][3];
            for (int i = 0; i < 3; i++) {
                // set initial value
                dp[costs.length - 1][i] = costs[costs.length - 1][i];
            }
            for (int i = costs.length - 2; i >= 0; i--) {
                dp[i][0] = Math.min(dp[i + 1][1], dp[i + 1][2]) + costs[i][0];
                dp[i][1] = Math.min(dp[i + 1][0], dp[i + 1][2]) + costs[i][1];
                dp[i][2] = Math.min(dp[i + 1][0], dp[i + 1][1]) + costs[i][2];
            }
            return Math.min(Math.min(dp[0][0], dp[0][1]), dp[0][2]);
        }
    }

    class RecursiveMemo {
        private int[][] costs;
        private Map<String, Integer> memo;

        public int minCost(int[][] costs) {
            if (costs.length == 0)
                return 0;
            this.costs = costs;
            this.memo = new HashMap<>();
            return Math.min(paintCost(0, 0), Math.min(paintCost(0, 1), paintCost(0, 2)));
        }

        int paintCost(int n, int color) {
            if (memo.containsKey(getKey(n, color))) {
                return memo.get(getKey(n, color));
            }
            int totalCost = costs[n][color];
            if (n == costs.length - 1) {
            } else if (color == 0) { // Red
                totalCost += Math.min(paintCost(n + 1, 1), paintCost(n + 1, 2));
            } else if (color == 1) {
                totalCost += Math.min(paintCost(n + 1, 0), paintCost(n + 1, 2));
            } else {
                totalCost += Math.min(paintCost(n + 1, 1), paintCost(n + 1, 0));
            }
            memo.put(getKey(n, color), totalCost);
            return totalCost;
        }

        String getKey(int n, int color) {
            return String.valueOf(n) + "-" + String.valueOf(color);
        }
    }
}
