package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
public class FlattenTree114 {
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

    class PreOrderSolution {

        // 前序遍历的题目
        // time O(N)
        // espace O(N)
        public void flatten(TreeNode root) {
            List<TreeNode> list = new ArrayList<>();
            preOrderTranversal(root, list);
            int size = list.size();
            for (int i = 1; i < size; i++) {
                TreeNode prev = list.get(i - 1), curr = list.get(i);
                prev.left = null;
                prev.right = curr;
            }
        }

        private void preOrderTranversal(TreeNode root, List<TreeNode> list) {
            if (root != null) {
                list.add(root);
                preOrderTranversal(root.left, list);
                preOrderTranversal(root.right, list);
            }
        }
    }

    // https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/2.3-shou-ba-shou-shua-er-cha-shu-xun-lian-di-gui-si-wei/er-cha-shu-xi-lie-1
    // 思路比较巧妙
    // 利用后序遍历 左->右->中的方法
    // 每次嵌套都把当前节点的左子树放在右子树上
    // 然后循环到当前右子树的最后一个节点 把新的右子树接上去
    // time O(N)
    // espace O(logN)
    class RecursiveSolution {
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            flatten(root.left);
            flatten(root.right);
            // 后序遍历
            // 1、左右子树已经被拉平成一条链表
            TreeNode left = root.left;
            TreeNode right = root.right;
            // 2、将左子树作为右子树
            root.left = null;
            root.right = left;
            // 3、将原先的右子树接到当前右子树的末端
            TreeNode p = root;
            while (p.right != null) {
                p = p.right;
            }
            p.right = right;
        }
    }

    // time O(N)
    // espace O(1)
    class PreNodeSolution {
        public void flatten(TreeNode root) {
            TreeNode curr = root;
            while (curr != null) {
                if (curr.left != null) {
                    TreeNode next = curr.left;
                    TreeNode predecessor = next;
                    while (predecessor.right != null) {
                        predecessor = predecessor.right;
                    }
                    predecessor.right = curr.right;
                    curr.left = null;
                    curr.right = next;
                }
                curr = curr.right;
            }
        }
    }
}
