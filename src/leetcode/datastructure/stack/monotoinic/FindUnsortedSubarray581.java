package leetcode.datastructure.stack.monotoinic;

import java.util.Arrays;
import java.util.Stack;

//https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
public class FindUnsortedSubarray581 {
    // time O(N * logN)
    // espace O(N)
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int i = 0, j = n - 1;
        while (i <= j && nums[i] == arr[i]) {
            i++;
        }
        while (i <= j && nums[j] == arr[j]) {
            j--;
        }
        return j - i + 1;
    }

    // time O(N)
    // space O(n)
    class StackOpti {
        public int findUnsortedSubarray(int[] nums) {
            int left = nums.length, right = 0;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < nums.length; i++) {
                // 递减栈
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    left = Math.min(left, stack.pop());
                }
                stack.push(i);
            }
            // System.out.println(left);
            stack.clear();
            for (int i = nums.length - 1; i >= 0; i--) {
                // 递增栈
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    right = Math.max(right, stack.pop());
                }
                stack.push(i);
            }
            return Math.max(0, right - left + 1);
        }
    }

}
