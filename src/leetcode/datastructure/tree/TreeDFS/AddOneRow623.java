package leetcode.datastructure.tree.TreeDFS;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.cn/problems/add-one-row-to-tree/
public class AddOneRow623 {
    // time O(H)
    // space O(H)
    class BFS {
        public TreeNode addOneRow(TreeNode root, int val, int depth) {
            if (depth == 1) {
                TreeNode ans = new TreeNode(val);
                ans.left = root;
                return ans;
            }
            Deque<TreeNode> d = new ArrayDeque<>();
            d.addLast(root);
            int cur = 1;
            while (!d.isEmpty()) {
                int sz = d.size();
                while (sz-- > 0) {
                    TreeNode t = d.pollFirst();
                    if (cur == depth - 1) {
                        TreeNode a = new TreeNode(val), b = new TreeNode(val);
                        a.left = t.left;
                        b.right = t.right;
                        t.left = a;
                        t.right = b;
                    } else {
                        if (t.left != null)
                            d.addLast(t.left);
                        if (t.right != null)
                            d.addLast(t.right);
                    }
                }
                cur++;
            }
            return root;
        }
    }

    // time O(H)
    // space O(H)
    class DFS {
        public TreeNode addOneRow(TreeNode root, int val, int depth) {
            if (root == null) {
                return root;
            }
            if (depth == 1) {
                return new TreeNode(val, root, null);
            }
            if (depth == 2) {
                root.left = new TreeNode(val, root.left, null);
                root.right = new TreeNode(val, null, root.right);
            } else {
                root.left = addOneRow(root, val, depth - 1);
                root.right = addOneRow(root, val, depth - 1);
            }
            return root;
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
