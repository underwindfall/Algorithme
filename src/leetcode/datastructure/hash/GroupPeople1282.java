package leetcode.datastructure.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/
public class GroupPeople1282 {
    // time O(n ^ 2)
    // space O(n^2)
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> group = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            if (!group.containsKey(size)) {
                group.put(size, new ArrayList<>());
            }
            group.get(size).add(i);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : group.entrySet()) {
            int size = entry.getKey();
            List<Integer> people = entry.getValue();
            int times = people.size() / size;

            for (int j = 0; j < times; j++) {
                List<Integer> res = new ArrayList<>();
                for (int i = 0 + size * j; i < size * (j + 1); i++) {
                    res.add(people.get(i));
                }
                ans.add(res);
            }
        }
        return ans;
    }
}
