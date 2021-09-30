package leetcode.datastructure.stack;

import java.util.Stack;

//https://leetcode-cn.com/problems/minimum-remove-to-make-valid-parentheses/
public class MinRemoveToMakeValid1249 {

    // time O(n)
    // space O(n)
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < charArray.length; i++) {
            // stack 匹配 （ 遇到就加入， 找到能匹配的就抵消
            if (charArray[i] == '(') {
                stack.add(i);
            }

            if (charArray[i] == ')') {
                // 如果不为空，说明不能抵消 ） 这个括号也要呗删除
                if (!stack.isEmpty() && stack.peek() >= 0) {
                    if (charArray[stack.peek()] == '(') {
                        stack.pop();
                    } else {
                        stack.add(i);
                    }
                } else {
                    stack.add(i);
                }
            }
        }

        for (int i = charArray.length - 1; i >= 0; i--) {
            if (!stack.isEmpty() && stack.peek() == i) {
                stack.pop();
            } else {
                sb.append(charArray[i]);
            }
        }
        return sb.reverse().toString();
    }
}
