package leetcode.datastructure.graph;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/find-eventual-safe-states/
public class EventualSafeNodes802 {
    // time O(V+E)
    // espace O(V+E)

    private int[][] g;
    private int n;

    private enum State {
        UNKNWON, VISITIING, UNSAFE, SAFE
    }

    private State states[];

    public List<Integer> eventualSafeNodes(int[][] graph) {
        g = graph;
        n = g.length;
        states = new State[n];
        for (int i = 0; i < n; i++) {
            states[i] = State.UNKNWON;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dfs(i) == State.SAFE) {
                ans.add(i);
            }
        }
        ans.sort((a, b) -> a - b);
        return ans;
    }

    State dfs(int cur) {
        if (states[cur] == State.VISITIING) {
            return states[cur] = State.UNSAFE;
        }
        if (states[cur] != State.UNKNWON) {
            return states[cur];
        }
        for (int nxt : g[cur]) {
            if (dfs(nxt) == State.UNSAFE) {
                return states[cur] = State.UNSAFE;
            }
        }
        return states[cur] = State.SAFE;
    }

    // time O(V + E)
    // espace O(V + E)
    class DFS {
        /**
        * 使用 Boolean 利用了 null 表示还未计算出结果
        * true 表示从当前顶点出发的所有路径有回路
        * false 表示从当前顶点出发的所有路径没有回路
        */
        private Boolean[] visited;

        public List<Integer> eventualSafeNodes(int[][] graph) {
            // node  数量
            int len = graph.length;
            visited = new Boolean[len];
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                if (dfs(i, graph)) {
                    continue;
                }
                result.add(i);
            }
            return result;
        }

        boolean dfs(int source, int[][] graph) {
            if (visited[source] != null) {
                return visited[source];
            }
            // 所有的source 出发的路径有回路
            visited[source] = true;
            // 节点source的所有后继节点 都不能回到自己就是安全的
            for (int successor : graph[source]) {
                if (dfs(successor, graph)) {
                    return true;
                }
            }
            // 重置source
            visited[source] = false;
            return false;
        }
    }
}
