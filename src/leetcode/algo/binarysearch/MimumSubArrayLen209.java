package leetcode.algo.binarysearch;

//https://leetcode-cn.com/problems/minimum-size-subarray-sum
public class MimumSubArrayLen209 {
    // time O(logN)
    // espace O(1)
    class BinarySearch {
        public int minSubArrayLen(int target, int[] nums) {
            int left = 1, right = nums.length, ans = Integer.MAX_VALUE;
            while (left <= right) {
                int mid  = (right + left) / 2;
                if (windowExist(mid, target, nums)) {
                    right = mid - 1;
                    ans = right;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }

        boolean windowExist(int size, int target, int[] nums) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i >= size) {
                    sum -= nums[i - size];
                }
                sum += nums[i];
                if (sum >= target) {
                    return true;
                }
            }
            return false;
        }
    }

    // time O(N)
    // espac O(1)
    class SlidingWindow {
        public int minSubArrayLen(int target, int[] nums) {
            int n = nums.length;
            int fast = 0, slow = 0;
            int sum = 0;
            int ans = Integer.MAX_VALUE;
            while (fast < n) {
                sum += nums[fast];
                while (sum >= target) {
                    ans = Math.min(ans, fast - slow + 1);
                    sum -= nums[slow];
                    slow++;
                }
                fast++;
            }
            return ans == Integer.MAX_VALUE ? 0 : ans;
        }
    }
}
