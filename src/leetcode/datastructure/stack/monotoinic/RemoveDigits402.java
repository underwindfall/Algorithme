package leetcode.datastructure.stack.monotoinic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

// https://leetcode-cn.com/problems/remove-k-digits
public class RemoveDigits402 {
    /**
     * 可以用deque进行优化
     * 
     * @param num
     * @param k
     * @return
     */
    // time O(n)
    // space O(n)
    public String removeKdigits(String num, int k) {
        Stack<Integer> stack = new Stack<>();
        // 思路就是找到每个char 对应的比起小的数字， 并且注意不要超过k， 那就可以转换成
        // 找相邻的最小数字，并且取前num.length() - k 位
        int remain = num.length() - k;
        char nums[] = num.toCharArray();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i] && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(i);
        }
        StringBuilder sb = new StringBuilder();
        List<Integer> tmp = new ArrayList<>();
        while (!stack.isEmpty()) {
            tmp.add(0, stack.pop());
        }
        for (int i = 0; i < remain; i++) {
            char c = nums[tmp.get(i)];

            if (c == '0' && sb.length() == 0)
                continue;
            sb.append(c);
        }
        return sb.length() > 0 ? sb.toString() : "0";
    }

    // time O(n)
    // space O(n)
    class Optmization {
        public String removeKdigits(String num, int k) {
            Deque<Integer> stack = new ArrayDeque<>();
            // 思路就是找到每个char 对应的比起小的数字， 并且注意不要超过k， 那就可以转换成
            // 找相邻的最小数字，并且取前num.length() - k 位
            int remain = num.length() - k;
            char nums[] = num.toCharArray();
            for (int i = 0; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[stack.peekLast()] > nums[i] && k > 0) {
                    stack.pollLast();
                    k--;
                }
                stack.addLast(i);
            }
            StringBuilder sb = new StringBuilder();
            // List<Integer> tmp = new ArrayList<>();
            // while(!stack.isEmpty() ) {
            // tmp.add(0, stack.pop());
            // }
            for (int i = 0; i < remain; i++) {
                char c = nums[stack.pollFirst()];
                if (c == '0' && sb.length() == 0)
                    continue;
                sb.append(c);
            }

            return sb.length() > 0 ? sb.toString() : "0";
        }
    }
}
