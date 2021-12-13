package leetcode.datastructure.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/course-schedule-iii/
public class ScheduleCourseIII630 {
    // time O(n * logN)
    // space O(n)
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
        // 优先队列中所有课程的总时间
        int total = 0;

        for (int[] course : courses) {
            int ti = course[0], di = course[1];
            if (total + ti <= di) {
                total += ti;
                q.offer(ti);
            } else if (!q.isEmpty() && q.peek() > ti) {
                total -= q.poll() - ti;
                q.offer(ti);
            }
        }

        return q.size();

    }
}
