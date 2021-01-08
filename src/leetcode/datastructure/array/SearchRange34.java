package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class SearchRange34 {
    /**
     * 思路通过调换nums[mid] < target 是判断前面的数字，nums[mid] > target 判断后面的数字
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return new int[] { -1, -1 };
        }
        int leftIndex = findLeftIndex(nums, target);
        if (leftIndex == -1) {
            return new int[] { -1, -1 };
        }
        int rightIndex = findRightIndex(nums, target);
        return new int[] { leftIndex, rightIndex };
    }

    private int findLeftIndex(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                right = mid;
            } else {
                right = mid - 1;
            }
        }
        if (nums[left] == target) {
            return left;
        } else {
            return -1;
        }
    }

    private int findRightIndex(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                left = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
