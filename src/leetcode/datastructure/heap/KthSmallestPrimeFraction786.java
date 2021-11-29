package leetcode.datastructure.heap;

import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/k-th-smallest-prime-fraction/
public class KthSmallestPrimeFraction786 {
    // time O(n^2 * logk)
    // space O(k)
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Double.compare(b[0] * 1.0 / b[1], a[0] * 1.0 / a[1]));
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double t = arr[i] * 1.0 / arr[j];
                if (q.size() < k || q.peek()[0] * 1.0 / q.peek()[1] > t) {
                    if (q.size() == k)
                        q.poll();
                    q.add(new int[] { arr[i], arr[j] });
                }
            }
        }
        return q.poll();
    }
}
