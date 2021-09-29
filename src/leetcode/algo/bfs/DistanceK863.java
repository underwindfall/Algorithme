package leetcode.algo.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/
public class DistanceK863 {
    /**
     * dfs 简历graph
     * bfs 从target出发寻找到k的node
     */
    class Solution {
        Map<TreeNode, TreeNode> graph = new HashMap<>();

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            buildGraph(root);
            List<Integer> res = new ArrayList<>();
            if (k == 0)
                res.add(target.val);
            Queue<TreeNode> queue = new LinkedList<>();
            Set<TreeNode> visited = new HashSet<>();
            queue.add(target);
            visited.add(target);
            int level = 0;
            while (!queue.isEmpty() && level < k) {
                level++;
                int len = queue.size();
                for (int i = 0; i < len; i++) {
                    TreeNode node = queue.poll();
                    List<TreeNode> list = new ArrayList<>();
                    if (graph.containsKey(node)) {
                        list.add(graph.get(node));
                    }
                    list.add(node.left);
                    list.add(node.right);
                    for (TreeNode tmp : list) {
                        if (tmp != null && visited.contains(tmp) == false) {
                            if (level == k) {
                                res.add(tmp.val);
                            }
                            visited.add(tmp);
                            queue.add(tmp);
                        }
                    }
                }
            }

            return res;
        }

        void buildGraph(TreeNode node) {
            if (node != null) {
                if (node.left != null) {
                    graph.put(node.left, node);
                }

                if (node.right != null) {
                    graph.put(node.right, node);
                }
                buildGraph(node.left);
                buildGraph(node.right);
            }
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
