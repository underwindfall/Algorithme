package leetcode.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode-cn.com/problems/repeated-dna-sequences/
public class FindRepeatedDNA187 {
    // time O(n*10)
    // space O(n)
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i + 10 <= n; i++) {
            String cur = s.substring(i, i + 10);
            int cnt = map.getOrDefault(cur, 0);
            if (cnt == 1)
                ans.add(cur);
            map.put(cur, cnt + 1);
        }
        return ans;
    }
}
