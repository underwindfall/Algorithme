package leetcode.algo.bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
public class ZigzagLevelOrder103 {
    // time O(n)
    // space O(n)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();

            for (int i = 0; i < size; i++) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }

                if (curNode.left != null) {
                    nodeQueue.add(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.add(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }

    // time O(n)
    // space O(n)
    class DFS {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            traversal(root, res, 0);
            return res;
        }

        private void traversal(TreeNode root, List<List<Integer>> res, int level) {
            if (root == null) {
                return;
            }

            if (res.size() == level) {
                res.add(new ArrayList<Integer>());
            }

            if (level % 2 == 1) {
                res.get(level).add(0, root.val);
            } else {
                res.get(level).add(root.val);
            }

            traversal(root.left, res, level + 1);
            traversal(root.right, res, level + 1);
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
