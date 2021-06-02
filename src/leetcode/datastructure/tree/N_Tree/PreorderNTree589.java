package leetcode.datastructure.tree.N_Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

//https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
public class PreorderNTree589 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // time O(N)
    // espace O(N)
    class DFS {
        public List<Integer> preorder(Node root) {
            List<Integer> result = new ArrayList<>();
            dfs(root, result);
            return result;
        }

        void dfs(Node root, List<Integer> result) {
            if (root == null) {
                return;
            }
            result.add(root.val);
            for (Node node : root.children) {
                dfs(node, result);
            }
        }
    }

    // root left....right
    // time O(N)
    // espace O(N)
    class StackSolution {
        public List<Integer> preorder(Node root) {
            List<Integer> result = new ArrayList<>();
            Stack<Node> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (node == null) {
                    continue;
                }
                result.add(node.val);
                Collections.reverse(node.children);
                for (Node item: node.children){
                    stack.add(item);
                }
            }
            return result;
        }
    }
}
