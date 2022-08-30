package leetcode.algo.dp.subsquence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode-cn.com/problems/word-break-ii/
public class WordBreakII140 {

    // time O(wordDict * s.length)
    // space O(wordDict * s.length)
    class BacktrackMemo {
        public List<String> wordBreak(String s, List<String> wordDict) {
            Map<Integer, List<List<String>>> map = new HashMap<Integer, List<List<String>>>();
            List<List<String>> wordBreaks = backtrack(s, s.length(), new HashSet<String>(wordDict), 0, map);
            List<String> ans = new LinkedList<String>();
            for (List<String> wordBreak : wordBreaks) {
                ans.add(String.join(" ", wordBreak));
            }
            return ans;
        }

        List<List<String>> backtrack(
                String s,
                int length,
                Set<String> wordSet,
                int index,
                Map<Integer, List<List<String>>> map) {
            if (!map.containsKey(index)) {
                List<List<String>> levels = new LinkedList<List<String>>();
                if (index == length) {
                    levels.add(new LinkedList<String>());
                }
                for (int i = index + 1; i <= length; i++) {
                    String word = s.substring(index, i);
                    if (wordSet.contains(word)) {
                        List<List<String>> nextWordBreaks = backtrack(s, length, wordSet, i, map);
                        for (List<String> nextWordBreak : nextWordBreaks) {
                            LinkedList<String> wordBreak = new LinkedList<String>(nextWordBreak);
                            wordBreak.offerFirst(word);
                            levels.add(wordBreak);
                        }
                    }
                }
                map.put(index, levels);
            }
            return map.get(index);
        }
    }

    // time O(wordDict * s.length)
    // space O(wordDict * s.length)
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordDict);
        dfs(s, 0, new LinkedList<>(), dict, ans);
        return ans;
    }

    void dfs(String source, int startIndex, LinkedList<String> path, Set<String> dict, List<String> ans) {
        if (startIndex == source.length()) {
            ans.add(String.join(" ", path));
            return;
        }
        for (int i = startIndex; i < source.length(); i++) {
            String word = source.substring(startIndex, i + 1);
            if (!dict.contains(word)) {
                continue;
            }
            path.addLast(word);
            dfs(source, i + 1, path, dict, ans);
            path.removeLast();
        }
    }

}