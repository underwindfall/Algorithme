package leetcode.datastructure.trie;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/implement-trie-ii-prefix-tree/
public class TriePrefixII1804 {
    // time O(n)
    // space O(n)
    class MapSolution {
        Map<String, Integer> map = new HashMap<>();

        class TrieNode {
            boolean isEnd = false;
            TrieNode[] children = new TrieNode[26];
            int prefixCount = 0;
        }

        TrieNode root;

        public MapSolution() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                int u = word.charAt(i) - 'a';
                if (p.children[u] == null) {
                    p.children[u] = new TrieNode();
                }
                p = p.children[u];
                p.prefixCount++;
            }
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        public int countWordsEqualTo(String word) {
            return map.getOrDefault(word, 0);
        }

        public int countWordsStartingWith(String prefix) {
            TrieNode p = root;
            for (int i = 0; i < prefix.length(); i++) {
                int u = prefix.charAt(i) - 'a';
                if (p.children[u] == null) {
                    return 0;
                }
                p = p.children[u];
            }
            return p.prefixCount;
        }

        public void erase(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                int u = word.charAt(i) - 'a';
                if (p.children[u].prefixCount > 0) {
                    p.children[u].prefixCount--;
                    p = p.children[u];
                } else {
                    TrieNode tmp = p.children[u];
                    p.children[u] = null;
                    p = tmp;
                }
            }

            if (map.containsKey(word)) {
                map.put(word, map.get(word) - 1);
            }
        }
    }

    // time O(n)
    // space O(n)
    class TrieNode {
        boolean isEnd;
        TrieNode[] tns = new TrieNode[26];
        int numberOfSharedPrefix;
        int numberOfInstanceForThisWord;

    }

    TrieNode root;

    public TriePrefixII1804() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int u = word.charAt(i) - 'a';
            if (p.tns[u] == null) {
                p.tns[u] = new TrieNode();
            }
            p = p.tns[u];
            p.numberOfSharedPrefix++;
        }
        p.isEnd = true;
        p.numberOfInstanceForThisWord++;
    }

    public int countWordsEqualTo(String word) {

        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int u = word.charAt(i) - 'a';
            if (p.tns[u] == null) {
                return 0;
            }
            p = p.tns[u];
        }

        return p.numberOfInstanceForThisWord;
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            int u = prefix.charAt(i) - 'a';
            if (p.tns[u] == null) {
                return 0;
            }
            p = p.tns[u];
        }
        return p.numberOfSharedPrefix;
    }

    public void erase(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int u = word.charAt(i) - 'a';
            if (p.tns[u].numberOfSharedPrefix > 0) {
                p.tns[u].numberOfSharedPrefix--;
                p = p.tns[u];
            } else {
                TrieNode temp = p.tns[u];
                p.tns[u] = null;
                p = temp;
            }
        }
        p.numberOfInstanceForThisWord--;
    }
}
