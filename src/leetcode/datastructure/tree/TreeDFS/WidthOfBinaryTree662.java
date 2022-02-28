package leetcode.datastructure.tree.TreeDFS;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import kotlin.Pair;

// https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
public class WidthOfBinaryTree662 {
    // time O(n)
    // space O(n)
    class BFS {
        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
            q.offer(new Pair<>(root, 0));
            int ans = 0;
            while (!q.isEmpty()) {
                Pair<TreeNode, Integer> head = q.peek();
                int size = q.size();
                Pair<TreeNode, Integer> elem = null;
                for (int i = 0; i < size; i++) {
                    elem = q.poll();
                    TreeNode node = elem.getFirst();
                    if (node.left != null) {
                        q.offer(new Pair<>(node.left, 2 * elem.getSecond()));
                    }
                    if (node.right != null) {
                        q.offer(new Pair<>(node.right, 2 * elem.getSecond() + 1));
                    }
                }
                ans = Math.max(ans, elem.getSecond() - head.getSecond() + 1);
            }
            return ans;
        }
    }

    // time O(n)
    // space O(n)
    class DFS {
        private Integer maxWidth = 0;
        private HashMap<Integer, Integer> firstColIndexTable;

        void dfs(TreeNode node, Integer depth, Integer colIndex) {
            if (node == null)
                return;
            // initialize the value, for the first seen colIndex per level
            if (!firstColIndexTable.containsKey(depth)) {
                firstColIndexTable.put(depth, colIndex);
            }
            Integer firstColIndex = firstColIndexTable.get(depth);

            maxWidth = Math.max(this.maxWidth, colIndex - firstColIndex + 1);

            // Preorder DFS. Note: it is important to put the priority on the left child
            dfs(node.left, depth + 1, 2 * colIndex);
            dfs(node.right, depth + 1, 2 * colIndex + 1);
        }

        public int widthOfBinaryTree(TreeNode root) {
            // table contains the first col_index for each level
            this.firstColIndexTable = new HashMap<Integer, Integer>();

            // start from depth = 0, and colIndex = 0
            dfs(root, 0, 0);

            return this.maxWidth;
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
