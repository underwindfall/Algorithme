package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
public class ConvertBSTToGreaterSumTree538 {
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

    // 题目可以发现 root 的规律是 右 中 左 的顺序迭代出来的结果
    // time O(N)
    // espace O(N)
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        inOrderTraverse(root);
        return root;
    }

    void inOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.right);
        // 维护累加和
        sum += root.val;
        //改变当前节点的值
        root.val= sum;
        inOrderTraverse(root.left);
    }
}
