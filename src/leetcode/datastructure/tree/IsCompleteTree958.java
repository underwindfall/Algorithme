package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
public class IsCompleteTree958 {
    //time O(n)
    //space O(n)
    class Solution {
        public boolean isCompleteTree(TreeNode root) {
            List<ANode> nodes = new ArrayList<>();
            nodes.add(new ANode(root, 1));
            int i = 0;
            while (i < nodes.size()) {
                ANode anode = nodes.get(i++);
                if (anode.node != null) {
                    nodes.add(new ANode(anode.node.left, anode.code * 2));
                    nodes.add(new ANode(anode.node.right, anode.code * 2 + 1));
                }
            }

            return nodes.get(i - 1).code == nodes.size();
        }

        class ANode { // Annotated Node
            TreeNode node;
            int code;

            ANode(TreeNode node, int code) {
                this.node = node;
                this.code = code;
            }
        }
    }

    // time O(N)
    // space O(N)
    public boolean isCompleteTree(TreeNode root) {
        Boolean isRirchNull = false;// 定义一个布尔变量表示是否遇到空结点。
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (isRirchNull && cur != null) {// 当遇到第一个为空的结点而且再次遇到一个为空的结点时返回false,表示完全二叉树
                return false;
            }
            if (cur == null) {// 遇到第一个为空的结点，改变布尔值的变量
                isRirchNull = true;
                continue;// 空结点没有左右子结点，不用执行下面的语句，直接跳出当前循环,进入下一次循环
            }
            queue.offer(cur.left);// 这里不用判断当前结点的左右子节点是否为空。
            queue.offer(cur.right);// 因为要记录是否遇到为空的结点，所以这里不管是空结点还是不为空结点都要加进去。
        }
        return true;// 遍历完还没有再次遇到则是完全二叉树返回true.
    }

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
}
