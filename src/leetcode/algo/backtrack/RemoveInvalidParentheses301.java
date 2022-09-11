package leetcode.algo.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/remove-invalid-parentheses/
// time O(n * 2 ^n)
// space O(n^2)
public class RemoveInvalidParentheses301 {
    // count maxString len
    int maxLen = 0;

    public List<String> removeInvalidParentheses(String s) {
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(')
                l++;
            else if (c == ')')
                r++;
        }
        int maxPair = Math.min(l, r);
        Set<String> parentheses = new HashSet<>();

        dfs(s, "", 0, 0, 0, maxPair, parentheses);

        return new ArrayList<>(parentheses);
    }

    void dfs(String s, String path, int index, int open, int close, int maxPair, Set<String> parentheses) {
        if (open > maxPair || close > maxPair || close > open) {
            return;
        }

        if (index == s.length()) {
            if (open == close && path.length() >= maxLen) {
                maxLen = path.length();
                parentheses.add(path);
            }
            return;
        }

        char c = s.charAt(index);
        if (c == '(') {
            dfs(s, path + c, index + 1, open + 1, close, maxPair, parentheses); // add (
            dfs(s, path, index + 1, open, close, maxPair, parentheses); // remove (
        } else if (c == ')') {
            dfs(s, path + c, index + 1, open, close + 1, maxPair, parentheses); // add )
            dfs(s, path, index + 1, open, close, maxPair, parentheses); // remove )
        } else {
            dfs(s, path + c, index + 1, open, close, maxPair, parentheses);
        }
    }
}
