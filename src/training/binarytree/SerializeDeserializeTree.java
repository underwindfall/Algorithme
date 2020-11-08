package training.binarytree;

import java.util.LinkedList;

// https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
// 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

// 示例: 

// 你可以将以下二叉树：

//     1
//    / \
//   2   3
//      / \
//     4   5

// 序列化为 "[1,2,3,null,null,4,5]"

public class SerializeDeserializeTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder ans = new StringBuilder();
        serialize(root, ans);
        return ans.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
        } else {
            sb.append(root.val);
            sb.append(",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        LinkedList<String> queue = new LinkedList<>();
        for (String s : data.split(",")) {
            queue.add(s);
        }
        return deserialize(queue);
    }

    private TreeNode deserialize(LinkedList<String> queue) {
        String data = queue.poll();
        if ("null".equals(data)) {
            return null;
        }
        TreeNode temp = new TreeNode(Integer.parseInt(data));
        temp.left = deserialize(queue);
        temp.right = deserialize(queue);
        return temp;
    }

    public static void main(String[] args) {
        SerializeDeserializeTree serializeDeserializeTree = new SerializeDeserializeTree();
        TreeNode root = serializeDeserializeTree.new TreeNode(1);
        root.left = serializeDeserializeTree.new TreeNode(2);
        TreeNode right = serializeDeserializeTree.new TreeNode(5);
        root.right = right;

        right.left = serializeDeserializeTree.new TreeNode(4);
        root.right.right = serializeDeserializeTree.new TreeNode(5);
        System.out.println(serializeDeserializeTree.serialize(root));
    }
}
