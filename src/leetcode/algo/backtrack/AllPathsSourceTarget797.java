package leetcode.algo.backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//https://leetcode-cn.com/problems/all-paths-from-source-to-target/
public class AllPathsSourceTarget797 {
    // time O(n * 2^n)
    // space O(n)
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<Integer>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        stack.offerLast(0);
        dfs(graph, 0, graph.length - 1);
        return res;
    }

    void dfs(int[][] graph, int x, int n) {
        if (x == n) {
            res.add(new ArrayList<>(stack));
        }
        for (int adj : graph[x]) {
            stack.offerLast(adj);
            dfs(graph, adj, n);
            stack.pollLast();
        }
    }

}
