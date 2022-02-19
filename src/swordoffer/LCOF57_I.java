package swordoffer;

//https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof/
public class LCOF57_I {
    // time O(n)
    // space O(1)
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int s = nums[i] + nums[j];
            if (s < target) {
                i++;
            } else if (s == target) {
                res[0] = nums[i];
                res[1] = nums[j];
                break;
            } else if (s > target) {
                j--;
            }
        }
        return res;
    }
}
