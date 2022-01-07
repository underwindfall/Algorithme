package leetcode.datastructure.stack;

import java.util.Stack;

//https://leetcode-cn.com/problems/maximum-nesting-depth-of-the-parentheses/
public class MaxNestingStringDepth1614 {
    // time O(n)
    // space O(1)
    /**
     * 题目意思比较离谱。。。。求复合条件的maxDepth
     * 
     * @param s
     * @return
     */
    public int maxDepth(String s) {
        int maxDepth = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                count++;
            else if (s.charAt(i) == ')')
                count--;

            maxDepth = Math.max(maxDepth, count);
        }
        return maxDepth;
    }

    // time O(n)
    // space O(n/2)
    class StackSolution {
        public int maxDepth(String s) {
            int maxDepth = 0;
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    // 此时一定满足栈内有(括号。 或者多个(括号
                    maxDepth = Math.max(maxDepth, stack.size());
                    stack.pop();
                } else if (s.charAt(i) == '(') {
                    stack.push(s.charAt(i));
                }
            }
            return maxDepth;
        }
    }
}
