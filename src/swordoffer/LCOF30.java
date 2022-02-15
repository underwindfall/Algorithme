package swordoffer;

import java.util.Stack;

// https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
public class LCOF30 {
    class MinStack {
        Stack<Integer> stack;

        Stack<Integer> minStack;

        MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        // time O(1)
        // space O(n)
        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty() || x <= minStack.peek()) {
                minStack.push(x);
            }
        }

        // time O(1)
        // space O(n)
        public void pop() {
            Integer element = stack.pop();
            if (element.equals(minStack.peek())) {
                minStack.pop();
            }
        }

        // time O(1)
        // space O(n)
        public int top() {
            return stack.peek();
        }

        // time O(1)
        // space O(n)
        public int getMin() {
            return minStack.peek();
        }
    }
}
