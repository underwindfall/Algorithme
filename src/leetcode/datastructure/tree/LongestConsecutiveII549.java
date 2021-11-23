package leetcode.datastructure.tree;

//https://leetcode-cn.com/problems/binary-tree-longest-consecutive-sequence-ii/
public class LongestConsecutiveII549 {
    // time O(N)
    // espace O(N)
    class DFS {
        int max = 0;

        public int longestConsecutive(TreeNode root) {
            dfs(root);
            return max;
        }

        int[] dfs(TreeNode root) {
            // arr[0]为递增序列路径数，arr[1]为递减序列路径数
            int[] arr = new int[2];
            arr[0] = 1;
            arr[1] = 1;
            if (root == null)
                return arr;
            int[] left = dfs(root.left);
            int[] right = dfs(root.right);
            // dfs
            if (root.left != null) {
                if (root.left.val - 1 == root.val)
                    arr[1] = left[1] + 1;
                if (root.left.val + 1 == root.val)
                    arr[0] = left[0] + 1;
            }

            // 判断root在右子树中处于递增还是递减，最后arr结果中保留最大值
            if (root.right != null) {
                if (root.right.val - 1 == root.val)
                    arr[1] = Math.max(arr[1], right[1] + 1);
                if (root.right.val + 1 == root.val)
                    arr[0] = Math.max(arr[0], right[0] + 1);
            }
            // 统计结果
            max = Math.max(max, arr[0] + arr[1] - 1);
            return arr;
        }
    }

    public int longestConsecutive(TreeNode root) {
        if (root == null)
            return 0;
        int parent = dfs(root, 1) + dfs(root, -1) - 1;
        return Math.max(parent, Math.max(longestConsecutive(root.left), longestConsecutive(root.right)));
    }

    int dfs(TreeNode node, int diff) {
        if (node == null) {
            return 0;
        }

        int left = 0;
        int right = 0;

        if (node.left != null && node.left.val == node.val + diff) {
            left = dfs(node.left, diff);
        }
        if (node.right != null && node.right.val == node.val + diff) {
            right = dfs(node.right, diff);
        }

        int length = Math.max(left, right) + 1;
        return length;
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
