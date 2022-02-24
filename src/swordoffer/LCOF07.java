package swordoffer;

// https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
public class LCOF07 {
    // time O(n)
    // space O(n)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // preorder root, left, right
        // inorder left, root, right
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        int index = 0;
        while (inorder[index] != rootVal) {
            index++;
        }
        TreeNode root = new TreeNode(rootVal);
        int leftSize = index - inStart;
        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, index - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd);
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
