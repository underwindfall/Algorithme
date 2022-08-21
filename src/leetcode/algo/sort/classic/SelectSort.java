
package leetcode.algo.sort.classic;

public class SelectSort {
    /**
     * 选择排序
     * 每次选择未排序序列中的最小值与未排序序列的第一个位置交换
     * 超时！
     */
    // time O(N^2)
    public void selectSort(int[] nums, int k) {
        int min = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            min = i;
            // 循环查找最小值
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[min] > nums[j]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(nums, i, min);
            }
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
