package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
public class InsertNumberBST701 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // dfs
    // time O(logN)
    // espace O(logN)
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }


    //bfs
    //time O(N)
    //espace O(1)
    class BFS {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }
            TreeNode pos = root;
            while(pos != null) {
                if (val < pos.val) {
                    if (pos.left == null) {
                        pos.left = new TreeNode(val);
                        break;
                    } else {
                        pos = pos.left;
                    }
                } else {
                    if (pos.right == null) {
                        pos.right = new TreeNode(val);
                        break;
                    } else {
                        pos = pos.right;
                    }
                }
            }
            return root;
        }
    }
}
