package leetcode.algo.dp.prefix;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/continuous-subarray-sum/
public class ContinousSubArraySum523 {
    class Prefix {
        public boolean checkSubarraySum(int[] nums, int k) {
            //索引值
            Map<Integer, Integer> map = new HashMap<>();
            // 细节2
            map.put(0, -1);
            int presum = 0;
            for (int i = 0; i < nums.length; ++i) {
                presum += nums[i];
                // 细节1，防止 k 为 0 的情况
                int key = k == 0 ? presum : presum % k;
                if (map.containsKey(key)) {
                    if (i - map.get(key) >= 2) {
                        return true;
                    }
                    // 因为我们需要保存最小索引，当已经存在时则不用再次存入，不然会更新索引值
                    continue;
                }
                map.put(key, i);
            }
            return false;
        }
    }

    // time O(N^3)
    // espace O(1)
    class BruteForce {
        public boolean checkSubarraySum(int[] nums, int k) {
            for (int start = 0; start < nums.length - 1; start++) {
                for (int end = start + 1; end < nums.length; end++) {
                    int sum = 0;
                    for (int i = start; i <= end; i++)
                        sum += nums[i];
                    if (sum == k || (k != 0 && sum % k == 0))
                        return true;
                }
            }
            return false;
        }
    }
}
