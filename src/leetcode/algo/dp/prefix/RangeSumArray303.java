package leetcode.algo.dp.prefix;

//https://leetcode-cn.com/problems/range-sum-query-immutable
public class RangeSumArray303 {
    // 前缀和 基本概念
    // [i, j] 的和 包含i，j 数学上的算法是 prefix(j + 1) - prefix(i)
    private int[] prefixSum;

    public RangeSumArray303(int[] nums) {
        prefixSum = new int[nums.length  + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i+1] = prefixSum[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return prefixSum[j + 1] - prefixSum[i];
    }
}
