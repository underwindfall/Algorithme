package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
// 思路
//time O(N)
//espace O(N)
//判断递归条件
//看看root终止条件
//看看正常root的迭代fuc
//看看左边 右边往往一致
//参数不够，func来凑
// fun solve(root)
// if root == null return 
// if f(root) h(root)
// left = solve(root)
// right = solve(root)
// return g(root,left,right)
public class MaxDepthBinaryTree104 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        int maxDepth = 0;
        return maxDepth(root, maxDepth);
    }

    int maxDepth(TreeNode root, int count) {
        if (root == null) {
            return count;
        }
        count++;
        int leftCount = maxDepth(root.left, count);
        int rightCount = maxDepth(root.right, count);
        return Math.max(leftCount, rightCount);
    }
}
