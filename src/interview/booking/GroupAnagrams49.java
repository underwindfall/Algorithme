package interview.booking;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// https://leetcode.cn/problems/group-anagrams/
public class GroupAnagrams49 {
    // time O(n * 26)
    // space O(N)
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = hashCode(str);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        for (List<String> list : map.values()) {
            ans.add(list);
        }
        return ans;
    }

    // time O(26)
    String hashCode(String word) {
        int[] count = new int[26];
        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                sb.append((char) ('a' + i));
                sb.append(count[i]);
            }
        }
        return sb.toString();
    }
}
