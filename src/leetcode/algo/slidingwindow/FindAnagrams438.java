package leetcode.algo.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
public class FindAnagrams438 {
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
