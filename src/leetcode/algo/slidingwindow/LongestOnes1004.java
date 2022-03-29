package leetcode.algo.slidingwindow;

// https://leetcode-cn.com/problems/max-consecutive-ones-iii/
// 滑动窗口判断左右的节点
// time O(n)
// space O(1)
public class LongestOnes1004 {
    public int longestOnes(int[] nums, int k) {
        // 1 -> 0
        int ans = 0;
        for (int left = 0, right = 0, count = 0; right < nums.length; right++) {
            count += nums[right] == 0 ? 1 : 0;
            while (count > k) {
                count -= nums[left] == 0 ? 1 : 0;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
