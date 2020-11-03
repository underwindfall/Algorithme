package training.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode-cn.com/problems/binary-tree-preorder-traversal
/**
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 */
public class PreOrderTraversal {
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

    // morris
    //time : O(N)
    //esapce : O(1)
    // https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/leetcodesuan-fa-xiu-lian-dong-hua-yan-shi-xbian-2/
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        TreeNode cur1 = root;// 当前开始遍历的节点
        TreeNode cur2 = null;// 记录当前结点的左子树
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {// 找到当前左子树的最右侧节点，且这个节点应该在指向根结点之前，否则整个节点又回到了根结点。
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {// 这个时候如果最右侧这个节点的右指针没有指向根结点，创建连接然后往下一个左子树的根结点进行连接操作。
                    res.add(cur1.val);
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {// 当左子树的最右侧节点有指向根结点，此时说明我们已经回到了根结点并重复了之前的操作，同时在回到根结点的时候我们应该已经处理完 左子树的最右侧节点
                        // 了，把路断开。
                    cur2.right = null;
                }
            } else {
                res.add(cur1.val);
            }
            cur1 = cur1.right;// 一直往右边走，参考图
        }
        return res;
    }

    /**
     * 迭代方法
     * 
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                result.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return result;
    }

    /**
     * 嵌套方法
     * 
     * time : O(N)
     * 
     * 
     * espace: O(logN) ~ O(N)
     */
    List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        result.add(root.val);

        if (left != null) {
            preorderTraversal(left);
        }
        if (right != null) {
            preorderTraversal(right);
        }
        return result;
    }

}
