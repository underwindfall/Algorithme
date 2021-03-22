package leetcode.algo.dp.matrix;

import java.util.Arrays;

// https://leetcode-cn.com/problems/dungeon-game/
public class DungeonGame174 {
    class DP {
        public int calculateMinimumHP(int[][] dungeon) {
            int n = dungeon.length, m = dungeon[0].length;
            int[][] dp = new int[n + 1][m + 1];
            for (int i = 0; i <= n; ++i) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            dp[n][m - 1] = dp[n - 1][m] = 1;
            for (int i = n - 1; i >= 0; --i) {
                for (int j = m - 1; j >= 0; --j) {
                    int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                    dp[i][j] = Math.max(minn - dungeon[i][j], 1);
                }
            }
            return dp[0][0];
        }
    }

    //
    class Dfs {
        public int calculateMinimumHP(int[][] dungeon) {
            return dfs(dungeon, dungeon.length, dungeon[0].length, 0, 0);
        }

        int dfs(int[][] dungeon, int m, int n, int i, int j) {
            // 到达🏁 递归地址
            if (i == m - 1 && j == n - 1) {
                return Math.max(1 - dungeon[i][j], 1);
            }
            // 最后一行，只能向右搜索。
            if (i == m - 1) {
                return Math.max(dfs(dungeon, m, n, i, j + 1) - dungeon[i][j], 1);
            }
            // 最后一列，只能向下搜索
            if (j == n - 1) {
                return Math.max(dfs(dungeon, m, n, i + 1, j) - dungeon[i][j], 1);
            }

            // 向下搜索 + 向右搜索，得到(i, j)点的后续路径所要求的最低血量 Math.min(dfs(i + 1, j), dfs(i, j + 1))，
            // 又因为(i, j)点本身提供血量dungeon[i][j], 因此从(i, j)开始所需的最低血量为 Math.min(dfs(i + 1, j),
            // dfs(i, j + 1)) - dungeon[i][j]
            // 因为骑士的血量不能小于1，因此要和1取个max。
            return Math.max(Math.min(dfs(dungeon, m, n, i + 1, j), dfs(dungeon, m, n, i, j + 1)) - dungeon[i][j], 1);
        }
    }
}
