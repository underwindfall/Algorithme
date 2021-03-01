package leetcode.algo.dp.twodimension;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/paint-house/
public class PaintHouse256 {

   

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
