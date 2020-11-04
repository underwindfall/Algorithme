package training.binarytree;

//https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/
public class Dfs {
    public class TreeNode {
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

    void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        //这里🙅‍♂️不需要验证root 左树和右树的 是否是null的特性 因为在递归的调用过程中会重新check
        dfs(root.left);
        dfs(root.right);
    }
}
