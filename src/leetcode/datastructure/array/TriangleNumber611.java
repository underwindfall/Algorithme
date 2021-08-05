package leetcode.datastructure.array;

import java.util.Arrays;

//https://leetcode-cn.com/problems/valid-triangle-number/
public class TriangleNumber611 {
    // time O(N^3)
    // espace O(logN)
    class BruteForce {
        public int triangleNumber(int[] nums) {
            int n = nums.length;
            Arrays.sort(nums);
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    for (int k = j - 1; k >= 0; k--) {
                        if (nums[j] + nums[k] > nums[i])
                            ans++;
                    }
                }
            }
            return ans;
        }
    }

    // time O(n^2 * Logn)
    // espace O(logN)
    class Binarysearch {
        public int triangleNumber(int[] nums) {
            int n = nums.length;
            Arrays.sort(nums);
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    int l = 0, r = j - 1;
                    while (l < r) {
                        int mid = (r + l) >> 1;
                        if (nums[mid] + nums[j] > nums[i]) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }
                    if (l == r && nums[r] + nums[j] > nums[i]) {
                        ans += j - r;
                    }
                }
            }
            return ans;
        }
    }
}
