package leetcode.algo.binarysearch;

// https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
public class Search33 {
    // 虽说是旋转，但是必定是两个有序数组组成
    // 若num[0] < nums[mid] 左边是升序排列 在左边的数组里面查找
    // 若num[mid] < nums[last] 右边是升序排列 在左边的数组里面查找
    class Normal {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            if (nums.length == 0) {
                return -1;
            }
            if (nums.length == 1) {
                return nums[0] == target ? 0 : -1;
            }
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[0] <= nums[mid]) {
                    if (nums[0] <= target && nums[mid] > target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    if (nums[mid] < target && nums[right] >= target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
            return -1;
        }
    }

    class Recursive {
        public int search(int[] nums, int target) {
            return binarySearch(nums, 0, nums.length - 1, target);
        }

        private int binarySearch(int[] nums, int i, int j, int target) {
            int left = 0;
            int right = nums.length - 1;
            if (nums.length == 0) {
                return -1;
            }
            if (nums.length == 1) {
                return nums[0] == target ? 0 : -1;
            }
            if (left >= right) {
                return -1;
            }
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (nums[left] <= nums[right]) {
                if (target < nums[mid] && target >= nums[left]) {
                    return binarySearch(nums, left, mid - 1, target);
                } else {
                    return binarySearch(nums, mid + 1, right, target);
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    return binarySearch(nums, mid + 1, right, target);
                } else {
                    return binarySearch(nums, left, mid - 1, target);
                }
            }
        }
    }
}
