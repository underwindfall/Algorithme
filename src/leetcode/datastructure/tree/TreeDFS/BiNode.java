package leetcode.datastructure.tree.TreeDFS;

// https://leetcode.cn/problems/binode-lcci/
public class BiNode {
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

    TreeNode head = new TreeNode(-1);
    TreeNode prev = head;


    //time O(N)
    //space O(H)
    public TreeNode convertBiNode(TreeNode root) {
        dfs(root);
        return head.right;
    }

    void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        prev.right = node;
        node.left = null;
        prev = node;
        dfs(node.right);
    }

}
