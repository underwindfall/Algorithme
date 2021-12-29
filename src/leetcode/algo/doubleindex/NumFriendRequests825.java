package leetcode.algo.doubleindex;

import java.util.Arrays;

// https://leetcode-cn.com/problems/friends-of-appropriate-ages/
public class NumFriendRequests825 {
    // time O(n * logn)
    // space O(1)
    public int numFriendRequests(int[] ages) {
        int n = ages.length;
        Arrays.sort(ages);
        int left = 0, right = 0, ans = 0;
        for (int age : ages) {
            if (age < 15) {
                continue;
            }
            while (ages[left] <= 0.5 * age + 7) {
                ++left;
            }
            while (right + 1 < n && ages[right + 1] <= age) {
                ++right;
            }
            ans += right - left;
        }
        return ans;

    }
}
