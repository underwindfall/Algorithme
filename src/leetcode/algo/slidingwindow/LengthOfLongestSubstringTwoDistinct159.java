package leetcode.algo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters/
public class LengthOfLongestSubstringTwoDistinct159 {
    //time O(n)
    //space O(1)
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
        int left = 0, right = 0;
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        while (right < n) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            if (map.size() >= 3) {
                c = s.charAt(left);
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
                left++;
            }
            res = Math.max(res, right - left);
            right++;
        }
        return res;
    }
}
