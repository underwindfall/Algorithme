package leetcode.algo.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

// https://leetcode-cn.com/problems/binary-tree-vertical-order-traversal/
public class VerticalOrder314 {

    // time O(n)
    // space O(n)
    class BFSMinMax {
        public List<List<Integer>> verticalOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            Map<Integer, List<Integer>> map = new HashMap<>();
            Queue<TreeNode> qNode = new LinkedList<>();
            Queue<Integer> qLevel = new LinkedList<>();
            int min = 0, max = 0;
            qNode.add(root);
            qLevel.add(0);
            while (!qNode.isEmpty()) {
                TreeNode node = qNode.poll();
                int level = qLevel.poll();
                if (!map.containsKey(level)) {
                    map.put(level, new ArrayList<>());
                }
                map.get(level).add(node.val);
                if (node.left != null) {
                    qNode.add(node.left);
                    qLevel.add(level - 1);
                    min = Math.min(min, level - 1);
                }
                if (node.right != null) {
                    qNode.add(node.right);
                    qLevel.add(level + 1);
                    max = Math.max(max, level + 1);
                }
            }
            List<List<Integer>> res = new ArrayList<>();
            for (int i = min; i <= max; i++) {
                res.add(map.get(i));
            }
            return res;
        }
    }

    // time O(n)
    // space O(n)
    class BFSTreeMap {
        // time O(n)
        // space O(n)
        public List<List<Integer>> verticalOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            // 存放当前位置(key)的结果集(value)
            Map<Integer, List<Integer>> res = new TreeMap<>();
            // 存放当前节点（TreeNode）的位置 node - integer loop left -1 loop right + 1
            Map<TreeNode, Integer> nodeMap = new HashMap<>();
            nodeMap.put(root, 0);
            // bfs
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                // get level represented number
                int i = nodeMap.get(node);
                if (!res.containsKey(i)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(node.val);
                    res.put(i, list);
                } else {
                    List<Integer> list = res.get(i);
                    list.add(node.val);
                    res.put(i, list);
                }

                // 左边向下逐层减 1
                if (node.left != null) {
                    q.add(node.left);
                    nodeMap.put(node.left, i - 1);
                }
                // 右边向下逐层加 1
                if (node.right != null) {
                    q.add(node.right);
                    nodeMap.put(node.right, i + 1);
                }
            }
            return new ArrayList<>(res.values());
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
