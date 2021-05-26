package leetcode.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/symmetric-tree/description/
public class isSymmetric101 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 嵌套
    // time : O(N)
    // espace: O(height)
    class DFS {
        public boolean isSymmetric(TreeNode root) {
            return checkRecursive(root, root);
        }

        boolean checkRecursive(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            return left.val == right.val && checkRecursive(left.left, right.right)
                    && checkRecursive(left.right, right.left);
        }
    }

    // bfs
    //time O(N)
    //espace O(H)
    class BFS {
        public boolean isSymmetric(TreeNode root) {
            if(root==null || (root.left==null && root.right==null)) {
                return true;
            }
            //用队列保存节点
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            //将根节点的左右孩子放到队列中
            queue.add(root.left);
            queue.add(root.right);
            while(queue.size()>0) {
                //从队列中取出两个节点，再比较这两个节点
                TreeNode left = queue.poll();
                TreeNode right = queue.poll();
                //如果两个节点都为空就继续循环，两者有一个为空就返回false
                if(left==null && right==null) {
                    continue;
                }
                if(left==null || right==null) {
                    return false;
                }
                if(left.val!=right.val) {
                    return false;
                }
                //将左节点的左孩子， 右节点的右孩子放入队列
                queue.add(left.left);
                queue.add(right.right);
                //将左节点的右孩子，右节点的左孩子放入队列
                queue.add(left.right);
                queue.add(right.left);
            }
            
            return true;
        }

    }
}
