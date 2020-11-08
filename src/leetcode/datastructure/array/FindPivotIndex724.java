package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/find-pivot-index/
/**
 * 思路
 * 
 * - 从左到右循环获取sum 大小
 * 
 * - 再循环一边计算出左边的sum 大小 满足 leftSum * 2 == sum - nums[j] 停下
 * 
 */
// esapce: O(n)
// time:O(n)
public class FindPivotIndex724 {
    public int pivotIndex(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int leftSum = 0;
        for (int j = 0; j < nums.length; j++) {
            if (leftSum * 2 == sum - nums[j]) {
                return j;
            } else {
                leftSum += nums[j];
            }
        }
        return -1;
    }
}
