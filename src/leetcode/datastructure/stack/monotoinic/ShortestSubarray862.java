package leetcode.datastructure.stack.monotoinic;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/
public class ShortestSubarray862 {
    // time O(N)
    // space O(N)
    public int shortestSubarray(int[] nums, int k) {
        long[] preSum = new long[nums.length + 1];
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + (long) nums[i];
        }
        Deque<Integer> deque = new ArrayDeque<Integer>();
        for (int i = 0; i < preSum.length; i++) {
            // 将前缀和单调栈中结尾的比当前遍历的大的都弹出
            // 因为如果后面存在满足条件的j，使得preSum[j] - preSum[结尾] >= k，
            // 则preSum[j] - preSum[当前遍历]也 >= k，且长度更小，所以前面的更高的已经没有存在的意义了
            while (deque.size() != 0 && preSum[i] <= preSum[deque.peekLast()]) {
                deque.pollLast();
            }
            // 从前面找到满足的直接弹出，因为如果后面即便出现满足条件的，使得preSum[j]-preSum[开头] >= k ,
            // 则其长度也比当前的（当前下标-开头）长（当前是满足的），所以开头的满足条件的已经没有存在的意义了
            while (deque.size() != 0 && preSum[i] - preSum[deque.peekFirst()] >= k) {
                len = Math.min(len, i - deque.pollFirst());
            }
            deque.offerLast(i);
        }
        return len == Integer.MAX_VALUE ? -1 : len;
    }
}
