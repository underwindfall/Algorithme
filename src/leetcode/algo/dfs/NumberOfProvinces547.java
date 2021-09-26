package leetcode.algo.dfs;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/number-of-provinces/
public class NumberOfProvinces547 {
    class DFS {
        public int findCircleNum(int[][] isConnected) {
            // int[][] isConnected 是无向图的邻接矩阵，n 为无向图的顶点数量
            int n = isConnected.length;
            // 定义 boolean 数组标识顶点是否被访问
            boolean[] visited = new boolean[n];
            // 定义 cnt 来累计遍历过的连通域的数量
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                // 若当前顶点 i 未被访问，说明又是一个新的连通域，则遍历新的连通域且cnt+=1.
                if (!visited[i]) {
                    cnt++;
                    dfs(i, isConnected, visited);
                }
            }
            return cnt;
        }

        void dfs(int i, int[][] isConnected, boolean[] visited) {
            // 对当前顶点 i 进行访问标记
            visited[i] = true;
            // 继续遍历与顶点 i 相邻的顶点（使用 visited 数组防止重复访问）
            for (int j = 0; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1 && !visited[j]) {
                    dfs(j, isConnected, visited);
                }
            }
        }
    }

    class BFS {
        public int findCircleNum(int[][] isConnected) {
            // int[][] isConnected 是无向图的邻接矩阵，n 为无向图的顶点数量
            int n = isConnected.length;
            // 定义 boolean 数组标识顶点是否被访问
            boolean[] visited = new boolean[n];
            // 定义 cnt 来累计遍历过的连通域的数量
            int cnt = 0;

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                // 若当前顶点 i 未被访问，说明又是一个新的连通域，则bfs新的连通域且cnt+=1.
                if (!visited[i]) {
                    cnt++;
                    queue.add(i);
                    visited[i] = true;
                    while (!queue.isEmpty()) {
                        int v = queue.poll();
                        for (int w = 0; w < n; w++) {
                            if (isConnected[v][w] == 1 && !visited[w]) {
                                visited[w] = true;
                                queue.add(w);
                            }
                        }
                    }
                }
            }
            return cnt;
        }
    }

    class UF {
        // 并查集
        class UnionFind {
            int[] roots;
            int size; // 集合数量

            public UnionFind(int n) {
                roots = new int[n];
                for (int i = 0; i < n; i++) {
                    roots[i] = i;
                }
                size = n;
            }

            public int find(int i) {
                if (i == roots[i]) {
                    return i;
                }
                return roots[i] = find(roots[i]);
            }

            public void union(int p, int q) {
                int pRoot = find(p);
                int qRoot = find(q);
                if (pRoot != qRoot) {
                    roots[pRoot] = qRoot;
                    size--;
                }
            }
        }

        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            // 初始化并查集
            UnionFind uf = new UnionFind(n);
            // 遍历每个顶点，将当前顶点与其邻接点进行合并
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isConnected[i][j] == 1) {
                        uf.union(i, j);
                    }
                }
            }
            // 返回最终合并后的集合的数量
            return uf.size;

        }
    }

}
