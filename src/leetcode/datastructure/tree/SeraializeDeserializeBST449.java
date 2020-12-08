package leetcode.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/serialize-and-deserialize-bst/
public class SeraializeDeserializeBST449 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 直观思维这道题的解决方式和297普通树的遍历并无任何差别
    // 因为BST也是特殊的一种Tree 左<root<右
    // DFS(preOrder,postOrder)
    // BFS
    // Encodes a tree to a single string.

    // class BinaryStreamBST {
    //     public String serialize(TreeNode root) {
    //         StringBuilder stringBuilder = new StringBuilder();
    //         serialize(root, stringBuilder);
    //         return stringBuilder.toString();
    //     }

    //     void serialize(TreeNode root, StringBuilder sb) {
    //         if (root == null) {
    //             return;
    //         }
    //         sb.append(intToString(root.val));
    //         serialize(root.left, sb);
    //         serialize(root.right, sb);
    //     }

    //     String intToString(int x) {
    //         char[] bytes = new char[4];
    //         for (int i = 3; i > -1; --i) {
    //             bytes[3 - i] = (char) (x >> (i * 8) & 0xff);
    //         }
    //         return new String(bytes);
    //     }

    //     public TreeNode deserialize(String data) {
    //         int pos = 0;
    //         return deserialize(data, pos, Integer.MIN_VALUE, Integer.MAX_VALUE);
    //     }

    //     private TreeNode deserialize(String data, int pos, int minValue, int maxValue) {
    //         if (pos > data.length()) {
    //             return null;
    //         }
    //         int val = stringToInt(data) + pos;
    //         if (val < minValue || val > maxValue)
    //             return null;
    //         pos += val;
    //         TreeNode root = new TreeNode(val);
    //         root.left = deserialize(data, pos, minValue, val);
    //         root.right = deserialize(data, pos, val, maxValue);
    //         return root;
    //     }

    //     int stringToInt(String bytesStr) {
    //         int result = 0;
    //         for (char b : bytesStr.toCharArray()) {
    //             result = (result << 8) + (int) b;
    //         }
    //         return result;
    //     }

    // }

    class DFS {
        String NULL = "NULL";
        String SEP = ",";

        class PreOrder {

            public String serialize(TreeNode root) {
                StringBuilder stringBuilder = new StringBuilder();
                serialize(root, stringBuilder);
                return stringBuilder.toString();
            }

            void serialize(TreeNode root, StringBuilder sb) {
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
                LinkedList<String> nodes = new LinkedList<>();
                String[] treeData = data.split(SEP);
                for (String node : treeData) {
                    nodes.addLast(node);
                }
                return deserialize(nodes);
            }

            private TreeNode deserialize(LinkedList<String> nodes) {
                if (nodes.isEmpty()) {
                    return null;
                }
                String rootVal = nodes.removeFirst();
                if (rootVal.equals(NULL)) {
                    return null;
                }
                TreeNode root = new TreeNode(Integer.parseInt(rootVal));
                root.left = deserialize(nodes);
                root.right = deserialize(nodes);
                return root;
            }
        }

        class PostOrder {
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
                LinkedList<String> nodes = new LinkedList<>();
                for (String node : data.split(SEP)) {
                    nodes.addLast(node);
                }
                return deserialize(nodes);
            }

            private TreeNode deserialize(LinkedList<String> nodes) {
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
    }

    class BFS {
        String NULL = "NULL";
        String SEP = ",";

        public String serialize(TreeNode root) {
            Queue<TreeNode> nodes = new LinkedList<>();
            StringBuilder stringBuilder = new StringBuilder();
            if (root == null) {
                return "";
            }
            nodes.offer(root);
            while (!nodes.isEmpty()) {
                TreeNode curr = nodes.poll();
                if (curr == null) {
                    stringBuilder.append(NULL).append(SEP);
                    continue;
                }
                stringBuilder.append(curr.val).append(SEP);
                nodes.offer(curr.left);
                nodes.offer(curr.right);
            }
            return stringBuilder.toString();
        }

        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            String[] nodes = data.split(SEP);
            TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            for (int i = 0; i < nodes.length;) {
                TreeNode parent = queue.poll();
                String left = nodes[i++];
                if (!left.equals(NULL)) {
                    parent.left = new TreeNode(Integer.parseInt(left));
                    queue.offer(parent.left);
                } else {
                    parent.left = null;
                }
                String right = nodes[i++];
                if (!right.equals(NULL)) {
                    parent.right = new TreeNode(Integer.parseInt(right));
                    queue.offer(parent.right);
                } else {
                    parent.right = null;
                }
            }
            return root;

        }
    }
}
