package leetcode.datastructure.array;

// 双指针 快慢指针
// https://leetcode-cn.com/problems/move-zeroes
public class MoveZeroes283 {
    // 快慢指针
    // time O(N)
    // espace O(1)
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                if (fast > slow) {
                    nums[slow] = nums[fast];
                    nums[fast] = 0;
                }
                slow++;
            }
        }
    }
/*     public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    } */


}
