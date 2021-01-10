package leetcode.algo.binarysearch;

public class FindDuplicate287 {
    // 本质是利用了1-N的属性。
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int ans = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // mid判断中间的数字
            int mid = (right - left) / 2 + left;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                // 判断左边小于mid的点
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            // 证明cnt左边都是小于mid的点 且没有重复的 原因 因为上面for 遍历的整个数组
            if (cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }
}
