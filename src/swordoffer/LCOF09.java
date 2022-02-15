package swordoffer;

import java.util.Stack;

// https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
public class LCOF09 {
    // time O(n)
    // space O(n)
    //stack1只管add队列，stack2只管pop队列
    class CQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                if (stack2.isEmpty())
                    return -1;
                else
                    return stack2.pop();
            } else {
                return stack2.pop();
            }
        }
    }

}
