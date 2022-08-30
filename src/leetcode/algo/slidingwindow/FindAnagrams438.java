package leetcode.algo.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
public class FindAnagrams438 {
    // 时间复杂度：O(m+(n−m)×Σ)，
    // 其中 n 为字符串 s 的长度，m 为字符串 p 的长度，Σ 为所有可能的字符数。
    // 我们需要 O(m) 来统计字符串
    // p 中每种字母的数量；需要 O(m) 来初始化滑动窗口；
    // 需要判断 n−m+1 个滑动窗口中每种字母的数量是否与字符串 p
    // 中每种字母的数量相同，每次判断需要 O(Σ) 。因为 s 和 p 仅包含小写字母，所以 Σ=26。

    // 空间复杂度：O(Σ)。用于存储字符串 p 和滑动窗口中每种字母的数量。

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        while (right < s.length()) {
            char cRight = s.charAt(right);
            right++;

            if (need.containsKey(cRight)) {
                window.put(cRight, window.getOrDefault(cRight, 0) + 1);
                if (need.get(cRight).equals(window.get(cRight))) {
                    valid++;
                }
            }

            while (right - left >= p.length()) {
                if (valid == need.size()) {
                    ans.add(left);
                }
                char cLeft = s.charAt(left);
                left++;

                if (need.containsKey(cLeft)) {
                    if (need.get(cLeft).equals(window.get(cLeft))) {
                        valid--;
                    }
                    window.put(cLeft, window.getOrDefault(cLeft, 0) - 1);
                }
            }
        }
        return ans;
    }
}
