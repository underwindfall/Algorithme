package leetcode.algo.prefixsum;

import java.util.HashSet;
import java.util.Set;

//https://leetcode-cn.com/problems/continuous-subarray-sum/
public class CheckSubarraySum523 {
    // prefix
    // sum[j]−sum[i−1]=n*k
    // 要使得两者除 k 相减为整数，需要满足sum[j] 和 sum[i−1] 对 k 取余相同。
    // time O(N)
    // espace O(N)
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k)) {
                return true;
            }
        }
        return false;
    }
}
