package leetcode.algo.doubleindex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://leetcode-cn.com/problems/minimum-number-of-operations-to-make-array-continuous/
public class MinOperations2009 {
    /**
     * 利用set去重
     * 同时找出最小的满足n-1个不同数字有的的curr
     * 然后用总数n - curr 得到要进行操作的。
     * @param nums
     * @return
     */
    // time O(n)
    // space O(n)
    public int minOperations(int[] nums) {
        int n = nums.length;
        Set<Integer> us = new HashSet<>();
        for (int i : nums) {
            us.add(i);
        }

        int len = us.size();
        int[] arr = new int[len];
        int count = 0;
        for (int i : us) {
            arr[count++] = i;
        }
        Arrays.sort(arr);

        int curr = 0;
        int r = 0;
        for (int l = 0; l < len; l++) {
            while (r + 1 < len && arr[r + 1] - arr[l] <= n - 1) {
                r++;
            }
            curr = Math.max(curr, r - l + 1);
        }
        return n - curr;
    }
}
