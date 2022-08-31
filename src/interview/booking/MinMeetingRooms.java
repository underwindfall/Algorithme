package interview.booking;

import java.util.Arrays;
import java.util.PriorityQueue;
// import java.util.Scanner;

// https://leetcode.cn/problems/meeting-rooms-ii/
public class MinMeetingRooms {
    public int minMeetingRooms(int[][] intervals) {
        // priorityQueue
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        q.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (q.peek() <= intervals[i][0]) {
                q.poll();
            }
            q.add(intervals[i][1]);
        }
        return q.size();
    }

    public static void main(String[] args) {
        // Scanner in = new Scanner(System.in);
        // int a;
        // a = in.nextInt();
        // int b;
        // b = in.nextInt();
        // int sum;

        // sum = addNumbers(a, b);
        // System.out.println(sum);

    }
}
