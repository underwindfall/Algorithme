package leetcode.datastructure.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode-cn.com/problems/unique-word-abbreviation/
public class ValidWordAbbr288 {
    // time O(N)
    // espace O(N)
    private final Map<String, Set<String>> abbrDict = new HashMap<>();

    ValidWordAbbr288(String[] dictionary) {
        for (String string : dictionary) {
            String abbr = toAbbr(string);
            Set<String> words = abbrDict.containsKey(abbr) ? abbrDict.get(abbr) : new HashSet<>();
            words.add(string);
            abbrDict.put(abbr, words);
        }
    }

    public boolean isUnique(String word) {
        String abbr = toAbbr(word);
        Set<String> words = abbrDict.get(abbr);
        return words == null || (words.size() == 1 && words.contains(word));
    }

    String toAbbr(String s) {
        int n = s.length();
        if (n <= 2) {
            return s;
        }
        return s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
    }
}
