package interview.booking;

// https://leetcode.com/problems/count-number-of-nice-subarrays/
public class NumberOfSubarrays1248 {
    // time O(N)
    // espace O(1)
    public int numberOfSubarrays(int[] nums, int k) {
        int valid = 0;
        int N = nums.length;
        int left = 0, right = 0;
        int res = 0;
        while (right < N) {
            if (nums[right] % 2 == 1) {
                valid++;
            }
            right++;
            if (valid == k) {
                int tmp = right;
                while (right < N && nums[right] % 2 == 0) {
                    right++;
                }
                int rightEvenCount = right - tmp;
                int leftEvenCount = 0;
                while (nums[left] % 2 == 0) {
                    left++;
                    leftEvenCount++;
                }
                res += (rightEvenCount + 1) * (leftEvenCount + 1);
                left++;
                valid--;
            }
        }
        return res;
    }
}
