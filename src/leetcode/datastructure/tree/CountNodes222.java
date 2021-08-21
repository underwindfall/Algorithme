package leetcode.datastructure.tree;

//https://leetcode-cn.com/problems/count-complete-tree-nodes/
public class CountNodes222 {
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

    // time O(logN)
    // espace O(logN)
    class Recursive {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }

    // Time: O( (logN)^2 )
    // Space: O(logN)
    class CompleteTree {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftDepth = getDepth(root.left);
            int rightDepth = getDepth(root.right);

            if (leftDepth == rightDepth) {// 左子树是满二叉树
                return (int) Math.pow(2, leftDepth) - 1 + countNodes(root.right) + 1;
            } else {
                return (int) Math.pow(2, rightDepth) - 1 + countNodes(root.left) + 1;
            }

        }

        int getDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int depth = 1;
            while (root.left != null) {
                root = root.left;
                depth++;
            }
            return depth;
        }
    }

    // Time: O( (logN)^2 )
    // Space: O(logN)
    class BinarySearch {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int level = 0;
            TreeNode node = root;
            while (node.left != null) {
                level++;
                node = node.left;
            }
            int low = 1 << level, high = (1 << (level + 1)) - 1;
            while (low < high) {
                int mid = (high - low + 1) / 2 + low;
                if (exists(root, level, mid)) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            return low;
        }

        public boolean exists(TreeNode root, int level, int k) {
            int bits = 1 << (level - 1);
            TreeNode node = root;
            while (node != null && bits > 0) {
                if ((bits & k) == 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
                bits >>= 1;
            }
            return node != null;
        }

    }
}
