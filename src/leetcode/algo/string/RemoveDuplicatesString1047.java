package leetcode.algo.string;

import java.util.Stack;

// https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/
public class RemoveDuplicatesString1047 {
    // time O(n)
    // space O(n)
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
