package swordoffer;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/
public class LCOF37 {
    // time O(n)
    // space O(n)
    class DFS {
        String SEP = ",";
        String NULL = "NULL";

        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            preorder(root, sb);
            return sb.toString();
        }

        void preorder(TreeNode node, StringBuilder sBuilder) {
            if (node == null) {
                sBuilder.append(NULL).append(SEP);
                return;
            }
            sBuilder.append(node.val).append(SEP);
            preorder(node.left, sBuilder);
            preorder(node.right, sBuilder);
        }

        // time O(n)
        // space O(n)
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            // 将字符串转化成列表
            LinkedList<String> nodes = new LinkedList<>();
            for (String s : data.split(SEP)) {
                nodes.addLast(s);
            }
            return deserialize(nodes);
        }

        TreeNode deserialize(LinkedList<String> nodes) {
            if (nodes.isEmpty()) {
                return null;
            }
            /****** 前序遍历位置 ******/
            // 列表最左侧就是根节点
            String first = nodes.removeFirst();
            if (first.equals(NULL))
                return null;
            TreeNode root = new TreeNode(Integer.parseInt(first));
            root.left = deserialize(nodes);
            root.right = deserialize(nodes);
            return root;
        }
    }

    // time O(n)
    // space O(n)
    class BFS {
        String SEP = ",";
        String NULL = "NULL";

        // time O(n)
        // space O(n)
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode curr = q.poll();
                if (curr == null) {
                    sb.append(NULL).append(SEP);
                    continue;
                }
                sb.append(curr.val).append(SEP);
                q.offer(curr.left);
                q.offer(curr.right);
            }
            return sb.toString();
        }

        // time O(n)
        // space O(n)
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty())
                return null;
            String[] nodes = data.split(SEP);
            Queue<TreeNode> q = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
            q.offer(root);
            for (int i = 1; i < nodes.length;) {
                TreeNode parent = q.poll();
                String left = nodes[i++];
                if (!left.equals(NULL)) {
                    parent.left = new TreeNode(Integer.parseInt(left));
                    q.offer(parent.left);
                } else {
                    parent.left = null;
                }
                String right = nodes[i++];
                if (!right.equals(NULL)) {
                    parent.right = new TreeNode(Integer.parseInt(right));
                    q.offer(parent.right);
                } else {
                    parent.right = null;
                }
            }
            return root;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
