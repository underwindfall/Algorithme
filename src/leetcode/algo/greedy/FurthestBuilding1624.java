package leetcode.algo.greedy;

import java.util.PriorityQueue;

// https://leetcode.cn/problems/furthest-building-you-can-reach/
public class FurthestBuilding1624 {
    // time O(n logL)
    // space O(L)
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        // 由于我们需要维护最大的 l 个值，因此使用小根堆
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        for (int i = 1; i < n; i++) {
            int cha = heights[i] - heights[i - 1];
            if (cha > 0) {
                minheap.offer(cha);
                // 如果优先队列已满，需要拿出一个其中的最小值，改为使用砖块
                if (minheap.size() > ladders) {
                    bricks -= minheap.poll();
                }
                if (bricks < 0) {
                    return i - 1;
                }
            }
        }
        return n - 1;

    }
}
