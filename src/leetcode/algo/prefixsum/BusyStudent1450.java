package leetcode.algo.prefixsum;

import java.util.Arrays;

// https://leetcode.cn/problems/number-of-students-doing-homework-at-a-given-time/
public class BusyStudent1450 {
    // time O(N)
    // space O(1)
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int n = startTime.length;
        int maxEndTime = Arrays.stream(endTime).max().getAsInt();
        if (queryTime > maxEndTime) {
            return 0;
        }
        int[] count = new int[maxEndTime + 2];
        for (int i = 0; i < n; i++) {
            count[startTime[i]]++;
            count[endTime[i] + 1]--;
        }

        int ans = 0;
        for (int i = 0; i <= queryTime; i++) {
            ans += count[i];
        }
        return ans;
    }
}
