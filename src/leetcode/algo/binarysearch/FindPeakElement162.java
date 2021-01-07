package leetcode.algo.binarysearch;

// https://leetcode-cn.com/problems/find-peak-element/solution/xun-zhao-feng-zhi-by-leetcode/
public class FindPeakElement162 {
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
