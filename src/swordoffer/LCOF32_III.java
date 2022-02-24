package swordoffer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
public class LCOF32_III {

    // time O(n)
    // space O(n)
    class BFS {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null)
                return res;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            boolean isLeft = true;
            while (!q.isEmpty()) {
                int size = q.size();
                Deque<Integer> level = new ArrayDeque<>();
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    if (isLeft) {
                        level.offerLast(node.val);
                    } else {
                        level.offerFirst(node.val);
                    }
                    if (node.left != null) {
                        q.offer(node.left);
                    }
                    if (node.right != null) {
                        q.offer(node.right);
                    }
                }
                isLeft = !isLeft;
                res.add(new ArrayList<>(level));
            }
            return res;
        }
    }

    // time O(n)
    // space O(n)
    class DFS {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(root, res, 0);
            return res;
        }

        void dfs(TreeNode root, List<List<Integer>> res, int level) {
            if (root == null) {
                return;
            }
            if (res.size() == level) {
                res.add(new ArrayList<>());
            }
            if (level % 2 == 0) {
                res.get(level).add(root.val);
            } else {
                res.get(level).add(0, root.val);
            }
            dfs(root.left, res, level + 1);
            dfs(root.right, res, level + 1);
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
