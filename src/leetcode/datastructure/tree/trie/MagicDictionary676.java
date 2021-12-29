package leetcode.datastructure.tree.trie;

// https://leetcode-cn.com/problems/implement-magic-dictionary/
// time O()
// space O()
public class MagicDictionary676 {
    TrieNode root;

    MagicDictionary676() {
        root = new TrieNode();
    }

    public void buildDict(String[] dictionary) {
        for (String d : dictionary) {
            TrieNode p = root;
            for (int i = 0; i < d.length(); i++) {
                char c = d.charAt(i);
                int u = c - 'a';
                if (p.children[u] == null) {
                    p.children[u] = new TrieNode();
                }
                p = p.children[u];
            }
            p.isEnd = true;
        }
    }

    public boolean search(String searchWord) {
        return search(searchWord, 0, root, 1);
    }

    boolean search(String word, int index, TrieNode p, int count) {
        if (p == null) {
            return false;
        }
        if (index == word.length()) {
            return p.isEnd && count == 0;
        }

        int u = word.charAt(index) - 'a';

        if (p.children[u] != null && search(word, index + 1, p.children[u], count)) {
            return true;
        }

        if (count > 0) {
            for (int i = 0; i < 26; i++) {
                if (i == u || p.children[i] == null) {
                    continue;
                }
                if (search(word, index + 1, p.children[i], count - 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    class TrieNode {
        boolean isEnd;
        TrieNode[] children = new TrieNode[26];
    }
}
