package training.binarytree;

//二分查树
//定义：就是树的左子树>root>树的右子树
public class BinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

    }

    private TreeNode createBST(int[] nums) {
        TreeNode root = null;
        for (int num : nums) {
            root = insert(root, num);
        }
        return root;
    }

    private TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val <= root.val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 5, 3, 1, 4, 7, 6 };
        BinarySearchTree tree = new BinarySearchTree();
        TreeNode root = tree.createBST(nums);
        tree.inOrder(root);
    }
}
