package leetcode.algo.slidingwindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters/
public class LengthOfLongestSubstringTwoDistinct159 {
    // time O(n)
    // space O(1)
    class SlidingwindowOptimation {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int left = 0, right = 0;
            int n = s.length();
            Map<Character, Integer> map = new HashMap<>();
            int res = 0;
            while (right < n) {
                if (map.size() <= 2) {
                    char c = s.charAt(right);
                    map.put(c, right);
                    right++;
                }
                if (map.size() > 2) {
                    int leftMost = n;
                    for (int i : map.values()) {
                        leftMost = Math.min(i, leftMost);
                    }
                    map.remove(s.charAt(leftMost));
                    left = leftMost + 1;
                }
                res = Math.max(res, right - left);
            }
            return res;
        }
    }

    // sliding window
    // time O(n)
    // space O(1)
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if (n < 3)
            return n;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        Map<Character, Integer> hashmap = new HashMap<Character, Integer>();

        int max_len = 2;

        while (right < n) {
            // slidewindow contains less than 3 characters
            if (hashmap.size() < 3)
                hashmap.put(s.charAt(right), right++);

            // slidewindow contains 3 characters
            if (hashmap.size() == 3) {
                // delete the leftmost character
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(del_idx));
                // move left pointer of the slidewindow
                left = del_idx + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }
}
