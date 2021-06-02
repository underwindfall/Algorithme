package leetcode.datastructure.tree.N_Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

//https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
public class PostOrderNTree590 {
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
        public List<Integer> postorder(Node root) {
            List<Integer> result = new ArrayList<>();
            dfs(root, result);
            return result;
        }

        void dfs(Node node, List<Integer> result) {
            if (node == null)
                return;
            for (Node item : node.children) {
                dfs(item, result);
            }
            result.add(node.val);
        }
    }

    // time O(N)
    // espace O(N)
    class StackSolution {
        public List<Integer> postorder(Node root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            Stack<Node> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                result.add(node.val);
                for (Node item : node.children) {
                    stack.add(item);
                }
            }
            Collections.reverse(result);
            return result;
        }
    }
}
