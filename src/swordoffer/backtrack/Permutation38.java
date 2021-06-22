package swordoffer.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
public class Permutation38 {

    // time O(N*N!)
    // espace O(N)
    // 去重方式
    // 如果同一层 cs(i) == cs(i - 1) && !used[i - 1]
    public String[] permutation(String s) {
        boolean[] used = new boolean[s.length()];
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        List<String> list = new ArrayList<>();
        dfs(cs, s.length(), 0, "", used, list);
        int index = 0;
        String[] result = new String[list.size()];
        for (String str : list) {
            result[index++] = str;
        }
        return result;
    }

    void dfs(char[] cs, int len, int depth, String curr, boolean[] used, List<String> list) {
        if (len == depth) {
            list.add(curr);
            return;
        }
        for (int i = 0; i < len; i++) {
            // remove duplicates
            if (i > 0 && !used[i - 1] && cs[i] == cs[i - 1]) {
                continue;
            }
            if (!used[i]) {
                used[i] = true;
                dfs(cs, len, depth + 1, curr + cs[i], used, list);
                used[i] = false;
            }
        }
    }

    // time O(N*N!)
    // espace O(N)
    // 这题就是排列 set 去重
    class SolutionSet {
        public String[] permutation(String s) {

            Set<String> set = new HashSet<>();

            boolean[] used = new boolean[s.length()];

            dfs(s, s.length(), 0, "", used, set);

            String[] result = new String[set.size()];
            set.toArray(result);
            return result;
        }

        void dfs(String s, int len, int depth, String curr, boolean[] used, Set<String> res) {
            if (depth == len) {
                res.add(curr);
                return;
            }

            for (int i = 0; i < len; i++) {
                if (!used[i]) {
                    curr += s.charAt(i);
                    used[i] = true;
                    dfs(s, len, depth + 1, curr, used, res);
                    used[i] = false;
                    curr = curr.substring(0, curr.length() - 1);
                }
            }
        }
    }
}