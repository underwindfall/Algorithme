package leetcode.datastructure.hash;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/binary-subarrays-with-sum/
public class NumSubarraysWithSum930 {
    // time O(N)
    // espace O(N)
    public int numSubarraysWithSum(int[] nums, int goal) {
        int sum = 0;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        int ret = 0;
        for (int num : nums) {
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            sum += num;
            ret += cnt.getOrDefault(sum - goal, 0);
        }
        return ret;
    }
}
