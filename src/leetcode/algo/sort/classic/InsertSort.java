package leetcode.algo.sort.classic;

public class InsertSort {
    /**
     * 直接插入排序 120ms
     * 直接将数组中一个数插入到已经进行排序的数组中去
     * 前半部分都已经排好序
     */
    public int InsertSort(int[] nums, int k) {
        for (int i = 1; i < nums.length; i++) {
            int temp = i;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[temp] < nums[j]) {
                    swap(nums, temp, j);
                    temp -= 1;
                }
            }
            // System.out.println(Arrays.toString(nums));
        }
        return nums[nums.length - k];
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
