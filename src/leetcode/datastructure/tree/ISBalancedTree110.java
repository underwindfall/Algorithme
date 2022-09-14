package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/balanced-binary-tree/description/
public class ISBalancedTree110 {

    // time O(N*logN) logN 是高度 因为个体Height会被调用H height次
    // espace O(N)
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftCount = getHeight(root.left, 0);
        int rightCount = getHeight(root.right, 0);
        return Math.abs(leftCount - rightCount) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    int getHeight(TreeNode root, int count) {
        if (root == null) {
            return count;
        }
        count++;
        int left = getHeight(root.left, count);
        int right = getHeight(root.right, count);
        return Math.max(left, right);
    }

    // 这个思路有点倒着输出链表
    // void printListNode(ListNode node){
    //     if(node==null){
    //         return;
    //     }
    //     printListNode(node.next);
    //     System.out.println(node.val);
    // }
    // time O(N)~O(logN)
    // espace O(N)
    class FromBottomToTop {
        public boolean isBalanced(TreeNode root) {
            return getHeight(root) >= 0;
        }

        public int getHeight(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftH = getHeight(root.left);
            int rightH = getHeight(root.right);
            if (leftH == -1 || rightH == -1 || Math.abs(leftH - rightH) > 1) {
                return -1;
            } else {
                return Math.max(leftH, rightH) + 1;
            }
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