package leetcode.datastructure.heap;

import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/minimum-cost-to-connect-sticks/
public class ConnectSticks1167 {
    // time O(N * logN)
    // space O(N)
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : sticks) {
            pq.offer(i);
        }
        int cost = 0;
        while (pq.size() != 1) {
            int min = pq.poll() + pq.poll();
            pq.offer(min);
            cost += min;
        }
        return cost;
    }
}
