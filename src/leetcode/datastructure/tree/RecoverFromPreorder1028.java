package leetcode.datastructure.tree;

import java.util.Stack;

//https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal/
public class RecoverFromPreorder1028 {
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

    class DFSSolution {
        // 用来记录当前初始化到字符串的哪个位置
        int currentIndex = 0;

        public TreeNode recoverFromPreorder(String traversal) {
            // 先生成树根
            String rootval = "";
            int i = 0;
            for (; i < traversal.length() && traversal.charAt(i) != '-'; i++) {
                rootval = rootval + traversal.charAt(i);
            }
            currentIndex = i;
            TreeNode root = new TreeNode(Integer.parseInt(rootval));
            // 生成树根的左子树
            root.left = build(traversal, 0);
            // 生成树根的右子树
            root.right = build(traversal, 0);
            return root;
        }

        public TreeNode build(String traversal, int preDepth) {
            // 如果全部初始化完 终止
            if (traversal.length() == currentIndex) {
                return null;
            }
            // 如果没有初始化完
            // 统计当前层数
            int currentDepth = 0;
            for (int i = currentIndex; i < traversal.length(); i++) {
                if (traversal.charAt(i) != '-')
                    break;
                else
                    currentDepth++;
            }
            // 判断当前层数 是否 大于前一层
            if (currentDepth <= preDepth)
                return null;
            // 如果是该层的子节点 则先将该节点作为左子树创建
            // 获取当前数据
            String number = "";
            int i = currentIndex + currentDepth;
            for (; i < traversal.length(); i++) {
                if (traversal.charAt(i) == '-')
                    break;
                else
                    number = number + traversal.charAt(i);
            }
            currentIndex = i;
            // 创建节点
            TreeNode node = new TreeNode(Integer.parseInt(number));
            // 获取左子树
            node.left = build(traversal, currentDepth);
            // 获取右子树
            node.right = build(traversal, currentDepth);
            return node;
        }

    }

    // time O(traversal.length)
    // espace O(H)
    class DFS {
        public TreeNode recoverFromPreorder(String traversal) {
            TreeNode dummy = new TreeNode();
            recoverFromPreorder(dummy, traversal, -1, 0);
            return dummy.left;
        }

        // parent 为父节点，S 为原始字符串，depth 为父节点的深度，index 为 S 子串的起始位置
        int recoverFromPreorder(TreeNode parent, String S, int depth, int index) {
            if (index >= S.length()) {
                return index;
            }
            // 循环里的逻辑是添加子节点
            while (index < S.length()) {
                int currentDepth = getDepth(S, index);
                // 当前节点的深度小于或等于 parent 节点的深度，那么退出循环，这个子节点肯定不应该挂到 parent 下面
                if (currentDepth <= depth) {
                    break;
                }
                // 获取节点值
                index += currentDepth;
                int end = index;
                while (end < S.length() && S.charAt(end) != '-') {
                    end++;
                }
                int number = Integer.parseInt(S.substring(index, end));
                index = end; // 更新索引
                // 将当前节点挂到 parent 上
                TreeNode tree = new TreeNode();
                tree.val = number;
                if (parent.left == null) {
                    parent.left = tree;
                } else {
                    parent.right = tree;
                }
                // 继续遍历后续节点，完成 tree 的构造
                index = recoverFromPreorder(tree, S, currentDepth, index);
            }
            // parent 构造完毕，返回未遍历的子串的索引
            return index;
        }

        // 获取当前节点的深度，比如 --2---3----2 返回 2，-9--999 则放回 1
        int getDepth(String S, int start) {
            int index = 0;
            while (start + index < S.length() && S.charAt(start + index) == '-') {
                index++;
            }
            return index;
        }
    }

    // left root right
    // time O(traversal.length)
    // espace O(H)
    class BFS {

        public TreeNode recoverFromPreorder(String traversal) {
            Stack<TreeNode> stack = new Stack<>();
            char[] str = traversal.toCharArray();
            int i = 0;
            while (i < str.length) {
                int index = 0; // 当前构建的节点所属的level
                int curr = 0;
                while (i < str.length && str[i] == '-') {
                    index++;
                    i++;
                }
                while (i < str.length && str[i] != '-') {
                    curr = curr * 10 + (str[i] - '0');
                    i++;
                }

                TreeNode node = new TreeNode(curr);
                if (stack.isEmpty()) {
                    stack.push(node);
                } else {
                    while (stack.size() > index) {// 只要栈高>当前节点的level，就栈顶出栈
                        stack.pop();
                    }
                    TreeNode fanode = stack.peek();
                    if (fanode.left == null) {// 栈顶是父亲了，但左儿子已经存在
                        fanode.left = node;
                    } else {
                        fanode.right = node;
                    }
                    stack.push(node);// curNode自己也是父亲，入栈，等儿子
                }

            }
            while (stack.size() > 1) {
                stack.pop();
            }
            return stack.pop();
        }

    }
}
