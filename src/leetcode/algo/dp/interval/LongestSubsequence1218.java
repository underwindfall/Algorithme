package leetcode.algo.dp.interval;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference/
public class LongestSubsequence1218 {
    // time O(n)
    // space O(n)
    public int longestSubsequence(int[] arr, int difference) {
        // dp(i) last element i
        int ans = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i - difference, 0) + 1);
            ans = Math.max(ans, map.get(i));
        }
        return ans;
    }
}
