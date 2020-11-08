package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/search-insert-position/
//二分法计算
//time :O(logN)
// espace: O(1)
public class SearchInsertPosition35 {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int start = 0, end = n - 1, ans = n;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (target <= nums[mid]) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }
}
