package leetcode.datastructure.tree;

//https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
public class SearchBST700 {
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

    // time O(H)
    // espace O(1)
    class DFS {
        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null || root.val == val) {
                return root;
            }
            if (val < root.val) {
                return searchBST(root.left, val);
            } else {
                return searchBST(root.right, val);
            }
        }
    }

    // time O(H)
    // espace O(1)
    class BFS {
        public TreeNode searchBST(TreeNode root, int val) {
            while (root != null && val != root.val)
                root = val < root.val ? root.left : root.right;
            return root;
        }
    }
}