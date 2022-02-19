package swordoffer;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/NYBBNL/
public class LCOF52_II {

    // time O(n)
    // space O(1)
    class Recursive {
        public TreeNode increasingBST(TreeNode root) {
            // 终止条件，如果是空节点，或者是叶子节点，直接返回
            if (root == null || (root.left == null && root.right == null)) {
                return root;
            }

            // 将左右子树处理好
            TreeNode left = increasingBST(root.left);
            TreeNode right = increasingBST(root.right);

            // 左子树为头结点，记录
            TreeNode first = left;
            // 需要将左右子树连起来
            // 左子树已经是排好只有右节点的了，找到最后一个右节点
            while (left != null && left.right != null) {
                left = left.right;
            }

            // 如果左节点不为空，将最后一个右节点的右节点设置为root。
            // 将root的左节点设置为null
            if (left != null) {
                left.right = root;
                root.left = null;
            }

            // 将root的右节点设置为处理好的right
            root.right = right;

            // 如果左子树不为空，返回左子树的节点，否则返回root节点
            return first != null ? first : root;
        }
    }

    // time O(n)
    // space O(n)
    class InOrder {
        public TreeNode increasingBST(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            inOrder(root, list);
            TreeNode node = new TreeNode(list.get(0));
            TreeNode head = node;
            for (int i = 1; i < list.size(); i++) {
                node.left = null;
                node.right = new TreeNode(list.get(i));
                node = node.right;
            }
            return head;
        }

        void inOrder(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            inOrder(root.left, list);
            list.add(root.val);
            inOrder(root.right, list);
        }

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
