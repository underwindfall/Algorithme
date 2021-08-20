package leetcode.algo.binarysearch;

// https://leetcode-cn.com/problems/find-peak-element/solution/xun-zhao-feng-zhi-by-leetcode/
public class FindPeakElement162 {
    // time O(log N)
    // espace O(1)
    class Classsic {
        // mid - 1 < mid && mid > mid + 1 return mid
        // mid -1 < mid < mid + 1 peak in right
        // mid - 1 > mid > mid + 1 peak in left
        // mid < mid - 1 && mid < mid + 1 left or right
        public int findPeakElement(int[] nums) {
            if (nums.length == 1)
                return 0;
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (isPeak(nums, mid)) {
                    return mid;
                } else if (mid == 0 || nums[mid - 1] < nums[mid]) {
                    // ascending order
                    left = mid + 1;
                } else {
                    // deascending order
                    right = mid;
                }
            }
            return left;
        }

        boolean isPeak(int[] nums, int index) {
            if (index == 0) {
                return nums[index] > nums[index + 1];
            } else if (index == nums.length - 1) {
                return nums[index - 1] < nums[index];
            } else {
                return nums[index - 1] < nums[index] && nums[index] > nums[index + 1];
            }
        }
    }

    // time O(N)
    // espace O(1)
    class Iterative {
        public int findPeakElement(int[] nums) {
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] > nums[mid + 1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }
    }

    // time O(log(N))
    // espace O(log(N))
    // 如果你的右边已经判断过比你小，只需要往左在判断一次即可
    // 如果你的右边已经判断过比你大，下一个数字递归到mid+1
    class Recursive {
        public int findPeakElement(int[] nums) {
            return search(nums, 0, nums.length - 1);
        }

        private int search(int[] nums, int l, int r) {
            if (l == r) {
                return l;
            }
            int mid = (r - l) / 2 + l;
            if (nums[mid] > nums[mid + 1]) {
                return search(nums, l, mid);
            } else {
                return search(nums, mid + 1, r);
            }
        }
    }
}
