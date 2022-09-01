package leetcode.datastructure.stack.monotoinic;

import java.util.Stack;

// https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop/
public class FinalPrices1475 {
    // time O(n)
    // space O(n)
    public int[] finalPrices(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int n = prices.length;
        int[] nextSmaller = new int[n];
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                nextSmaller[stack.pop()] = prices[i];
            }
            stack.push(i);
        }


        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = prices[i] - nextSmaller[i];
        }
        return ans;
    }
}
