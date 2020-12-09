package leetcode.datastructure.tree.Full;

// 计算所有的节点数量
public class FullBT {
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

    // 正常树的节点数量计算方法
    class NormalTree {

        // time O(N)
        // epsace O(N)
        public int countNodes(TreeNode root) {
            int count = 0;
            countNodes(root, count);
            return count;
        }

        private void countNodes(TreeNode root, int count) {
            if (root == null) {
                return;
            }
            // count root
            count += 1;
            countNodes(root.left, count);
            countNodes(root.right, count);
        }
    }

    // 完美二叉树 ========== 满二叉树 感觉像是翻译问题 不过学习的时候也是个坑
    // time: O(logN) logN = h = tree height======= logN
    // espace: O (logN)
    class PerfectTree {
        int h = 0;

        public int countNodes(TreeNode root) {
            while (root != null) {
                root = root.left;
                h++;
            }
            return (int) Math.pow(2, h) - 1;
        }
    }

    // 完满二叉树
    class FullTree {
        public int countNodes(TreeNode root) {
            int count = 0;
            countNodes(root, count);
            return count;
        }

        private void countNodes(TreeNode root, int count) {
            if (root == null) {
                return;
            }
            // count root
            count += 1;
            countNodes(root.left, count);
            countNodes(root.right, count);
        }
    }

    // 完全二叉树
    // 开头说了，这个算法的时间复杂度是 O(logN*logN)，这是怎么算出来的呢？

    // 直觉感觉好像最坏情况下是 O(N*logN) 吧，因为之前的 while 需要 logN 的时间，最后要 O(N) 的时间向左右子树递归：

    // return 1 + countNodes(root.left) + countNodes(root.right);
    // 关键点在于，这两个递归只有一个会真的递归下去，另一个一定会触发hl == hr而立即返回，不会递归下去。

    // 为什么呢？原因如下：

    // 一棵完全二叉树的两棵子树，至少有一棵是满二叉树：
    class CompleteTree {
        public int countNodes(TreeNode root) {

            TreeNode left = root, right = root;
            int hLeft = 0, hRight = 0;
            while (left != null) {
                left = left.left;
                hLeft++;
            }
            while (right != null) {
                right = right.right;
                hRight++;
            }
            // 此时左右子树的高度相同 特殊的完全二叉树 是一个完美二叉树
            if (hLeft == hRight) {
                return (int) Math.pow(2, hLeft) - 1;
            }
            // 如果高度不一致，按照普通的逻辑计算树
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

}
