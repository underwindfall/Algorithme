package leetcode.datastructure.heap;

import java.util.PriorityQueue;

// https://leetcode.com/problems/minimize-deviation-in-array/
public class MinimumDeviation1675 {
    // time O(k * logN)
    // space O(n)
    class PQ {
        public int minimumDeviation(int[] nums) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            int minimum = Integer.MAX_VALUE;
            for (int num : nums) {
                if (num % 2 == 0) {
                    pq.offer(num);
                    minimum = Math.min(num, minimum);
                } else {
                    pq.offer(num * 2);
                    minimum = Math.min(num * 2, minimum);
                }
            }

            int ans = Integer.MAX_VALUE;
            while (!pq.isEmpty()) {
                int value = pq.poll();
                ans = Math.min(ans, value - minimum);
                if (value % 2 == 0) {
                    pq.offer(value / 2);
                    minimum = Math.min(value / 2, minimum);
                } else {
                    break;
                }
            }
            return ans;
        }
    }
}
