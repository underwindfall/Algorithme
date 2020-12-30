package leetcode.datastructure.hash;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
public class LengthOfLongestSubstring3 {
    //思路 滑动窗口算法
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] c = s.toCharArray();
        int max = 0;
        for (int start = 0, end = 0; end < c.length; end++) {
            if (map.containsKey(c[end])) {
                start = Math.max(map.get(c[end]) + 1, start);
            }
            max = Math.max(max, end - start + 1);
            map.put(c[end], end);
        }
        return max;
    }
}
