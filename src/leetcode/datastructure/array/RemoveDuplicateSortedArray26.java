package leetcode.datastructure.array;

public class RemoveDuplicateSortedArray26 {

    // 想到的双指针
    // 因为是排序数组 所以只需要每次记录最新的左边的数组就好 除了重复了就下一个
    // 注意点 注意审题 他根本要求的不是删除这个重复的数组 只是截取某段数组的输出即可
    // time O(N)
    // espace O(1)
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int latestNum;
        for (int fast = 1; fast < nums.length; fast++) {
            latestNum = nums[slow];
            if (nums[fast] != latestNum) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }
}
