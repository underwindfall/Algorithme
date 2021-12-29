package leetcode.datastructure.trie;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/add-bold-tag-in-string/
public class AddBoldTag616 {

    // time O(n)
    // space O(n)
    public String addBoldTag(String s, String[] words) {
        boolean[] bold = new boolean[s.length()];
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            for (String word : words) {
                if (s.substring(i).startsWith(word)) {
                    end = Math.max(end, i + word.length());
                }
            }
            bold[i] = end > i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!bold[i]) {
                stringBuilder.append(s.charAt(i));
                continue;
            }
            int j = i;
            while (j < s.length() && bold[j]) {
                j++;
            }
            stringBuilder.append("<br>" + s.substring(i, j) + "</br>");
            i = j - 1;
        }
        return stringBuilder.toString();
    }

    // time O(n)
    // space O(1)
    class TrieSolution {

        String prefix = "<b>";
        String suffix = "</b>";

        public String addBoldTag(String s, String[] dict) {
            if (s == null) {
                return "";
            }
            int end = -1;
            StringBuilder sb = new StringBuilder();
            Trie trie = new Trie();
            for (String word : dict)
                trie.insert(word);
            for (int i = 0; i < s.length(); i++) {
                if (end >= i) {
                    end = Math.max(end, trie.search(s, i));
                } else {
                    end = trie.search(s, i);
                    if (end > i) {
                        sb.append(prefix);
                    }
                }
                if (end == i)
                    sb.append(suffix);
                sb.append(s.charAt(i));
            }
            if (end == s.length())
                sb.append(suffix);
            return sb.toString();
        }

        public class Trie {
            TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            public void insert(String word) {
                if (word == null)
                    return;
                TrieNode node = root;
                for (char c : word.toCharArray()) {
                    if (!node.map.containsKey(c)) {
                        node.map.put(c, new TrieNode());
                    }
                    node = node.map.get(c);
                }
                node.end = true;
            }

            public int search(String s, int index) {
                TrieNode node = root;
                int res = -1;
                while (node != null && index < s.length()) {
                    node = node.map.getOrDefault(s.charAt(index++), null);
                    if (node != null && node.end == true) {
                        res = index;
                    }
                }
                return res;
            }
        }

        public class TrieNode {
            Map<Character, TrieNode> map;
            boolean end;

            public TrieNode() {
                map = new HashMap<>();
                end = false;
            }
        }

    }
}
