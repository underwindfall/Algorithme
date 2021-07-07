package leetcode.datastructure.heap;


import java.util.PriorityQueue;

//https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/
public class KthLargest703 {
    // time O(N * logK)
    // espace O(logK)
    class JavaPriorityQueue {
        PriorityQueue<Integer> pq;
        int k;

        JavaPriorityQueue(int k, int nums[]) {
            this.k = k;
            pq = new PriorityQueue<>();
            for (int x : nums) {
                add(x);
            }
        }

        public int add(int val) {
            pq.offer(val);
            if (pq.size() > k) {
                pq.poll();
            }
            return pq.peek();
        }
    }

}
