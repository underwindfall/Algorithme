package leetcode.datastructure.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode-cn.com/problems/possible-bipartition/
public class PossibleBipartition886 {
    // dfs
    // time O(N+E)
    // espace O(N+E)
    List<List<Integer>> graph;
    Map<Integer, Integer> color;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        // dislikes 必定在一组
        graph = new ArrayList<>(n + 1);
        color = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : dislikes) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for (int node = 1; node <= n; node++) {
            if (!color.containsKey(node) && !dfs(node, 0)) {
                return false;
            }
        }

        return true;
    }

    boolean dfs(int node, int c) {
        if (color.containsKey(node)) {
            return color.get(node) == c;
        }
        color.put(node, c);
        for (int adj : graph.get(node)) {
            if (!dfs(adj, c ^ 1)) {
                return false;
            }
        }
        return true;
    }
}
