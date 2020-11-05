package training.binarytree;

import java.util.HashMap;
import java.util.Map;

// 根据一棵树的前序遍历与中序遍历构造二叉树。

// 注意:
// 你可以假设树中没有重复的元素。

// 例如，给出

// 前序遍历 preorder = [3,9,20,15,7]
// 中序遍历 inorder = [9,3,15,20,7]
// 返回如下的二叉树：

//     3
//    / \
//   9  20
//     /  \
//    15   7

public class ConstructTreeInorderAndPreorder {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

    }

    int pre_idx;
    int[] preorder;
    int[] inorder;
    Map<Integer, Integer> idx_map = new HashMap<>();

    //time O(n)
    //espace O(N)
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        for (int i = 0; i < preorder.length; i++) {
            idx_map.put(inorder[i], i);
        }
        int preorderLeftIndex = 0;
        int preorderRightIndex = preorder.length - 1;
        int inOrderLeftIndex = 0;
        int inOrderRightIndex = inorder.length - 1;
        return recursiveBuildTree(preorderLeftIndex, preorderRightIndex, inOrderLeftIndex, inOrderRightIndex);
    }

    private TreeNode recursiveBuildTree(int preorderLeftIndex, int preorderRightIndex, int inOrderLeftIndex,
            int inOrderRightIndex) {
        if (preorderLeftIndex > preorderRightIndex) {
            return null;
        }
        int rootIndex = preorderLeftIndex;
        int inOrderRootIndex = idx_map.get(preorder[rootIndex]);

        TreeNode root = new TreeNode(preorder[rootIndex]);
        int leftTreeSize = inOrderRootIndex - inOrderLeftIndex;
        root.left = recursiveBuildTree(preorderLeftIndex + 1, preorderLeftIndex + 1 + leftTreeSize - 1,
                inOrderLeftIndex, inOrderRootIndex - 1);
        root.right = recursiveBuildTree(preorderLeftIndex + 1 + leftTreeSize - 1 + 1, preorderRightIndex,
                inOrderRootIndex + 1, inOrderRightIndex);
        return root;
    }

    // https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--22/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end) {
        // preorder 为空，直接返回 null
        if (p_start == p_end) {
            return null;
        }
        int root_val = preorder[p_start];
        TreeNode root = new TreeNode(root_val);
        // 在中序遍历中找到根节点的位置
        int i_root_index = 0;
        for (int i = i_start; i < i_end; i++) {
            if (root_val == inorder[i]) {
                i_root_index = i;
                break;
            }
        }
        int leftNum = i_root_index - i_start;
        // 递归的构造左子树
        root.left = buildTreeHelper(preorder, p_start + 1, p_start + leftNum + 1, inorder, i_start, i_root_index);
        // 递归的构造右子树
        root.right = buildTreeHelper(preorder, p_start + leftNum + 1, p_end, inorder, i_root_index + 1, i_end);
        return root;
    }

}
