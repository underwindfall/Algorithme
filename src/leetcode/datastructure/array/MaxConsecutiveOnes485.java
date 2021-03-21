package leetcode.datastructure.array;

// 快慢指针
/**
 * MaxConsecutiveOnes485
 */
// https://leetcode-cn.com/problems/max-consecutive-ones/
public class MaxConsecutiveOnes485 {
    //time O(N)
    //espace O(1)
    public int findMaxConsecutiveOnes(int[] nums) {
        int slow = 0;
        int maxConsecutive = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] == 1) {
                ++slow;
                maxConsecutive = Math.max(slow, maxConsecutive);
            } else {
                slow = 0;
            }
        }
        return maxConsecutive;
    }
}