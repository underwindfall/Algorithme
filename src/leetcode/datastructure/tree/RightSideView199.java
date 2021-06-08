package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode-cn.com/problems/binary-tree-right-side-view/
public class RightSideView199 {
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

    // time O(N)
    // espace O(N)
    class DFS {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            dfs(root, result, 0);
            return result;
        }

        void dfs(TreeNode node, List<Integer> result, int depth) {
            if (node == null) {
                return;
            }
            if (depth == result.size()) {
                result.add(node.val);
            }
            depth++;
            dfs(node.right, result, depth);
            dfs(node.left, result, depth);
        }
    }

    // time O(N)
    // espace O(N)
    class BFS {

        class Pair<K, V> {
            private K key;
            private V value;

            Pair(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public V getValue() {
                return value;
            }

            public K getKey() {
                return key;
            }
        }

        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
            if (root == null) {
                return result;
            }
            queue.add(new Pair<>(root, 1));
            while (!queue.isEmpty()) {
                Pair<TreeNode, Integer> p = queue.poll();
                TreeNode node = p.getKey();
                int level = p.getValue();

                if (level > result.size()) {
                    result.add(node.val);
                }
                if (node.right != null) {
                    queue.add(new Pair<>(node.right, level + 1));
                }
                if (node.left != null) {
                    queue.add(new Pair<>(node.left, level + 1));
                }
            }
            return result;
        }
    }
}
