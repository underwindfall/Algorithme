package leetcode.datastructure.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//https://leetcode-cn.com/problems/redundant-connection/
class FindRedundantConnection684 {
    // time O(N^2)
    // espace O(N)
    class DFS {
        private Map<Integer, List<Integer>> graph;
        private Set<Integer> visited;

        public int[] findRedundantConnection(int[][] edges) {
            this.graph = new HashMap<>();
            this.visited = new HashSet<>();

            // 遍历每一条边
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1];
                if (graph.containsKey(u) && graph.containsKey(v)) {
                    visited.clear();
                    // 深度优先遍历该图 判断u v 之间是否有一条路径
                    if (dfs(u, v)) {
                        return edge;
                    }
                }

                // 所有相邻顶点都找不到回路，才向图中添加这条边，由于是无向图，所以要添加两条边
                addEdge(u, v);
                addEdge(v, u);
            }
            return null;
        }

        void addEdge(int u, int v) {
            if (graph.containsKey(u)) {
                graph.get(u).add(v);
                return;
            }
            List<Integer> successors = new ArrayList<>();
            successors.add(v);
            graph.put(u, successors);
        }

        /**
         * 从 source 开始进行深度优先遍历，看看是不是能够找到一条到 target 的回路
         *
         * @param source
         * @param target
         * @return 找不到回路返回 false
         */
        boolean dfs(int source, int target) {
            if (source == target) {
                return true;
            }
            visited.add(source);
            // 遍历 source 的所有相邻顶点
            for (int adj : graph.get(source)) {
                if (!visited.contains(adj)) {
                    if (dfs(adj, target)) {
                        return true;
                    }
                }
            }
            // 所有相邻顶点都找不到，才能返回 false
            return false;
        }
    }

    // https://www.bilibili.com/video/BV1mt411J79j
    // path compression
    // time O(nlog*n) == O(1)
    class UnionFind {

        class UnionFindSet {
            private int[] parents;
            private int[] ranks;

            UnionFindSet(int n) {
                parents = new int[n + 1];
                ranks = new int[n + 1];

                for (int i = 0; i < parents.length; i++) {
                    parents[i] = i;
                    ranks[i] = 1;
                }
            }

            // Merge sets that contains u and v.
            // Return true if merged, false if u and v are already in one set.
            boolean Union(int u, int v) {
                int pu = find(u);
                int pv = find(v);
                if (pu == pv)
                    return false;
                // Meger low rank tree into high rank tree
                if (ranks[pv] > ranks[pu]) {
                    parents[pu] = pv;
                } else if (ranks[pu] > ranks[pv]) {
                    parents[pv] = pu;
                } else {
                    parents[pv] = pu;
                    ranks[pu] += 1;
                }
                return true;
            }

            int find(int u) {
                while (parents[u] != u) {
                      // Compress the path during traversal
                    parents[u] = parents[parents[u]];
                    u = parents[u];
                }
                return u;
            }
        }

        public int[] findRedundantConnection(int[][] edges) {
            UnionFindSet s = new UnionFindSet(edges.length);
            for (int[] edge : edges)
                if (!s.Union(edge[0], edge[1]))
                    return edge;
            return null;
        }
    }
}