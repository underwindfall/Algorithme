package leetcode.datastructure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode-cn.com/problems/merge-intervals/

public class MergeInterval56 {


    //time:O(N)
    //espace:O(N)-> count 数量
    public int[][] mergeTwoIndex(int[][] intervals) {
        int count = intervals.length;
        // 这里注意lenght - 1 是因为双指针快的那个将走在后面
        for (int slow = 0; slow < intervals.length - 1; slow++) {
            int L = intervals[slow][0];
            int R = intervals[slow][1];
            for (int fast = slow + 1; fast < intervals.length; fast++) {
                int nextL = intervals[fast][0];
                int nextR = intervals[fast][1];
                // 可以merge的
                if (L <= nextR && R >= nextL) {
                    intervals[fast][0] = Math.min(L, nextL);
                    intervals[fast][1] = Math.max(R, nextR);
                    // 直接换到下一个数组
                    intervals[slow] = null;
                    count--;
                    break;
                }
            }
        }
        int[][] ans = new int[count][];
        for (int[] interval : intervals) {
            if (interval != null) {
                ans[--count] = interval;
            }
        }
        return ans;
    }

    //time:O(NlogN)
    //espace:O(logN)
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        //logN 的时间和空间复杂度
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; i++) {
            int L = intervals[i][0], R = intervals[i][1];
            // 不merge状况，因为他的右边R比新的左边L要小
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[] { L, R });
            } else {
                // merged的右边R为新加入的R和merged大小比较
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

}
