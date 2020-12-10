package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/diameter-of-binary-tree/submissions/
public class DiameterOfBinaryTree543 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // time O(N)其中 N 为二叉树的节点数，即遍历一棵二叉树的时间复杂度，每个结点只被访问一次。
    // espace O(logN)
    // 由于递归函数在递归过程中需要为每一层递归函数分配栈空间，所以这里需要额外的空间且该空间取决于递归的深度，而递归的深度显然为二叉树的高度，并且每次递归调用的函数里又只用了常数个变量，

    //这题官方题解解释为深度遍历
    // 那其实你可以想像成从底到顶进行遍历工作
     
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        // 参考 maxDepth 这道题目
        depth(root);
        return max;
    }

    int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftC = depth(root.left);
        int rightC = depth(root.right);
        max = Math.max(max, leftC + rightC);
        return Math.max(leftC, rightC) + 1;
    }
}
