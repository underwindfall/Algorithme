package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/description/
public class FindModeBST501 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int maxCount = 1;
    private int curCount = 1;
    TreeNode pre = null;

    public int[] findMode(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int[] result = new int[list.size()];
        int index = 0;
        for (int i : list) {
            result[index++] = i;
        }
        return result;
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorder(node.left, list);
        if (pre != null) {
            if (pre.val == node.val) {
                curCount++;
            } else {
                curCount = 1;
            }
        }
        if (curCount > maxCount) {
            // 就一个众数
            list.clear();
            list.add(node.val);
            maxCount = curCount;
        } else if (curCount == maxCount) {
            // 说明不止一个值
            list.add(node.val);
        }
        pre = node;
        inorder(node.right, list);
    }
}
