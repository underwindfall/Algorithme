package leetcode.algo.dp.prefix;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/subarray-sum-equals-k/
public class SubArraySumsK560 {
    // espace O(N)
    // time O(N)
    // [i, j]区间的值为k <==> prefixSum(j) - prefixSum (i - 1) == k
    // prefix( - 1) = 0
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        // key -> prefix sum
        // value -> 个数
        Map<Integer, Integer> map = new HashMap<>();
        // i== 0 ->i-1 = -1 的个数 为0
        map.put(0, 1);
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (map.containsKey(prefixSum - k)) {
                ans += map.get(prefixSum - k);
            }
            if (map.containsKey(prefixSum)) {
                map.put(prefixSum, map.get(prefixSum) + 1);
            } else {
                map.put(prefixSum, 1);
            }
        }
        return ans;
    }
}
