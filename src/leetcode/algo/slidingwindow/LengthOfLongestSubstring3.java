package leetcode.algo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
public class LengthOfLongestSubstring3 {
    // time O(n)
    // space O(n)
    // 题目可以解读为，s中存在一个字符串t，t是s排列的最小串
    // 思路是右边扩张，直至遇到重复的数字大于1，出现重复，左边收缩
    class Slidingwindow {
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> window = new HashMap<>();
            int right = 0, left = 0;
            int res = 0; // 记录结果
            while (right < s.length()) {
                char cRight = s.charAt(right);
                right++;
                window.put(cRight, window.getOrDefault(cRight, 0) + 1);

                while (window.get(cRight) > 1) {
                    char cLeft = s.charAt(left);
                    left++;
                    window.put(cLeft, window.getOrDefault(cLeft, 0) - 1);
                }
                // 在这里更新答案
                res = Math.max(res, right - left);
            }
            return res;
        }
    }

    // 思路是当出现重复的字符后，把start的位置提前至end，然后比较max
    class Hash {
        public int lengthOfLongestSubstring(String s) {
            char[] charArray = s.toCharArray();
            if (s == null || s.length() == 0) {
                return 0;
            }
            Map<Character, Integer> map = new HashMap<>();
            int max = 0;
            for (int start = 0, end = 0; end < charArray.length; end++) {
                if (map.containsKey(charArray[end])) {
                    start = Math.max(map.get(charArray[end]) + 1, start);
                }
                max = Math.max(max, end - start + 1);
                map.put(charArray[end], end);
            }
            return max;
        }
    }
}
