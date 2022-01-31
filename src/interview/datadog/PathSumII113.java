package interview.datadog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//https://leetcode-cn.com/problems/path-sum-ii/
public class PathSumII113 {
    // time O(N^2)
    // espace O(N)
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(root, targetSum, res);
        return res;
    }

    void dfs(TreeNode root, int targetSum, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.val == targetSum && root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
        }

        dfs(root.left, targetSum - root.val, res);
        dfs(root.right, targetSum - root.val, res);
        path.remove(path.size() - 1);
    }

    // time O(N^2)
    // espace O(N)
    class DFS {
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            dfs(root, targetSum, new ArrayList<>(), res);
            return res;
        }

        void dfs(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> res) {
            if (root == null) {
                return;
            }
            if (root.val == targetSum && root.left == null && root.right == null) {
                path.add(root.val);
                res.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
            path.add(root.val);
            dfs(root.left, targetSum - root.val, path, res);
            dfs(root.right, targetSum - root.val, path, res);
            path.remove(path.size() - 1);
        }
    }

    // timeO(n^2)
    // space O(n)
    class BFS {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return ret;
            }

            Queue<TreeNode> queueNode = new LinkedList<TreeNode>();
            Queue<Integer> queueSum = new LinkedList<Integer>();
            queueNode.offer(root);
            queueSum.offer(0);

            while (!queueNode.isEmpty()) {
                TreeNode node = queueNode.poll();
                int rec = queueSum.poll() + node.val;

                if (node.left == null && node.right == null) {
                    if (rec == targetSum) {
                        getPath(node);
                    }
                } else {
                    if (node.left != null) {
                        map.put(node.left, node);
                        queueNode.offer(node.left);
                        queueSum.offer(rec);
                    }
                    if (node.right != null) {
                        map.put(node.right, node);
                        queueNode.offer(node.right);
                        queueSum.offer(rec);
                    }
                }
            }

            return ret;
        }

        public void getPath(TreeNode node) {
            List<Integer> temp = new LinkedList<Integer>();
            while (node != null) {
                temp.add(node.val);
                node = map.get(node);
            }
            Collections.reverse(temp);
            ret.add(new LinkedList<Integer>(temp));
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
