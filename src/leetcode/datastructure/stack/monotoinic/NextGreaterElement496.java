package leetcode.datastructure.stack.monotoinic;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// https://leetcode-cn.com/problems/next-greater-element-i/
public class NextGreaterElement496 {
    // time O(n)
    // space O(n)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Map<Integer, Integer> map = helper(nums2);
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    private Map<Integer, Integer> helper(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();  // 存放高个元素的栈
        for (int i = nums.length - 1; i >= 0; i--) {  // 倒着往栈里放
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();  // 矮个起开，反正也被挡着了
            }

            map.put(nums[i], stack.isEmpty() ? -1 : stack.peek());  // 当前元素身后的第一个高个
            stack.push(nums[i]);  // 进队，接受之后的身高判定
        }

        return map;
    }
}
