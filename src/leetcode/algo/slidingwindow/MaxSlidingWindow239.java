package leetcode.algo.slidingwindow;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/sliding-window-maximum/
// 在一堆数字中，已知最值为 A，如果给这堆数添加一个数 B，那么比较一下 A 和 B 就可以立即算出新的最值；
// 但如果减少一个数，就不能直接得到最值了，因为如果减少的这个数恰好是 A，就需要遍历所有数重新找新的最值。
// 回到这道题的场景，每个窗口前进的时候，要添加一个数同时减少一个数，
// 所以想在 O(1) 的时间得出新的最值，不是那么容易的，需要「单调队列」这种特殊的数据结构来辅助。

public class MaxSlidingWindow239 {
    // time O(N * logN)
    // space O(N)
    // 每当我们向右移动窗口时，我们就可以把一个新的元素放入优先队列中，此时堆顶的元素就是堆中所有元素的最大值。然而这个最大值可能并不在滑动窗口中，
    // 在这种情况下，这个值在数组 \textit{nums}nums 中的位置出现在滑动窗口左边界的左侧
    class PQ {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int[] ans = new int[nums.length - k + 1];
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]));
            for (int i = 0; i < k; i++) {
                pq.offer(new int[] { nums[i], i });
            }
            ans[0] = pq.peek()[0];
            for (int i = k; i < nums.length; i++) {
                pq.offer(new int[] { nums[i], i });
                while (pq.peek()[1] <= i - k) {
                    pq.poll();
                }
                ans[i - k + 1] = pq.peek()[0];
            }
            return ans;
        }
    }

    // time O(n)
    // space O(k)
    /* 单调队列的实现 */
    class MonotonicQueue {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            Deque<Integer> deque = new LinkedList<Integer>();
            for (int i = 0; i < k; ++i) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
            }

            int[] ans = new int[n - k + 1];
            ans[0] = nums[deque.peekFirst()];
            for (int i = k; i < n; ++i) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
                while (deque.peekFirst() <= i - k) {
                    deque.pollFirst();
                }
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
            return ans;
        }
    }
}
