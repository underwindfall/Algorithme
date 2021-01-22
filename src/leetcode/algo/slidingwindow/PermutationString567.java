package leetcode.algo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/permutation-in-string
public class PermutationString567 {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char s : s1.toCharArray()) {
            need.put(s, need.getOrDefault(s, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;

        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }

            while (right - left >= s1.length()) {
                if (valid == need.size()) {
                    return true;
                }
                char cLeft = s2.charAt(left);
                left++;
                if (need.containsKey(cLeft)) {
                    if (window.get(cLeft).equals(need.get(cLeft))) {
                        valid--;
                    }
                    window.put(cLeft, window.getOrDefault(cLeft, 0) - 1);
                }
            }
        }

        return false;
    }
}
