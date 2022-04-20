package leetcode.datastructure.tree.BasicBST;

import java.util.Stack;

// https://leetcode-cn.com/problems/binary-search-tree-iterator/
public class BSTInteractor173 {

    //time o(N)
    //space O(N)
    class BSTIterator {
        private TreeNode cur;
        private Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            cur = root;
            stack = new Stack<TreeNode>();
        }

        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            int ret = cur.val;
            cur = cur.right;
            return ret;
        }

        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
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
