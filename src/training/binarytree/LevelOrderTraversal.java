package training.binarytree;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

//https://leetcode-cn.com/problems/binary-tree-level-order-traversa
public class LevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

    }

    // 迭代
    // 层次遍历
    // time O(N)
    // espace O(N)
    public List<List<Integer>> levelOrder1(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 根节点进入循环
        queue.add(root);
        while (queue.size() > 0) {
            // 获取当前队列的长度，这个长度相当于 当前这一层的节点个数
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            // 将队列中的元素都拿出来(也就是获取这一层的节点)，放到临时list中
            // 如果节点的左/右子树不为空，也放入队列中
            for (int i = 0; i < size; i++) {
                TreeNode t = queue.remove();
                tmp.add(t.val);
                if (t.left != null) {
                    queue.add(t.left);
                }
                if (t.right != null) {
                    queue.add(t.right);
                }
            }
            res.add(tmp);
        }

        return res;
    }

    // 递归搜素
    // time : O(N)
    // espace: O(N)
    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    private void recursive(TreeNode node, int level) {
        if (levels.size() == level) {
            levels.add(new ArrayList<Integer>());
        }

        levels.get(level).add(node.val);
        if (node.left != null) {
            recursive(node.left, level + 1);
        }
        if (node.right != null) {
            recursive(node.right, level + 1);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return levels;
        recursive(root, 0);
        return levels;
    }

}   