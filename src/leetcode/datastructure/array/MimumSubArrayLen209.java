package leetcode.datastructure.array;

// https://leetcode-cn.com/problems/minimum-size-subarray-sum
public class MimumSubArrayLen209 {
    //time O(N)
    //espace O(1)
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int fast = 0, slow = 0;
        int sum = 0;
        while (fast < n) {
            sum += nums[fast];
            while (sum >= s) {
                ans = Math.min(ans, fast - slow + 1);
                sum -= nums[slow];
                slow++;
            }
            fast++;
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
