package leetcode.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode-cn.com/problems/number-of-connected-components-in-an-undirected-graph/
public class CountComponents323 {
    // time O(N)
    // espace O(N)
    @SuppressWarnings("unchecked")
    class DFS {
        public int countComponents(int n, int[][] edges) {
            // 第 1 步：构建图
            List<Integer>[] adj = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
            }
            // 无向图，所以需要添加双向引用
            for (int[] edge : edges) {
                adj[edge[0]].add(edge[1]);
                adj[edge[1]].add(edge[0]);
            }

            // 第 2 步：开始深度优先遍历
            int count = 0;
            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(adj, i, visited);
                    count++;
                }
            }
            return count;
        }

        /**
         * @param adj     邻接表
         * @param u       从顶点 u 开始执行深度优先遍历
         * @param visited 记录某个结点是否被访问过
         */
        private void dfs(List<Integer>[] adj, int u, boolean[] visited) {
            visited[u] = true;
            List<Integer> successors = adj[u];
            for (int successor : successors) {
                if (!visited[successor]) {
                    dfs(adj, successor, visited);
                }
            }
        }
    }

    // time O(N)
    // espace O(N)
    class BFS {
        public int countComponents(int n, int[][] edges) {
            int count = 0;
            List<List<Integer>> adjList = new ArrayList<>();
            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                adjList.get(edge[0]).add(edge[1]);
                adjList.get(edge[1]).add(edge[0]);
            }

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    count++;
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(i);
                    while (!queue.isEmpty()) {
                        int index = queue.poll();
                        visited[index] = true;
                        for (int next : adjList.get(index)) {
                            if (!visited[next]) {
                                queue.offer(next);
                            }
                        }
                    }
                }
            }
            return count;
        }
    }

    // 并查集
    class UnionFind {
        public int countComponents(int n, int[][] edges) {
            int[] parents = new int[n];
            Arrays.fill(parents, -1);
            for (int[] e : edges) {
                int root1 = find(parents, e[0]);
                int root2 = find(parents, e[1]);
                if (root1 != root2) {
                    parents[root1] = root2;
                    n--;
                }
            }
            return n;
        }

        int find(int[] parents, int x) {
            int root = x;
            while (parents[root] != -1) {
                root = parents[root];
            }
            return root;
        }
    }
}
