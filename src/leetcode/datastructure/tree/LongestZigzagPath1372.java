package leetcode.datastructure.tree;

//https://leetcode-cn.com/problems/longest-zigzag-path-in-a-binary-tree/
public class LongestZigzagPath1372 {
    int ans = 0;

    //time O(N)
    //espace  O(1)
    public int longestZigZag(TreeNode root) {
        dfs(root.left, 1, 1);
        dfs(root.right, 1, 0);
        return ans;
    }

    // direction 0 -> left 1 -> right
    void dfs(TreeNode node, int depth, int direction) {
        if (node == null) {
            return;
        }
        ans = Math.max(ans, depth);
        if (direction == 0) {
            // 如果不能左, 从这个点重新出发
            dfs(node.right, 1, 0);
            dfs(node.left, depth + 1, 1);
        } else {
            // 如果不能右, 从这个点重新出发
            dfs(node.left, 1, 1);
            dfs(node.right, depth + 1, 0);
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
