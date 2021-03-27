package leetcode.algo.dp.prefix;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k/
public class MaxSubArraySumLen325 {

    class Prefix {
        // value -> end index
        // key -> prefixSum
        public int maxSubArrayLen(int[] nums, int k) {
            int max = 0;
            int prefixSum = 0;
            Map<Integer, Integer> map = new HashMap<>();
            // 这一步很有必要 对于计算时候
            map.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                prefixSum += nums[i];
                if (!map.containsKey(prefixSum)) {
                    map.put(prefixSum, i);
                }
                // 满足 prefixSum - map == k
                if (map.containsKey(prefixSum - k)) {
                    max = Math.max(max, i - map.get(prefixSum - k));
                }
            }
            return max;
        }
    }

    class SlidingWindow {
        public int maxSubArrayLen(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            int sum = 0;
            int max = 0;
            int index = 0;
            while (index < nums.length) {
                sum += nums[index];
                Integer sub = sum - k;
                if (map.containsKey(sub)) {
                    max = Math.max(max, index - map.get(sub));
                }
                if (!map.containsKey(sum)) {
                    map.put(sum, index);
                }
                index++;
            }
            return max;
        }
    }

    /**
     * time O(n^2) espace O(n)
     */
    class BruteForce {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;

        public int maxSubArrayLen(int[] nums, int k) {
            for (int i = 0; i < nums.length; i++) {
                int sum = 0;
                int endIndex = map.getOrDefault(i, 0) + i;
                for (int j = endIndex; j < nums.length; j++) {
                    sum += nums[j];
                    if (sum == k) {
                        int length = j - i + 1;
                        max = Math.max(max, length);
                        if (j > endIndex)
                            map.put(i, length);
                    }
                }
            }
            return max;
        }
    }

}
