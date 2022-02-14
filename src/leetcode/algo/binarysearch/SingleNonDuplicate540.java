package leetcode.algo.binarysearch;

// https://leetcode-cn.com/problems/single-element-in-a-sorted-array/
public class SingleNonDuplicate540 {

    // time O(n)
    // space O(1)
    class LinearySearch {
        public int SingleNonDuplicate(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n - 1; i += 2) {
                if (nums[i] != nums[i + 1])
                    return nums[i];
            }
            return nums[n - 1];
        }
    }

    // time O(logn)
    // space O(1)
    class BinarySearch {
        // index 偶数 偶数 + 1 是正常值
        public int SingleNonDuplicate(int[] nums) {
            int n = nums.length;
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = (r - l) / 2 + l;
                if (mid % 2 == 0) {
                    if (mid + 1 < n && nums[mid] == nums[mid + 1]) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                } else {
                    if (mid - 1 >= 0 && nums[mid - 1] == nums[mid]) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
            }
            return nums[r];
        }
    }
}
