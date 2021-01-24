package leetcode.algo.slidingwindow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// https://leetcode-cn.com/problems/sliding-window-maximum/
// 在一堆数字中，已知最值为 A，如果给这堆数添加一个数 B，那么比较一下 A 和 B 就可以立即算出新的最值；
// 但如果减少一个数，就不能直接得到最值了，因为如果减少的这个数恰好是 A，就需要遍历所有数重新找新的最值。
// 回到这道题的场景，每个窗口前进的时候，要添加一个数同时减少一个数，
// 所以想在 O(1) 的时间得出新的最值，不是那么容易的，需要「单调队列」这种特殊的数据结构来辅助。

public class MaxSlidingWindow239 {
    /* 单调队列的实现 */
    class MonotonicQueue {
        Deque<Integer> q = new LinkedList<>();

        public void push(int n) {
            // 将小于 n 的元素全部删除
            while (!q.isEmpty() && q.getLast() < n) {
                q.pollLast();
            }
            // 然后将 n 加入尾部
            q.addLast(n);
        }

        public int max() {
            return q.getFirst();
        }

        public void pop(int n) {
            if (n == q.getFirst()) {
                q.pollFirst();
            }
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();
    
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                //先填满窗口的前 k - 1
                window.push(nums[i]);
            } else {
                // 窗口向前滑动，加入新数字
                window.push(nums[i]);
                // 记录当前窗口的最大值
                res.add(window.max());
                // 移出旧数字
                window.pop(nums[i - k + 1]);
            }
        }
        // 需要转成 int[] 数组再返回
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
}
