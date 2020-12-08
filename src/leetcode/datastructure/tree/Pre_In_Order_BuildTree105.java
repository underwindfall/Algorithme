package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class Pre_In_Order_BuildTree105 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 思路找规律
    // preOrder的规律是 root,left,right
    // inOrder 的规律是 left,root,right
    // 1. 可以轻易得到 root 的值 preOrder[0] = root
    // 2. 通过loop inOrder， 可以找到root所对应的index 从而找到左子树和右子树
    // 3. 左子树=inOrder[0.....index] 右子树=inOrder[index+1.....inOrder.length]
    // 4. 那相应的preOrder的左子树和右子树也能找到 因为同一个树的左子树和右子树的数量应该一致
    // =================>左子树=preOrder[rootIndex+1.....左子树InOrder-1],右子树=preOrder[左子树InOrder.....preOrder.length]
    //
    // time O(N)
    // espace O(N)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];
        // rootVal在中序遍历中的索引借以来找到左子树和右子树
        int index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        int leftSize = index - inStart;
        root.left = build(preorder, preStart + 1, leftSize + preStart, inorder, inStart, index - 1);
        root.right = build(preorder, leftSize + preStart + 1, preEnd, inorder, index + 1, inEnd);
        return root;
    }
}
