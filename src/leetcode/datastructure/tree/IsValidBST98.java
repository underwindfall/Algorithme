package leetcode.datastructure.tree;

import java.util.ArrayDeque;
import java.util.Deque;

// 判断一个tree是不是BST
// 关键在于是否满足左子树的最大值<root.val<右子树的最小值
//https://leetcode-cn.com/problems/validate-binary-search-tree
public class IsValidBST98 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // time O(N)
    // espace O(N)
    // 如何判断一个tree 是不是BST
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    // time o(n)
    //space O(n )
    class Iterative {
        public boolean isValidBST(TreeNode root) {
            Deque<TreeNode> stk = new ArrayDeque<>();
            TreeNode prev = null;
            while (root != null || !stk.isEmpty()) {
                while (root != null) {
                    stk.push(root);
                    root = root.left;
                }
                root = stk.pop();
                if (prev != null && prev.val >= root.val)
                    return false;
                prev = root;
                root = root.right;
            }
            return true;
        }
    }

    // time O(N)
    // epsace O(N)
    // 中序遍历 从小到大 left root right
    class PreOrder {
        long pre = Long.MIN_VALUE;

        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            // left subTree
            if (!isValidBST(root.left)) {
                return false;
            }

            // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;
            return isValidBST(root.right);
        }
    }
}
