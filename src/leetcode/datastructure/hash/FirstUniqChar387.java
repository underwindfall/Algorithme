package leetcode.datastructure.hash;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/first-unique-character-in-a-string/
public class FirstUniqChar387 {
    class Hash {
        public int firstUniqChar(String s) {
            Map<Character, Integer> map = new HashMap<>();
            for (char c : s.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            for (char c : s.toCharArray()) {
                if (map.get(c) == 1) {
                    return map.get(c);
                }
            }
            return -1;
        }
    }

    class Array {
        public int firstUniqChar(String s) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            char[] chars = s.toCharArray();
            for (int i = 0; i < s.length(); i++) {
                if (count[chars[i] - 'a'] == 1)
                    return i;
            }
            return -1;
        }
    }
}
