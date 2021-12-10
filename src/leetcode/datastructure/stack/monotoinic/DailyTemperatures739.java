package leetcode.datastructure.stack.monotoinic;

import java.util.Stack;

// https://leetcode-cn.com/problems/daily-temperatures/
public class DailyTemperatures739 {
    //time O(n)
    //space O(n)
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.add(i);
        }
        return res;
    }
}
