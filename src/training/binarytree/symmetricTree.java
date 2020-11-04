package training.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//https://leetcode-cn.com/problems/symmetric-tree
// 给定一个二叉树，检查它是否是镜像对称的。

//  

// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

//     1
//    / \
//   2   2
//  / \ / \
// 3  4 4  3
//  

// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

//     1
//    / \
//   2   2
//    \   \
//    3    3

public class SymmetricTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

    }

    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode
     * left; TreeNode right; TreeNode(int x) { val = x; } }
     */
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            Queue<TreeNode> queue = new ArrayDeque<>();

            List<Integer> nodes = new ArrayList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                // 这里可以优化下
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();

                    if (node.left != null) {
                        queue.add(node.left);
                        nodes.add(node.left.val);
                    } else {
                        nodes.add(null);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                        nodes.add(node.right.val);
                    } else {
                        nodes.add(null);
                    }
                }

                int nodeSize = nodes.size();
                if (nodeSize % 2 != 0) {
                    return false;
                }
                int halfSize = nodeSize / 2;
                for (int j = 0; j < halfSize; j++) {
                    if (nodes.get(j) == nodes.get(nodeSize - 1 - j)) {
                        continue;
                    } else {
                        return false;
                    }
                }
                nodes.clear();
            }
            return true;
        }

        // 嵌套
        // time : O(N)
        // espace: O(height)
        public boolean isSymmetricRecurisve(TreeNode root) {
            return checkRecurisve(root, root);
        }

        private boolean checkRecurisve(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            return left.val == right.val && checkRecurisve(left.left, right.right) && checkRecurisve(left.right, right.left);
        }

        // 迭代
        public boolean isSymmetricIterative(TreeNode root) {
            return checkIterative(root, root);
        }

        private boolean checkIterative(TreeNode left, TreeNode right) {
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(left);
            queue.add(right);
            while (!queue.isEmpty()) {
                left = queue.poll();
                right = queue.poll();
                if(left==null && right==null){
                    continue;
                }
                if((left==null || right==null)||(left.val!=right.val)){
                    return false;
                }
                queue.add(left.left);
                queue.add(right.right);
                queue.add(left.right);
                queue.add(right.left);
            }
            return true;
        }
    }

}
