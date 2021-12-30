package leetcode.datastructure.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/meeting-rooms-ii/
public class MinMeetingRoomsII253 {
    // time O(n * logn)
    // space O(n)
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (pq.peek() <= intervals[i][0]) {
                pq.poll();
            }
            pq.add(intervals[i][1]);
        }
        return pq.size();
    }
}
