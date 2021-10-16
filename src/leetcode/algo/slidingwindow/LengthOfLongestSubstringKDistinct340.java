package leetcode.algo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters/
public class LengthOfLongestSubstringKDistinct340 {
    //sliding window
    // time O(n* k)
    // space O(n)
    public  int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int valid = 0;
        int maxLen = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (window.getOrDefault(c, 0) == 0) {
                valid++;
            }
            window.put(c, window.getOrDefault(c, 0) + 1);

            while (valid > k) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
                if (window.get(d) == 0) {
                    valid--;
                }
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }
}
