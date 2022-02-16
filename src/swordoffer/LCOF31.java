package swordoffer;

import java.util.Stack;

// https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
public class LCOF31 {
    //time O(n^2)
    //space O(n)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
            
        }       
        return stack.empty();
    }
}
