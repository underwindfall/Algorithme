package leetcode.datastructure.stack.monotoinic;

import java.util.Stack;

// https://leetcode-cn.com/problems/next-greater-element-ii/
public class NextGreaterElementII503 {
    // time O(n)
    // space O(n)
    public int[] nextGreaterElements(int[] nums) {
        // index, value
        int n = nums.length;

        Stack<Integer> stack = new Stack<>();
        int res[] = new int[n];
        for (int i = n * 2 - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i % n]) {
                stack.pop();
            }
            res[i % n] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i % n);
        }

        return res;
    }

}
