package leetcode.datastructure.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode-cn.com/problems/group-shifted-strings/
public class GroupStrings249 {
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();

        for (String e : strings) {
            String hashed = hash(e);
            if (!map.containsKey(hashed)) {
                map.put(hashed, new ArrayList<>());
            }
            List<String> list = map.get(hashed);
            list.add(e);
        }
        List<List<String>> res = new ArrayList<>();
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }

    private String hash(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            builder.append("#");
            builder.append((str.charAt(i) - str.charAt(i) + 26) % 26);
        }
        return builder.toString();
    }
}
