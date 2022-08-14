package leetcode.algo.sort.classic;

public class CountSort {
    /**
     * 计数排序 是一种桶排序
     * 需要一个额外数组存储数组中每个元素出现次数，
     * 然后从小到大依次插入回数组
     * 4ms 赶上快排了 以空间换时间，元素少时很快
     */
    public int countSort(int[] nums, int k) {
        int max = maxValue(nums);
        int min = minValue(nums);
        // System.out.println(max);
        int bucketLen = max - min + 1;
        int[] bucket = new int[bucketLen];
        for (int i = 0; i < nums.length; i++)
            bucket[nums[i] - min] += 1; // 偏移值，以到以0为起点，max为终点
        // System.out.println(Arrays.toString(bucket));
        int newIndex = 0;
        for (int i = 0; i < bucketLen; i++) {
            while (bucket[i] > 0) {
                nums[newIndex++] = i + min; // 再移回来偏移值
                bucket[i] -= 1;
            }
            // System.out.println(Arrays.toString(bucket));
        }
        // System.out.println(Arrays.toString(nums));
        return nums[nums.length - k];
    }

    int minValue(int[] nums) {
        int min = nums[0];
        for (int i = 0; i < nums.length; i++)
            min = nums[i] < min ? nums[i] : min;
        return min;
    }

    int maxValue(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = nums[i] > max ? nums[i] : max;
        }
        // System.out.format("max: %d \n",max);
        return max;
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
