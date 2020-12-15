package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode-cn.com/problems/symmetric-tree/description/
public class isSymmetric101 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 嵌套
    // time : O(N)
    // espace: O(height)
    class DFS {
        public boolean isSymmetric(TreeNode root) {
            return checkRecursive(root, root);
        }

        boolean checkRecursive(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            return left.val == right.val && checkRecursive(left.left, right.right)
                    && checkRecursive(left.right, right.left);
        }
    }

    class BFS {
        public boolean isSymmetric(TreeNode root) {
            // bfs
            Queue<TreeNode> queue = new LinkedList<>();
            if (root == null) {
                return true;
            }
            queue.add(root);
            while (!queue.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                int count = queue.size();
                for (int i = count; i > 0; i--) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        list.add(node.left.val);
                        queue.add(node.left);
                    } else {
                        list.add(-1);
                    }
                    if (node.right != null) {
                        list.add(node.right.val);
                        queue.add(node.right);
                    } else {
                        list.add(-1);
                    }
                    count--;
                }

                if (!isSymmetric(list)) {
                    return false;
                }
            }
            return true;
        }

        boolean isSymmetric(List<Integer> list) {
            int size = list.size();
            for (int i = 0; i < size / 2; i++) {
                if (list.get(i) == list.get(size - 1 - i)) {
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        }
    }
}
