package leetcode.datastructure.heap;

import java.util.PriorityQueue;

// https://leetcode.com/problems/minimum-cost-to-connect-sticks/
public class ConnectSticks1167 {
    // time O(n)
    // space O(n)
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : sticks) {
            pq.add(i);
        }
        int cost = 0;
        while (pq.size() != 1) {
            int min = pq.poll() + pq.poll();
            pq.add(min);
            cost += min;
        }
        return cost;
    }
}
