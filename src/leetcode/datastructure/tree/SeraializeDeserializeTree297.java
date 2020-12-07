package leetcode.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
public class SeraializeDeserializeTree297 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class BFS {
        // 用于区分每个节点
        String SEP = ",";
        // 表示当前的子树为空
        String NULL = "#";

        // time O(N)
        // espace O(N)
        public String serialize(TreeNode root) {
            if (root == null)
                return "";
            StringBuilder stringBuilder = new StringBuilder();
            // 初始化队列，将root加入队列
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                // 层级遍历代码位置
                if (cur == null) {
                    stringBuilder.append(NULL).append(SEP);
                    continue;
                }
                stringBuilder.append(cur.val).append(SEP);
                q.offer(cur.left);
                q.offer(cur.right);
            }
            return stringBuilder.toString();
        }

        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            String[] nodes = data.split(SEP);
            // 第一个元素root
            TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
            // 队列 q 记录父节点，将 root 加入队列
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            for (int i = 1; i < nodes.length;) {
                // 队列中存的都是父节点
                TreeNode parent = q.poll();
                // 父节点对应的左侧子节点的值
                String left = nodes[i++];
                if (!left.equals(NULL)) {
                    parent.left = new TreeNode(Integer.parseInt(left));
                    q.offer(parent.left);
                } else {
                    parent.left = null;
                }
                // 父节点对应的右侧子节点的值
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

        @SuppressWarnings("unused")
        private void traverse(TreeNode root) {
            // 初始化队列，将 root 加入队列
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                // 层级代码
                System.out.println(root.val);
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
    }

    class DFS {

        // 这道题目的难点不在于如何遍历treenode，而在于多种方法 多种思维方式序列化和反序列化
        class PreOrderTranversal {
            // 用于区分每个节点
            String SEP = ",";
            // 表示当前的子树为空
            String NULL = "#";

            // espace O(N)
            // time O(N) N = nodes number
            // Encodes a tree to a single string.
            public String serialize(TreeNode root) {
                StringBuilder stringBuilder = new StringBuilder();
                serialize(root, stringBuilder);
                return stringBuilder.toString();
            }

            private void serialize(TreeNode root, StringBuilder sb) {
                if (root == null) {
                    sb.append(NULL).append(SEP);
                    return;
                }
                sb.append(root.val).append(SEP);
                serialize(root.left, sb);
                serialize(root.right, sb);
            }

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

        // time O(N)
        // espace O(N)
        class PostOrderTraversal {
            // 用于区分每个节点
            String SEP = ",";
            // 表示当前的子树为空
            String NULL = "#";

            public String serialize(TreeNode root) {
                StringBuilder stringBuilder = new StringBuilder();
                serialize(root, stringBuilder);
                return stringBuilder.toString();
            }

            private void serialize(TreeNode root, StringBuilder sb) {
                if (root == null) {
                    sb.append(NULL).append(SEP);
                    return;
                }
                serialize(root.left, sb);
                serialize(root.right, sb);
                sb.append(root.val).append(SEP);
            }

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
                String last = nodes.removeLast();
                if (last.equals(NULL)) {
                    return null;
                }
                TreeNode root = new TreeNode(Integer.parseInt(last));
                root.right = deserialize(nodes);
                root.left = deserialize(nodes);
                return root;
            }

        }

        /**
         * this won't wokr
         */
        class InOrderTraversal {
            // 用于区分每个节点
            String SEP = ",";
            // 表示当前的子树为空
            String NULL = "#";

            public String serialize(TreeNode root) {
                StringBuilder stringBuilder = new StringBuilder();
                serialize(root, stringBuilder);
                return stringBuilder.toString();
            }

            private void serialize(TreeNode root, StringBuilder sb) {
                if (root == null) {
                    sb.append(NULL).append(SEP);
                    return;
                }
                serialize(root.left, sb);
                sb.append(root.val).append(SEP);
                serialize(root.right, sb);
            }

            // Decodes your encoded data to tree.
            public TreeNode deserialize(String data) {
                // 将字符串转化成列表
                LinkedList<String> nodes = new LinkedList<>();
                for (String s : data.split(SEP)) {
                    nodes.addLast(s);
                }
                // return deserialize(nodes);
                return null;
            }

            // 出现了问题无法解决获取到根节点
            // TreeNode deserialize(LinkedList<String> nodes) {
            // if (nodes.isEmpty()) {
            // return null;
            // }
            // /****** 中遍历位置 ******/
            // // 列表最左侧就是根节点

            // }
        }
    }
}
