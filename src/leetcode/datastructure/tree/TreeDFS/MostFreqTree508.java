package leetcode.datastructure.tree.TreeDFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.cn/problems/most-frequent-subtree-sum/
public class MostFreqTree508 {
    //time O(logN)
    //space O(N)
    // key = sum value = freq
    Map<Integer, Integer> count = new HashMap<>();
    int maxCount = 0;
    List<Integer> list = new ArrayList<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int treeSum = entry.getKey(), freq = entry.getValue();
            if (freq == maxCount) {
                list.add(treeSum);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sum = node.val + dfs(node.left) + dfs(node.right);
        maxCount = Math.max(maxCount, sum);
        count.put(sum, count.getOrDefault(sum, 0) + 1);
        return sum;
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
