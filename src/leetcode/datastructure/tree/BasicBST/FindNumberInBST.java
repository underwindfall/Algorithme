package leetcode.datastructure.tree.BasicBST;

public class FindNumberInBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 可以穷举
    // 前序遍历
    // time O(N)
    // espace O(N)
    class DFS {
        public boolean isInBST(TreeNode root, int target) {
            if (root == null) {
                return false;
            }
            if (root.val == target) {
                return true;
            }
            // 当前节点没找到就递归地去左右子树寻找
            return isInBST(root.left, target) || isInBST(root.right, target);
        }
    }

    // time O(logN)
    // espace O(logN)
    class BinarySearch {
        public boolean isInBST(TreeNode root, int target) {
            if (root == null) {
                return false;
            }
            if (root.val == target) {
                return true;
            }
            if (root.val > target) {
                return isInBST(root.left, target);
            }
            if (root.val < target) {
                return isInBST(root.right, target);
            }
            return false;
        }
    }
}
