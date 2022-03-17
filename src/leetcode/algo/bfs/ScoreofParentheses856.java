package leetcode.algo.bfs;

import java.util.Stack;

// https://leetcode-cn.com/problems/score-of-parentheses/
public class ScoreofParentheses856 {
    // time O(n)
    // space O(n)
    public int scoreOfParentheses(String s) {
        if (s == null) {    
            return 0;
        }
        int score = 0;
        Stack<Integer> stack = new Stack<>();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            // （ 即为0
            if (cs[i] == '(') {
                stack.push(0);
            } else if (cs[i] == ')') {
                int top = stack.peek();
                // 上一步不为0， 说明有嵌套，分数*2
                // 上一步为0， 说明就是最外的一层 + 1 例如 () + 1分 入栈
                top = top == 0 ? 1 : top * 2;
                // 栈顶元素弹出 并修改栈顶值 计算分数
                stack.pop();
                // 如果栈为空了 说明是一个完整结构 ()() + 2 () + 1
                if (stack.isEmpty()) {
                    score += top;
                } else {
                    // 说明是嵌套结构 比如 (())
                    // 修改栈顶的值，即上一步计算的值 + 1 或者 *2
                    int insideNum = stack.pop();
                    insideNum += top;
                    stack.push(insideNum);
                }
            }
        }
        return score;
    }
}
