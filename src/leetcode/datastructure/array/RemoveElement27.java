package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/remove-element/
public class RemoveElement27 {
    // time O(N)
    // espace O(1)
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
