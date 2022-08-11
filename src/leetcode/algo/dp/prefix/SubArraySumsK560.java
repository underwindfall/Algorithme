package leetcode.algo.dp.prefix;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/subarray-sum-equals-k/
public class SubArraySumsK560 {
    // time O(N^2)
    // space O(N)
    class prefixSumForceLoop {
        public int subarraySum(int[] nums, int k) {
            int len = nums.length;
            // 计算前缀和数组
            int[] presum = new int[len + 1];
            presum[0] = 0;
            for (int i = 0; i < len; i++) {
                presum[i + 1] = presum[i] + nums[i];
            }

            int count = 0;
            for (int left = 0; left < len; left++) {
                for (int right = left; right < len; right++) {
                    // 区间和 [left..right]，注意下标偏移
                    if (presum[right + 1] - presum[left] == k) {
                        count++;
                    }
                }
            }
            return count;
        }
    }

    // espace O(N)
    // time O(N)
    // [i, j]区间的值为k <==> prefixSum(j) - prefixSum (i - 1) == k
    // prefix( - 1) = 0
    public int subarraySum(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);

        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;

            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }

            // 然后维护 preSumFreq 的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}
