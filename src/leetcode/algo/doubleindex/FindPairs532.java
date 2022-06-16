package leetcode.algo.doubleindex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.cn/problems/k-diff-pairs-in-an-array/
public class FindPairs532 {
    // time O(n)
    // space O(n)
    class Hash {
        public int findPairs(int[] nums, int k) {
            Set<Integer> visited = new HashSet<Integer>();
            Set<Integer> res = new HashSet<Integer>();
            for (int num : nums) {
                if (visited.contains(num - k)) {
                    res.add(num - k);
                }
                if (visited.contains(num + k)) {
                    res.add(num);
                }
                visited.add(num);
            }
            return res.size();

        }
    }

    // time O(n * logN)
    // space O(1)
    class DoubleIndex {
        public int findPairs(int[] nums, int k) {
            Arrays.sort(nums);
            int n = nums.length, y = 0, res = 0;
            for (int x = 0; x < n; x++) {
                if (x == 0 || nums[x] != nums[x - 1]) {
                    while (y < n && (nums[y] < nums[x] + k || y <= x)) {
                        y++;
                    }
                    if (y < n && nums[y] == nums[x] + k) {
                        res++;
                    }
                }
            }
            return res;
        }
    }
}
