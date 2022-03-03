package swordoffer;

import java.util.HashSet;
import java.util.Set;

//https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
public class LCOF38 {
    // time O(n^2)
    // space O(n)
    public String[] permutation(String s) {
        boolean[] used = new boolean[s.length()];
        Set<String> ans = new HashSet<>();
        dfs(s, new StringBuilder(), used, ans);
        return ans.toArray(new String[ans.size()]);
    }

    void dfs(String s, StringBuilder curr, boolean[] used, Set<String> res) {
        if (curr.length() == s.length()) {
            res.add(curr.toString());
            return;
        }
        if (curr.length() > s.length()) {
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!used[i]) {
                used[i] = true;
                curr.append(s.charAt(i));
                dfs(s, curr, used, res);
                curr.deleteCharAt(curr.length() - 1);
                used[i] = false;
            }
        }
    }
}
