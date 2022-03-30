package leetcode.datastructure.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

// time O((n +k)logk)
// space O(k)
// https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests/
public class BusiesServers1606 {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // servers
        TreeSet<Integer> avaiable = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            avaiable.add(i);
        }
        // 递增序列
        // [end, machine server index]
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        // count how many requests has been treated each server
        int[] requests = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                avaiable.add(busy.poll()[1]);
            }
            if (avaiable.isEmpty()) {
                continue;
            }
            Integer p = avaiable.ceiling(i % k);
            if (p == null) {
                p = avaiable.first();
            }
            requests[p]++;
            busy.offer(new int[] {arrival[i] + load[i], p});
            avaiable.remove(p);
        }
        int maxRequest = Arrays.stream(requests).max().getAsInt();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == maxRequest) {
                ans.add(i);
            }
        }
        return ans;
    }
}
