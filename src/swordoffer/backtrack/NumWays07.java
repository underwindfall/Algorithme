package swordoffer.backtrack;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/chuan-di-xin-xi/
public class NumWays07 {
    // dfs 讲题目转化成有向图的解法
    // time O(N^K)
    // espace O(n + m + k)
    class DFS {
        int ways = 0;

        public int numWays(int n, int[][] relation, int k) {
            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                edges.add(new ArrayList<>());
            }
            for (int[] edge : relation) {
                int src = edge[0], dst = edge[1];
                edges.get(src).add(dst);
            }
            dfs(0, 0, edges, n, k);
            return ways;
        }

        void dfs(int index, int depth, List<List<Integer>> edges, int target, int length) {
            if (depth == length) {
                if (index == target - 1) {
                    ways++;
                }
                return;
            }

            List<Integer> list = edges.get(index);
            for (int nextIndex : list) {
                dfs(nextIndex, depth + 1, edges, target, length);
            }
        }
    }

    //time O(k *m)
    //espace O(k*n)
    // 转行成计数问题
    // 通过dp(i, j) 第i轮 转递到j 的方案数
    // dp(0, 0) = 1 第0轮 只有转递到0 方案数为1 其余为0
    // dp(0, j) = 0
    // dp(i + 1, dst) = dp(i, src') + dp(i, src'') +..... dp(i, src'''') // 所有的i之和
    class DP {
        public int numWays(int n, int[][] relation, int k) {
            int[][] dp = new int[k + 1][n];
            dp[0][0] = 1;
            for (int i = 0; i < k; i++) {
                for (int[] edge : relation) {
                    int src = edge[0], dst = edge[1];
                    dp[i + 1][dst] += dp[i][src];
                }
            }
            return dp[k][n - 1];
        }
    }
}
