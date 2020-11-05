package training.binarytree;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
// 根据一棵树的中序遍历与后序遍历构造二叉树。

// 注意:
// 你可以假设树中没有重复的元素。

// 例如，给出

// 中序遍历 inorder = [9,3,15,20,7]
// 后序遍历 postorder = [9,15,7,20,3]
// 返回如下的二叉树：

//     3
//    / \
//   9  20
//     /  \
//    15   7

public class ConstructTreeInorderAndPostorder {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

    }

    int post_idx;
    int[] postorder;
    int[] inorder;
    Map<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        // 从后序遍历的最后一个元素开始
        post_idx = postorder.length - 1;

        // 建立（元素，下标）键值对的哈希表
        int idx = 0;
        for (Integer val : inorder) {
            idx_map.put(val, idx++);
        }

        return helper(0, inorder.length - 1);
    }

    private TreeNode helper(int leftNodeIndex, int rightNodeIndex) {
        // 如果这里没有节点构造二叉树了，就结束
        if (leftNodeIndex > rightNodeIndex) {
            return null;
        }
        // 选择 post_idx 位置的元素作为当前子树根节点
        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);
        // 根据 root 所在位置分成左右两棵子树
        int index = idx_map.get(root_val);
        post_idx--;
        // 构造右子树
        root.right = helper(index + 1, rightNodeIndex);
        // 构造左子树
        root.left = helper(leftNodeIndex, index - 1);
        return root;
    }

    public TreeNode buildTreeRecursive(int[] inorder, int[] postorder) {
        return buildTreeRecursive(inorder, postorder, 0, inorder.length - 1, 0, inorder.length - 1);
    }

    TreeNode buildTreeRecursive(int[] inorder, int[] postorder, int inLeft, int inRight, int postLeft, int postRight) {
        if (inRight < inLeft) {
            return null;
        }
        int rootVal = postorder[postRight];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = 0;
        for (int i = inRight; i >= inLeft; --i) { // 完全一样的代码，这里从右往左遍历1ms，从左往右遍历4ms
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        root.left = buildTreeRecursive(inorder, postorder, inLeft, rootIndex - 1, postLeft,
                postLeft + rootIndex - inLeft - 1);
        root.right = buildTreeRecursive(inorder, postorder, rootIndex + 1, inRight, postLeft + rootIndex - inLeft,
                postRight - 1);
        return root;
    }

}
