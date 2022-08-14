package leetcode.algo.sort.classic;

public class ShellSort {
    /**
     * 直接插入排序对于部分有序数组很有效，但是每次只能交换相邻元素
     * shell排序在直接插入排序数组的基础上进行改进，可以交换距离为h的元素，将一个大数组拆分为若干个距离为h的小数组，在小数组中使用直接插入排序，不断降低h的值到h=1，在大数组上使用直接插入排序
     */
    public int ShellSort(int[] nums, int k) {
        int N = nums.length;
        int h = 1;
        while (h < N / 5)
            h = 5 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && nums[j] < nums[j - h]; j -= h) {
                    swap(nums, j, j - h);
                }
            }
            h = h / 5;
        }
        return nums[nums.length - k];
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
