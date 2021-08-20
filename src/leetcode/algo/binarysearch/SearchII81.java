package leetcode.algo.binarysearch;

//https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
public class SearchII81 {

    class Solution {
        public boolean search(int[] nums, int target) {
            int n = nums.length;
            if (n == 0) {
                return false;
            }
            if (n == 1) {
                return nums[0] == target;
            }
            int l = 0, r = n - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (nums[mid] == target) {
                    return true;
                }
                if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                    ++l;
                    --r;
                } else if (nums[l] <= nums[mid]) {
                    if (nums[l] <= target && target < nums[mid]) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    if (nums[mid] < target && target <= nums[n - 1]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
            }
            return false;
        }
    }
    
    
    // time O(logN)
    // espace O(1)
    // 分开的位置会导致 丧失二义性
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        // 恢复二义性
        while (l < r && nums[0] == nums[r]) {
            r--;
        }
        // 第一次二分 找出旋转点
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] >= nums[0]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        int idx = n;
        if (nums[r] < nums[0] && r + 1 < n) {
            idx = r + 1;
        }
        // 第二次二分，找目标值
        int ans = find(nums, 0, idx - 1, target);
        if (ans != -1)
            return true;
        ans = find(nums, idx, n - 1, target);
        return ans != -1;

    }

    int find(int[] nums, int l, int r, int target) {
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid - 1;
            }
        }
        return nums[r] == target ? r : -1;
    }
}
