package leetcode.datastructure.tree;

import java.util.Stack;

// https://leetcode-cn.com/problems/construct-binary-tree-from-string/
public class Str2tree536 {

    //time O(n)
    //space O(n)
    class StackSolution {
        public TreeNode str2tree(String s) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode parent = null, curNode = null;
            int sign = 1, si = 0;
            while (si < s.length()) {
                if (s.charAt(si) == ')') {
                    curNode = stack.pop();
                    parent = stack.peek();
                    if (parent.left != null) {
                        parent.right = curNode;
                    } else {
                        parent.left = curNode;
                    }
                    si++;
                } else if (s.charAt(si) == '(') {
                    si++;
                } else if (s.charAt(si) == '-') {
                    sign = -1;
                    si++;
                } else {
                    int num = 0;
                    while (si < s.length() && s.charAt(si) >= '0' && s.charAt(si) <= '9') {
                        num = num * 10 + s.charAt(si) - '0';
                        si++;
                    }
                    num *= sign;
                    sign = 1;
                    stack.push(new TreeNode(num));
                }
            }

            if (!stack.isEmpty()) {
                return stack.peek();
            }
            return parent;
        }
    }

    class RecursiveSolution {
        int i = 0;

        public TreeNode str2tree(String s) {
            if (s == null || s.length() == 0) {
                return null;
            }
            return helper(s.toCharArray());
        }

        TreeNode helper(char[] s) {
            if (i == s.length) {
                return null;
            }
            // extrac number
            StringBuilder num = new StringBuilder();
            while (i < s.length && s[i] != '(' && s[i] != ')') {
                num.append(s[i]);
                i++;
            }
            // create new node
            TreeNode node = null;
            if (num.length() != 0) {
                node = new TreeNode(Integer.parseInt(num.toString()));
            }
            if (i < s.length && s[i] == '(') {
                // create left child node
                i++;
                node.left = helper(s);
                i++;
                if (i < s.length && s[i] == '(') {
                    // create right child node
                    i++;
                    node.right = helper(s);
                    i++;
                }
            }
            // if meet ')' return parent node
            return node;

        }
    }

    // time O(n)
    // space O(n)
    class Recursive {
        public TreeNode str2tree(String s) {
            if (s == null || s.length() == 0) {
                return null;
            }
            int firstParent = s.indexOf("(");
            int val = firstParent == -1 ? Integer.parseInt(s) : Integer.parseInt(s.substring(0, firstParent));
            TreeNode cur = new TreeNode(val);
            if (firstParent == -1) {
                return cur;
            }
            int start = firstParent, leftParentCount = 0;
            for (int i = start; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    leftParentCount++;
                } else if (s.charAt(i) == ')') {
                    leftParentCount--;
                }
                if (leftParentCount == 0 && start == firstParent) {
                    cur.left = str2tree(s.substring(start + 1, i));
                    start = i + 1;
                } else if (leftParentCount == 0) {
                    cur.right = str2tree(s.substring(start + 1, i));
                }
            }
            return cur;
        }
    }

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
}
