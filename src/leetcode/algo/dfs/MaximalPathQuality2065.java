package leetcode.algo.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode-cn.com/problems/maximum-path-quality-of-a-graph/
public class MaximalPathQuality2065 {
    // dfs
    int ans = 0;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        // List<Integer> path=new ArrayList<>();//用作debug
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], time = edge[2];
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(u).add(new int[] { v, time });
            graph.get(v).add(new int[] { u, time });
        }
        dfs(0, 0, 0, graph, values, maxTime);
        return ans;
    }

    void dfs(int startN, int currValue, int currTime, Map<Integer, List<int[]>> graph, int[] values, int maxTime) {
        // path.add(startN);
        int value = values[startN];
        currValue += value;
        values[startN] = 0;
        if (startN == 0) {
            ans = Math.max(ans, currValue);
        }
        if (graph.get(startN) == null) {
            return;
        } else {
            for (int[] next : graph.get(startN)) {
                if (next[1] + currTime > maxTime) {
                    continue;
                }
                dfs(next[0], currValue, currTime + next[1], graph, values, maxTime);
            }
        }
        // 还原现场
        // path.remove(path.size()-1);
        values[startN] = value;
    }
}
