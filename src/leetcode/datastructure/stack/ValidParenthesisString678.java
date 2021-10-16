package leetcode.datastructure.stack;

import java.util.Stack;

//https://leetcode-cn.com/problems/valid-parenthesis-string/
public class ValidParenthesisString678 {
    // time O(n)
    // space O(n)
    public boolean checkValidString(String s) {
        // leftStack stack (
        // startStack stack *

        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(i);
            } else if (c == '*') {
                starStack.push(i);
            } else if (c == ')') {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                    continue;
                }
                if (!starStack.isEmpty()) {
                    starStack.pop();
                    continue;
                }
                return false;
            }
        }

        while (!leftStack.isEmpty() && !starStack.isEmpty()) {
            int left = leftStack.pop();
            int star = starStack.pop();
            if (left < star) {
                continue;
            } else {
                return false;
            }
        }

        if (!leftStack.isEmpty()) {
            return false;
        }
        return true;
    }
}
