package leetcode.algo.binarysearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode-cn.com/problems/closest-binary-search-tree-value/
public class ClosestBSTValue270 {
    // time O(n)
    // space O(n)
    class InOrder {
        List<Integer> tree = new ArrayList<>();

        public int closestValue(TreeNode root, double target) {
            // inorder
            inOrder(root);
            int treeVal = 0;
            double diff = Double.MAX_VALUE;
            for (int i = 0; i < tree.size(); i++) {
                if (Math.abs(tree.get(i) - target) < diff) {
                    treeVal = tree.get(i);
                    diff = Math.abs(tree.get(i) - target);
                }
            }
            return treeVal;
        }

        void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            tree.add(root.val);
            inOrder(root.right);
        }

    }

    // time O(h)
    // space O(1)
    class Binarysearch {
        double mindis = Double.MAX_VALUE;// 建立全局最小值
        int res;// 建立全局最终节点

        public int closestValue(TreeNode root, double target) {
            if (root != null) {
                double curdis = Math.abs(root.val - target);// 计算当前节点和target的距离
                if (curdis < mindis) {// 如果当前距离小于最小值，则替换（此处是同时发生替换）
                    mindis = curdis;
                    res = root.val;
                }
                // 二分搜索，利用二叉搜索树的条件：目标值大于当前节点val则去右子树继续下一轮的递归，否则去左子树
                res = target > root.val ? closestValue(root.right, target) : closestValue(root.left, target);
            }
            return res;
        }
    }

    // time O(k - H + k)
    // space O(h)
    class Iterative {
        public int closestValue(TreeNode root, double target) {
            LinkedList<TreeNode> stack = new LinkedList<>();
            long pred = Long.MIN_VALUE;
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.add(root);
                    root = root.left;
                }
                root = stack.removeLast();
                if (pred <= target && target < root.val) {
                    return Math.abs(pred - target) < Math.abs(root.val - target) ? (int) pred : root.val;
                }
                pred = root.val;
                root = root.right;
            }
            return (int) pred;
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
