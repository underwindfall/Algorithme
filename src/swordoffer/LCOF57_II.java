package swordoffer;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
public class LCOF57_II {
    // 滑动窗口 滑动解决target问题
    // time O(n)
    // space O(1)
    public int[][] findContinuousSequence(int target) {
        int left = 1; //窗口left边界
        int right = 1; //窗口right边界
        int sum = 0;
        List<int[]> res = new ArrayList<>();
        while (left <= target / 2) {
            if (sum < target) {
                //右边界向右移动
                sum += right;
                right++;
            } else if (sum > target) {
                //左边界向右移动
                sum -= left;
                left++;
            } else {
                //记录结果
                int[] arr = new int[right - left];
                for (int k = left; k < right; k++) {
                    arr[k - left] = k;
                }
                res.add(arr);
                //左边届向右移动
                sum -= left;
                left++;
            } 
        }
        return res.toArray(new int[res.size()][]);
    }
}
