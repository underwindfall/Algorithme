package interview.datadog;

//https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
public class MaxPathSumBinaryTree124 {

    //time O(N)
    //espace O(N)
    int result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return result;
    }

    // 函数功能：返回当前节点能为父亲提供的贡献，需要结合上面的图来看！
    private int dfs(TreeNode root) {
        // 如果当前节点为叶子节点，那么对父亲贡献为 0
        if (root == null)
            return 0;
        // 如果不是叶子节点，计算当前节点的左右孩子对自身的贡献left和right
        int left = dfs(root.left);
        int right = dfs(root.right);
        // 更新最大值，就是当前节点的val 加上左右节点的贡献。
        result = Math.max(result, root.val + left + right);
        // 计算当前节点能为父亲提供的最大贡献，必须是把 val 加上！
        int max = Math.max(root.val + left, root.val + right);
        // 如果贡献小于0的话，直接返回0即可！
        return max < 0 ? 0 : max;
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
