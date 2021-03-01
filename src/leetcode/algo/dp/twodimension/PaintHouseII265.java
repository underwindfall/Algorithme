package leetcode.algo.dp.twodimension;

// https://leetcode-cn.com/problems/paint-house-ii/
public class PaintHouseII265 {
    public int minCostII(int[][] costs) {
        // n个房子
        int n = costs.length;
        if (n == 0)
            return 0;
        // k种颜色的油漆
        int k = costs[0].length;
        if (k == 0)
            return 0;
        // 至少1种颜色，1间房子
        if (k == 1) {
            if (n == 1) {
                return costs[0][0];
            } else {
                // 不可能完成任务
                return 0;
            }
        }

        // 至此，至少有2种颜色
        // 相邻两个房子的颜色不同，求最小花费
        // f[i][j]表示将房子[0..i]刷完，并且i号房子是颜色j的最小花费
        int[][] f = new int[n][k];
        int preColorMin = 0;
        int preColorMin2 = 0;
        for (int i = 0; i < n; i++) {
            int costMin = Integer.MAX_VALUE;// 记录当前轮，粉刷完[0..i]的最小花费
            int colorMin = k - 1;// 记录最小花费下，i号房的颜色
            int costMin2 = Integer.MAX_VALUE;// 记录当前轮，粉刷完[0..i-1]的次最小花费
            int colorMin2 = k - 1;// 记录次最小花费下，i号房的颜色

            for (int j = 0; j < k; j++) {
                if (i == 0) {
                    f[i][j] = costs[i][j];
                } else {
                    // 当前颜色j不是前一轮的最小颜色，就让前一个房间取最小颜色
                    // 否则，让前一个房间粉刷为次最小颜色
                    // 最便宜颜色的正数第一或者正数第二
                    f[i][j] = costs[i][j] + (j != preColorMin ? f[i - 1][preColorMin] : f[i - 1][preColorMin2]);
                }

                if (f[i][j] < costMin) {
                    // 最小变次最小
                    colorMin2 = colorMin;
                    costMin2 = costMin;
                    // 更新最小
                    colorMin = j;
                    costMin = f[i][j];
                } else if (f[i][j] < costMin2) {
                    // 更新次最小
                    colorMin2 = j;
                    costMin2 = f[i][j];
                }
            }
            // 更新之前一层房子的最小值和次小值
            preColorMin = colorMin;
            preColorMin2 = colorMin2;
        }
        return f[n - 1][preColorMin];
    }
}
