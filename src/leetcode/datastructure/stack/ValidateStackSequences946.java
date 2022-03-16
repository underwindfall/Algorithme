package leetcode.datastructure.stack;

import java.util.Stack;

// https://leetcode-cn.com/problems/validate-stack-sequences/
public class ValidateStackSequences946 {
    // time O(n^2) -> worst cases O(n) normal cases
    // space O(n)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length;
        Stack<Integer> stack = new Stack<>();

        int j = 0;
        for (int x : pushed) {
            stack.push(x);
            while (!stack.isEmpty() && j < N && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return j == N;
    }
}
