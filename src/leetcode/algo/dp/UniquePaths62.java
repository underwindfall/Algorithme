package leetcode.algo.dp;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/unique-paths/
public class UniquePaths62 {

    class DP {
        // 从终点出发 到起点
        // time O(M*N)
        // espace O(M*N)
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            // 第一行都赋予 1
            for (int i = 0; i < m; ++i) {
                dp[i][0] = 1;
            }
            // 第一列都赋予 1
            for (int j = 0; j < n; ++j) {
                dp[0][j] = 1;
            }
            // 两个for循环推导，对于(i,j)来说，只能由上方或者左方转移过来
            for (int i = 1; i < m; ++i) {
                for (int j = 1; j < n; ++j) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return dp[m - 1][n - 1];
        }

    }

    // bottom
    class DFS {
        // time O(M*N)
        // espace O(M*N)
        public int uniquePaths(int m, int n) {
            return dfs(new HashMap<Pair, Integer>(), 0, 0, m, n);
        }

        private int dfs(Map<Pair, Integer> cache, int i, int j, int m, int n) {
            Pair p = new Pair(i, j);
            // 如果(i,j)在缓存中则直接返回
            if (cache.containsKey(p)) {
                return cache.get(p);
            }
            // 到达边界时，返回 1
            if (i == m - 1 || j == n - 1) {
                return 1;
            }
            // 继续递归调用，往下i+1，往右j+1
            cache.put(p, dfs(cache, i + 1, j, m, n) + dfs(cache, i, j + 1, m, n));
            return cache.get(p);
        }

        class Pair {
            public int first;
            public int second;

            Pair(int first, int second) {
                this.first = first;
                this.second = second;
            }
        }
    }

}
