package leetcode.datastructure.stack;

import java.util.Stack;

// https://leetcode-cn.com/problems/min-stack
public class MinStack155 {
    Stack<Integer> stack;

    Stack<Integer> minStack;

    MinStack155() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        Integer element = stack.pop();
        if (element.equals(minStack.peek())) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
