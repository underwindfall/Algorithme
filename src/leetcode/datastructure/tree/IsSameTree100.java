package leetcode.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/same-tree/
public class IsSameTree100 {

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

    // time min(m, n) m = p 节点数 n = q 节点数
    // espace min(m, n) m = p 节点数 n = q 节点数
    class DFS {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            } else if (p == null || q == null) {
                return false;
            } else if (p.val != q.val) {
                return false;
            } else {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
        }
    }

    // time min(m, n) m = p 节点数 n = q 节点数
    // espace min(m, n) m = p 节点数 n = q 节点数
    class BFS {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            } else if (p == null || q == null) {
                return false;
            }
            Queue<TreeNode> queue1 = new LinkedList<>();
            Queue<TreeNode> queue2 = new LinkedList<>();
            queue1.offer(p);
            queue2.offer(q);
            while (!queue1.isEmpty() && !queue2.isEmpty()) {
                TreeNode node1 = queue1.poll();
                TreeNode node2 = queue2.poll();
                if (node1.val != node2.val) {
                    return false;
                }
                TreeNode left1 = node1.left, right1 = node1.right, left2 = node2.left, right2 = node2.right;
                if (left1 == null ^ left2 == null) {
                    return false;
                }
                if (right1 == null ^ right2 == null) {
                    return false;
                }
                if (left1 != null) {
                    queue1.offer(left1);
                }
                if (right1 != null) {
                    queue1.offer(right1);
                }
                if (left2 != null) {
                    queue2.offer(left2);
                }
                if (right2 != null) {
                    queue2.offer(right2);
                }
            }
            return queue1.isEmpty() && queue2.isEmpty();
          
        } 
    }
}
