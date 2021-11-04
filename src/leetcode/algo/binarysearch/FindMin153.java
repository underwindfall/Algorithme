package leetcode.algo.binarysearch;

// https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
public class FindMin153 {
    // time O(logn)
    // espace O(1)
    // mid (all left) > mid && (all right) < mid
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (nums[high] < nums[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            } 
        }
        return nums[low];
    }
}
