package leetcode.algo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/minimum-window-substring/
public class MinWindow76 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        // need存放的不重复的字符出现的次数
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // left,right 表示滑动窗口的左右指针
        int left = 0, right = 0;
        // valid表示是否满足了t中的字符，不算重复的
        int valid = 0;
        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 判断取出的字符是否在需要的Map中
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            // 判断是否需要收缩（即已经找到了合适的覆盖串）
            while (valid == need.size()) {
                // 更新最小覆盖子串
                // 内部逻辑是如果当前的字符串长度right -left 小于len，那意味着当前的字符串较小，是目标的字符串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                //看下左边的字符串
                char c1 = s.charAt(left);
                // 移动左边的窗口
                left++;
                // 进行窗口内数据的一系列更新
                // 如果当前要移动的字符是包含在need中，我们需要进行讨论，如果该字符的次数刚好与我们需要的次数相等，则valid--，并同时更新window中这个值出现的次数
                if (need.containsKey(c1)) {
                    if (window.get(c1).equals(need.get(c1))) {
                        valid--;
                    }
                    window.put(c1, window.getOrDefault(c1, 0) - 1);
                }

            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
