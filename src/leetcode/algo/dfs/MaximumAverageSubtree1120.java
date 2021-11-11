package leetcode.algo.dfs;

//https://leetcode-cn.com/problems/maximum-average-subtree/ 
public class MaximumAverageSubtree1120 {
    // timeO (N)
    public double maximumAverageSubtree(TreeNode root) {
        return dfs(root)[2];
    }

    double[] dfs(TreeNode root) {
        if (root == null) {
            return new double[3];
        }
        double[] l = dfs(root.left), r = dfs(root.right);

        double childAvg = Math.max(l[2], r[2]);

        double sum = root.val + l[0] + r[0], cnt = 1 + l[1] + r[1];

        double maxOfTree = Math.max(childAvg, sum / cnt);

        return new double[] { sum, cnt, maxOfTree };
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
