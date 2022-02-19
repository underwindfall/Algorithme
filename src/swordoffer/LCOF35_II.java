package swordoffer;

import java.util.List;

//https://leetcode-cn.com/problems/569nqc/
public class LCOF35_II {
    // time O(n)
    // space(n)
    private static final int MEMO_SIZE = 1440;

    public int findMinDifference(List<String> timePoints) {
        boolean[] memo = new boolean[MEMO_SIZE];
        int lastTimeKey = -1;
        for (String timePoint : timePoints) {
            String[] time = timePoint.split(":");
            int key = getKey(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
            if (memo[key]) {
                return 0;
            }
            lastTimeKey = Math.max(lastTimeKey, key);
            memo[key] = true;
        }

        int minDifference = MEMO_SIZE;
        // 将第一次的 left 设置为 lastTimeKey，可以简洁代码，不用特意再找一次最尾端到最开始的时间差
        int left = lastTimeKey, right = 0;
        while (right < MEMO_SIZE) {
            if (memo[right]) {
                minDifference = Math.min((right - left + MEMO_SIZE) % MEMO_SIZE, minDifference);
                left = right;
            }
            right++;
        }
        return minDifference;
    }

    private int getKey(int hour, int minute) {
        return hour * 60 + minute;
    }
}
