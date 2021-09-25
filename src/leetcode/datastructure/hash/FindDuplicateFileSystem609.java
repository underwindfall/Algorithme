package leetcode.datastructure.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/find-duplicate-file-in-system/
public class FindDuplicateFileSystem609 {
    // time O(n)
    // space O(n)
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] values = path.split(" ");
            for (int i = 1; i < values.length; i++) {
                String[] name_content = values[i].split("\\(");
                name_content[1] = name_content[1].replace(")", "");
                List<String> list = map.getOrDefault(name_content[1], new ArrayList<>());
                list.add(values[0] + "/" + name_content[0]);
                map.put(name_content[1], list);
            }
        }
        List<List<String>> ans = new ArrayList<>();

        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                ans.add(map.get(key));
            }
        }
        return ans;
    }
}
