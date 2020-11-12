package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
public class FindMinInRotatedArray153 {
    class DoubleIndex {
        // 首先想到的是双指针
        // time O(N/2)
        // espace O(1)
        public int findMin(int[] nums) {
            int head = 0;
            int last = nums.length - 1;
            if (nums[head] < nums[last]) {
                return nums[head];
            }
            while (nums[head] > nums[last]) {
                head++;
            }
            return nums[head];
        }
    }

    // 有序数组二分法查找
    // time O(logN)
    // espace O(1)
    class BinarySearch {
        public int findMin(int[] nums) {
            int start = 0, end = nums.length - 1;
            while (start < end) {
                int mid = (end + start) / 2;
                // 说明中间的数字是小于最后的数字
                // 应该是前面是小智
                if (nums[mid] < nums[end]) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }

            return nums[start];
        }
    }

}
