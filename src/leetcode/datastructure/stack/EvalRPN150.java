package leetcode.datastructure.stack;

import java.util.Stack;

public class EvalRPN150 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int a = stack.pop();
                    stack.push(stack.pop() - a); // 第二个栈顶减第一个栈顶
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int b = stack.pop();
                    stack.push(stack.pop() / b); // 第二个栈顶除以第一个栈顶
                    break;
                default:
                    stack.push(Integer.parseInt(tokens[i]));
                    break;
            }
        }
        return stack.pop();
    }
}
