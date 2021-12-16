package leetcode.algo.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// https://leetcode-cn.com/problems/minimum-height-trees/
public class FindMinHeightTrees310 {
    // time O(m+n)
    // space O(m+N)
    class BFS {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            List<Integer> res = new ArrayList<>();
            /* 如果只有一个节点，那么他就是最小高度树 */
            if (n == 1) {
                res.add(0);
                return res;
            }
            /* 建立各个节点的出度表 */
            int[] degree = new int[n];
            /* 建立图关系，在每个节点的list中存储相连节点 */
            List<List<Integer>> map = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                map.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                degree[edge[0]]++;
                degree[edge[1]]++;/* 出度++ */
                map.get(edge[0]).add(edge[1]);/* 添加相邻节点 */
                map.get(edge[1]).add(edge[0]);
            }
            /* 建立队列 */
            Queue<Integer> queue = new LinkedList<>();
            /* 把所有出度为1的节点，也就是叶子节点入队 */
            for (int i = 0; i < n; i++) {
                if (degree[i] == 1)
                    queue.offer(i);
            }
            /* 循环条件当然是经典的不空判断 */
            while (!queue.isEmpty()) {
                res = new ArrayList<>();/*
                                         * 这个地方注意，我们每层循环都要new一个新的结果集合，
                                         * 这样最后保存的就是最终的最小高度树了
                                         */
                int size = queue.size();/* 这是每一层的节点的数量 */
                for (int i = 0; i < size; i++) {
                    int cur = queue.poll();
                    res.add(cur);/*
                                  * 把当前节点加入结果集，不要有疑问，为什么当前只是叶子节点为什么要加入结果集呢?
                                  * 因为我们每次循环都会新建一个list，所以最后保存的就是最后一个状态下的叶子节点，
                                  * 这也是很多题解里面所说的剪掉叶子节点的部分，你可以想象一下图，每层遍历完，
                                  * 都会把该层（也就是叶子节点层）这一层从队列中移除掉，
                                  * 不就相当于把原来的图给剪掉一圈叶子节点，形成一个缩小的新的图吗
                                  */
                    List<Integer> neighbors = map.get(cur);
                    /*
                     * 这里就是经典的bfs了，把当前节点的相邻接点都拿出来，
                     * 把它们的出度都减1，因为当前节点已经不存在了，所以，
                     * 它的相邻节点们就有可能变成叶子节点
                     */
                    for (int neighbor : neighbors) {
                        degree[neighbor]--;
                        if (degree[neighbor] == 1) {
                            /* 如果是叶子节点我们就入队 */
                            queue.offer(neighbor);
                        }
                    }
                }
            }
            return res;/* 返回最后一次保存的list */
        }
    }

    //time 
    //space
    class DFS {
        Map<Integer, List<Integer>> ans = new HashMap<>();
        int minHeight = Integer.MAX_VALUE;

        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            List<List<Integer>> graph = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < edges.length; i++) {
                int a = edges[i][0], b = edges[i][1];
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
            // System.out.println("height " + dfs(0, graph, new boolean[n]));
            for (int i = 0; i < n; i++) {
                int height = dfs(i, graph, new boolean[n]);
                // System.out.println("height " + height);
                if (height <= minHeight) {
                    // System.out.println("height " + height + " minHeight" + minHeight + " ans " +
                    // ans.get(height));
                    minHeight = height;
                    if (ans.containsKey(height)) {
                        ans.get(height).add(i);
                    } else {
                        ans.put(height, new ArrayList<>());
                        ans.get(height).add(i);
                    }
                }
            }
            // System.out.println(ans.get(1));
            // System.out.println(ans.get(2));

            return ans.get(minHeight);
        }

        int dfs(int root, List<List<Integer>> graph, boolean[] visited) {
            visited[root] = true;
            List<Integer> neigbours = graph.get(root);
            if (neigbours.size() == 1 && visited[neigbours.get(0)]) {
                return 0;
            }
            int height = 0;
            for (int i : neigbours) {
                if (!visited[i]) {
                    visited[i] = true;
                    height = Math.max(height, dfs(i, graph, visited));
                    // System.out.println(i);
                }
            }
            return height + 1;
        }
    }
}
