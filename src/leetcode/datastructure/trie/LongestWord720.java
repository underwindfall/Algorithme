package leetcode.datastructure.trie;

public class LongestWord720 {
    TrieNode root = new TrieNode();

    public String longestWord(String[] words) {
        for (String w : words) {
            insert(w);
        }
        String ans = "";
        for (String w : words) {
            if (search(w)) {
                if (ans.length() < w.length()) {
                    ans = w;
                } else if (ans.length() == w.length() && w.compareTo(ans) < 0) {
                    ans = w;
                }
            }
        }
        return ans;
    }


    void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (p.children[c - 'a'] == null) {
                p.children[c - 'a'] = new TrieNode();
            }
            p = p.children[c - 'a'];
        }
        p.isEnd = true;
        p.word = word;
    }

    boolean search(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (p.children[c - 'a'] == null || !p.children[c - 'a'].isEnd) {
                return false;
            }
            p = p.children[c - 'a'];
        }
        return p.isEnd;
    }


    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
        String word;
    }
}
