package leetcode.datastructure.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode-cn.com/problems/group-anagrams/
public class GroupAnagrams49 {

    // time O(n * logN* M)
    // space O(m)
    class Sort {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<String, List<String>>();
            for (String str : strs) {
                char[] array = str.toCharArray();
                Arrays.sort(array);
                String key = new String(array);
                List<String> list = map.getOrDefault(key, new ArrayList<String>());
                list.add(str);
                map.put(key, list);
            }
            return new ArrayList<List<String>>(map.values());
        }
    }

    class Count {

        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                String key = isSameGroup(str);
                if (!map.containsKey(key)) {
                    List<String> tmp = new ArrayList<>();
                    tmp.add(str);
                    map.put(key, tmp);
                } else {
                    map.get(key).add(str);
                }
            }
            List<List<String>> result = new ArrayList<>(map.values());
            return result;
        }

        String isSameGroup(String word) {
            int[] count = new int[26];
            for (char k : word.toCharArray()) {
                count[k - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (count[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(count[i]);
                }
            }
            String key = sb.toString();
            return key;
        }
    }
}
