package leetcode.datastructure.array;

/**
 * MaxConsecutiveOnes485
 */
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