package leetcode.algo.prefixsum;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/contiguous-array/
class FindMaxLength525 {
    // time O(N)
    // espace O(N)
    public int findMaxLength(int[] nums) {
        int max = 0, prefixSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                prefixSum++;
            } else {
                prefixSum--;
            }
            if (map.containsKey(prefixSum)) {
                max = Math.max(max, i - map.get(prefixSum));
            } else {
                map.put(prefixSum, i);
            }
        }
        return max;
    }
}