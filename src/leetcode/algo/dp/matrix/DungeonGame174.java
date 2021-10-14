package leetcode.algo.dp.matrix;

import java.util.Arrays;

// https://leetcode-cn.com/problems/dungeon-game/
public class DungeonGame174 {

    /**
     * 题目描述：
     * 存储着整数的二维数组grid,若grid[i][j]>0,经过它可增加生命值；grid[i][j]==0,经过它不会发生任何事；grid[i][j]<0,经过它会损失对应数值生命值。
     * 最大化骑士生命值-->最大化骑士路径上的血瓶->求最大路径和 X不可取
     *  最大化骑士生命值->损失最少的生命值 √
     * 
     * 通常的方法：dp[i][j]:从(0,0)到(i,j)的最小生命值
     * 只能知道能够从左上角到达B的最小生命值，并不知道到达B时的生命值。信息量不足，算法无法做出正确状态转移。
     * 
     * 正确做法： 反向思考：dp[i][j]:从(i,j)到终点右下角，所需的最小生命值
     * 
     */
    class DFS {
        int[][] memo;

        public int calculateMinimumHP(int[][] dungeon) {
            int m = dungeon.length;
            int n = dungeon[0].length;

            memo = new int[m][n];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return dp(dungeon, 0, 0);
        }

        public int dp(int[][] grid, int i, int j) {
            int m = grid.length;
            int n = grid[0].length;

            // 1、base case
            if (i == m - 1 && j == n - 1) { // 从最末尾位置到最末尾位置，需要的生命值
                return grid[i][j] >= 0 ? 1 : -grid[i][j] + 1;
            }
            if (i == m || j == n)
                return Integer.MAX_VALUE; // 数组越界时，用来参与min比较的值

            // 2、避免重复计算
            if (memo[i][j] != -1)
                return memo[i][j];

            // 3、状态转移逻辑
            int res = Math.min(dp(grid, i, j + 1), dp(grid, i + 1, j)) - grid[i][j];

            // 4、骑士的生命值至少为1，如果相减下来是负数的话，赋值为1
            memo[i][j] = res <= 0 ? 1 : res;

            // 5、返回结果
            return memo[i][j];
        }
    }
}
