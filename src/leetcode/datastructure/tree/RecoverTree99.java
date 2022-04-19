package leetcode.datastructure.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// https://leetcode-cn.com/problems/recover-binary-search-tree/
public class RecoverTree99 {
    // time O(n)
    // space O(h)
    class InoderImplict {
        public void recoverTree(TreeNode root) {
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode x = null, y = null, pred = null;
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null) {
                        x = pred;
                    } else {
                        break;
                    }
                }
                pred = root;
                root = root.right;
            }
            swap(x, y);
        }

        void swap(TreeNode x, TreeNode y) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }

    // time O(n)
    // space O(n)
    class InOrderExplict {
        List<Integer> nums = new ArrayList<>();

        public void recoverTree(TreeNode root) {
            // left mid right
            inOrder(root, nums);
            int[] swapped = findTwoSwapped(nums);
            restore(root, 2, swapped[0], swapped[1]);
        }

        void inOrder(TreeNode root, List<Integer> nums) {
            if (root == null) {
                return;
            }
            inOrder(root.left, nums);
            nums.add(root.val);
            inOrder(root.right, nums);
        }

        int[] findTwoSwapped(List<Integer> nums) {
            int n = nums.size();
            int index1 = -1, index2 = -1;
            for (int i = 0; i < n - 1; ++i) {
                if (nums.get(i + 1) < nums.get(i)) {
                    index2 = i + 1;
                    if (index1 == -1) {
                        index1 = i;
                    } else {
                        break;
                    }
                }
            }
            int x = nums.get(index1), y = nums.get(index2);
            return new int[] { x, y };
        }

        void restore(TreeNode root, int count, int x, int y) {
            if (root != null) {
                if (root.val == x || root.val == y) {
                    root.val = root.val == x ? y : x;
                    if (--count == 0) {
                        return;
                    }
                }
                restore(root.right, count, x, y);
                restore(root.left, count, x, y);
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
