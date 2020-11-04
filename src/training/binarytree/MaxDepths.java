package training.binarytree;

//https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xefb4e/
public class MaxDepths {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

    }

    // 深部遍历
    // 从上到下嵌套
    private int answer;

    public void maximum_depth_from_top_to_bottom(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (root.right == null && root.left == null) {
            answer = Math.max(depth, answer);
        }
        maximum_depth_from_top_to_bottom(root.right, depth + 1);
        maximum_depth_from_top_to_bottom(root.left, depth + 1);
    }

    public int maximum_depth_from_bottom_to_top(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_ans = maximum_depth_from_bottom_to_top(root.left);
        int right_ans = maximum_depth_from_bottom_to_top(root.right);
        return Math.max(left_ans, right_ans) + 1;
    }

    public static void main(String[] args) {

    }
}
