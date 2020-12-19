package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

// https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/description/
public class FindTarget653 {
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

    // 中序遍历 有个方法是迭代到最左边的叶子节点，就是最小的数。 然后进行loop循环遍历，结合二分搜索的特性进行遍历。
    // time O(logN/N)
    // espace O(N)
    class DFS {
        public boolean findTarget(TreeNode root, int k) {
            TreeNode p = new TreeNode(), q = new TreeNode();
            Stack<TreeNode> stack = new Stack<>();
            p = root;
            q = root;
            while (p != null || !stack.isEmpty()) {
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
                if (!stack.isEmpty()) {
                    p = stack.pop();
                    q = root;
                    while (q != null) {
                        if (p.val + q.val == k && p != q)
                            return true;
                        else if (p.val + q.val > k)
                            q = q.left;
                        else if (p.val + q.val < k)
                            q = q.right;
                        else
                            break;
                    }
                    p = p.right;
                }
            }
            return false;
        }
    }

    //  time O(N)
    // espace O(logN)
    class BST {
        List<Integer> list = new ArrayList<>();

        // time O(logN +N)
        // espace O(logN+1)
        public boolean findTarget(TreeNode root, int k) {
            inOrderTraverse(root);
            int l = 0;
            int r = list.size() - 1;
            while (l < r) {
                if (list.get(l) + list.get(r) < k) {
                    l++;
                } else if (list.get(l) + list.get(r) > k) {
                    r--;
                } else {
                    return true;
                }
            }
            return false;
        }

        // time O(N)
        // espace O(logN)
        void inOrderTraverse(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrderTraverse(root.left);
            list.add(root.val);
            inOrderTraverse(root.right);
        }
    }

    // time O(N)
    // espace O(N)
    class BFS {

        public boolean findTarget(TreeNode root, int k) {
            Set<Integer> set = new HashSet<>();
            return find(set, root, k);
        }

        private boolean find(Set<Integer> set, TreeNode root, int k) {
            if (root == null) {
                return false;
            }
            if (set.contains(k - root.val)) {
                return true;
            }
            set.add(root.val);
            return find(set, root.left, k) || find(set, root.right, k);
        }
    }
}
