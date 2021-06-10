package leetcode.datastructure.backtrack;

import java.util.HashSet;
import java.util.Set;

//https://leetcode-cn.com/problems/split-a-string-into-the-max-number-of-unique-substrings/
public class MaxUniqueSplit1593 {
    // time O(2^N *N)
    // espace O(N)
    int count = 0;

    public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        dfs(s, 0, set, 0);
        return count;
    }

    void dfs(String s, int startIndex, Set<String> set, int splitCount) {
        if (startIndex >= s.length()) {
            count = Math.max(count, splitCount);
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            String cut = s.substring(startIndex, i + 1);
            if (!set.contains(cut)) {
                set.add(cut);
                dfs(s, i + 1, set, splitCount + 1);
                set.remove(cut);
            }
        }
    }
}
