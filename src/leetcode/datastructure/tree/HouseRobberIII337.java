package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/house-robber-iii/description/
public class HouseRobberIII337 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // recursive
    // time O(N)
    // espace O(logN)
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 爷爷+孙子偷钱
        int val1 = root.val;
        if (root.left != null) {
            val1 += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val1 += rob(root.right.left) + rob(root.right.right);
        }
        // 爸爸偷钱
        int val2 = rob(root.left) + rob(root.right);
        return Math.max(val1, val2);
    }

    // https://leetcode-cn.com/problems/house-robber-iii/solution/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
    class DP {
        public int rob(TreeNode root) {
            int[] result = robInternal(root);
            return Math.max(result[0], result[1]);
        }

        public int[] robInternal(TreeNode root) {
            if (root == null)
                return new int[2];
            int[] result = new int[2];

            int[] left = robInternal(root.left);
            int[] right = robInternal(root.right);

            result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            result[1] = left[0] + right[0] + root.val;

            return result;
        }

    }
}
