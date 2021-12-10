package leetcode.datastructure.stack.monotoinic;

import java.util.Stack;

//https://leetcode-cn.com/problems/132-pattern/
public class Find132pattern456 {

    // time O(n)
    // space O(n)
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int last = Integer.MIN_VALUE; // 132中的2
        Stack<Integer> sta = new Stack<>();// 用来存储132中的3
        if (nums.length < 3)
            return false;
        for (int i = n - 1; i >= 0; i--) {

            if (nums[i] < last) // 若出现132中的1则返回正确值
                return true;

            // 若当前值大于或等于2则更新2（2为栈中小于当前值的最大元素）
            while (!sta.isEmpty() && sta.peek() < nums[i]) {
                last = sta.pop();
                // System.out.println(last);
            }

            // 将当前值压入栈中
            sta.push(nums[i]);
        }
        return false;
    }
}
