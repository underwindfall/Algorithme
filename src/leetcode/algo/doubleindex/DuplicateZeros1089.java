package leetcode.algo.doubleindex;

import java.util.Stack;

// https://leetcode.cn/problems/duplicate-zeros/
public class DuplicateZeros1089 {
    // time O(n)
    // space O(n)
    public void duplicateZeros(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int i : arr) {
            stack.push(i);
        }
        int n = arr.length;
        int index = 0;
        while (index < n) {
            int num = stack.peek();
            arr[index++] = num;
            if (num == 0 && index < n) {
                arr[index++] = num;
            }
            stack.pop();
        }
    }
}
