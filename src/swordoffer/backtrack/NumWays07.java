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
}
