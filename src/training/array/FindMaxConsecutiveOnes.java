package training.array;

/**
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * 
 * 示例 1:
 * 
 * 输入: [1,1,0,1,1,1] 输出: 3 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3. 注意：
 * 
 * 输入的数组只包含 0 和1。 输入数组的长度是正整数，且不超过 10,000
 * 
 * 
 */
public class FindMaxConsecutiveOnes {
    //思想一样 优化一下
    //双指针 快慢指针
    //time: O(N)
    //espace: O(1)
    static int findMaxConsecutiveOnes1(int[] nums) {
        int slow = 0;
        int oldSlow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] == 1) {
                slow++;
            } else {
                oldSlow = Math.max(oldSlow, slow);
                slow = 0;
            }
        }
        return Math.max(slow, oldSlow);
    }
    //双指针 快慢指针
    //time: O(N)
    //espace: O(1)
    static int findMaxConsecutiveOnes(int[] nums) {
        int slow = 0;
        int oldSlow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] == 1) {
                ++slow;
                oldSlow = Math.max(oldSlow, slow);
            } else {
                slow = 0;
            }
        }
        return oldSlow;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 0, 1, 1, 0, 1 };
        System.out.println(findMaxConsecutiveOnes(nums));
    }
}
