
package leetcode.algo.sort.classic;

public class SelectSort {
    /**
     * 选择排序
     * 每次选择未排序序列中的最小值与未排序序列的第一个位置交换
     * 超时！
     */
    public int selectSort(int[] nums, int k) {
        int min = 0;
        for (int i = 0; i < nums.length; i++) {
            min = nums[i];
            int tempIndex = -1;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] <= min) {
                    min = nums[j];
                    tempIndex = j;
                }
            }
            if (tempIndex == -1)
                break;
            swap(nums, i, tempIndex);
        }
        return nums[nums.length - k];
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
