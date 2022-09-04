package leetcode.datastructure.tree.TreeDFS;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/vertical-order-traversal-of-a-binary-tree/
public class VerticalTraversal987 {
    // time O(N * logN)
    // space O(N)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            if (a[1] != b[1])
                return a[1] - b[1];
            return a[2] - b[2];
        });

        int[] rootInfo = new int[] { 0, 0, root.val };
        pq.offer(rootInfo);
        dfs(root, rootInfo, pq);
        while (!pq.isEmpty()) {
            int[] last = pq.peek();
            int col = last[0];
            List<Integer> level = new ArrayList<>();
            while (!pq.isEmpty() && pq.peek()[0] == col) {
                level.add(pq.poll()[2]);
            }
            ans.add(level);
        }
        return ans;
    }

    void dfs(TreeNode node, int[] prevInfo, PriorityQueue<int[]> pq) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            int[] leftInfo = new int[] { prevInfo[0] - 1, prevInfo[1] + 1, node.val };
            pq.offer(leftInfo);
            dfs(node.left, leftInfo, pq);
        }
        if (node.right != null) {
            int[] rightInfo = new int[] { prevInfo[0] + 1, prevInfo[1] + 1, node.val };
            pq.offer(rightInfo);
            dfs(node.right, rightInfo, pq);
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
