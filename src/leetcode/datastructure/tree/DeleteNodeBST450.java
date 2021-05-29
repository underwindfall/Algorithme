package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/delete-node-in-a-bst
public class DeleteNodeBST450 {
    public class TreeNode {
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
    
    //time O(logN)
    //espace O(H)
    // 删除的思路= find+ replace
    // 找到过程很简单 删除的时候比较复杂 要考虑种情况
    // 1.targetNode 没有子节点 皆大欢喜 删就完了
    // 2.targetNode 有一个子节点，那就需要让这个子即诶带你代替自己的位置
    // 3.targetNode 有left right 两个节点，需要去right最小的点来代替 从而构成新的BST
    TreeNode deleteNode(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        if (root.val == target) {
            // case 1
            if (root.left == null && root.right == null) {
                return null;
            }
            // case 2
            else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // case 3
            else {
                TreeNode minNode = getMin(root.right);
                root.val = minNode.val;
                //递归删除
                root.right = deleteNode(root.right, minNode.val);
            }
        } else if (root.val > target) {
            root.left = deleteNode(root.left, target);
        } else if (root.val < target) {
            root.right = deleteNode(root.right, target);
        }
        return root;
    }

    private TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
