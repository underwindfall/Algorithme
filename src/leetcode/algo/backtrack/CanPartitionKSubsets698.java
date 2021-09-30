package leetcode.algo.backtrack;

import java.util.Arrays;

public class CanPartitionKSubsets698 {
    // backtracking
    // time O(n *n!)
    // space O(n)
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % k != 0)
            return false;

        boolean[] used = new boolean[n];
        Arrays.sort(nums);

        int avg = sum / k;

        if (nums[n - 1] > avg) {
            return false;
        }

        return dfs(nums, n - 1, avg, 0, k, used);
    }

    boolean dfs(int[] nums, int begin, int target, int currSum, int k, boolean[] used) {
        int n = nums.length;
        if (k == 1) {
            return true;
        }

        if (currSum == target) {
            return dfs(nums, n - 1, target, 0, k - 1, used);
        }

        for (int i = begin; i >= 0; i--) {
            if (used[i]) {
                continue;
            }

            if (currSum + nums[i] > target) {
                continue;
            }

            used[i] = true;

            if (dfs(nums, i - 1, target, currSum + nums[i], k, used)) {
                return true;
            }

            used[i] = false;

            while (i > 0 && nums[i - 1] == nums[i]) {
                i--;
            }
        }
        return false;
    }

}
