package leetcode.datastructure.tree.trie;

// https://leetcode-cn.com/problems/implement-trie-prefix-tree/
public class TriePrefixTree208 {

    class Solution {
        class Trie {

            private class TrieNode { // 每个节点最多有26个不同的小写字母
                private boolean isEnd;
                private TrieNode[] next;

                public TrieNode() {
                    isEnd = false;
                    next = new TrieNode[26];
                }

            }

            private TrieNode root;

            /** Initialize your data structure here. */
            public Trie() {
                root = new TrieNode();
            }

            /** Inserts a word into the trie. */
            public void insert(String word) {
                TrieNode cur = root;
                for (int i = 0, len = word.length(), ch; i < len; i++) {
                    ch = word.charAt(i) - 'a';
                    if (cur.next[ch] == null)
                        cur.next[ch] = new TrieNode();
                    cur = cur.next[ch];
                }
                cur.isEnd = true; // 加上一个标记，表示为一个单词
            }

            /** Returns if the word is in the trie. */
            public boolean search(String word) {
                TrieNode cur = root;
                for (int i = 0, len = word.length(), ch; i < len; i++) {
                    ch = word.charAt(i) - 'a';
                    if (cur.next[ch] == null)
                        return false;
                    cur = cur.next[ch];
                }
                return cur.isEnd;
            }

            /**
             * Returns if there is any word in the trie that starts with the given prefix.
             */
            public boolean startsWith(String prefix) {
                TrieNode cur = root;
                for (int i = 0, len = prefix.length(), ch; i < len; i++) {
                    ch = prefix.charAt(i) - 'a';
                    if (cur.next[ch] == null)
                        return false; // 若还没遍历完给定的前缀子串，则直接返回false
                    cur = cur.next[ch];
                }
                return true; // 直接返回true
            }
        }
    }

    class Trie {
        class TrieNode {

            // R links to node children
            private TrieNode[] links;

            private final int R = 26;

            private boolean isEnd;

            public TrieNode() {
                links = new TrieNode[R];
            }

            public boolean containsKey(char ch) {
                return links[ch - 'a'] != null;
            }

            public TrieNode get(char ch) {
                return links[ch - 'a'];
            }

            public void put(char ch, TrieNode node) {
                links[ch - 'a'] = node;
            }

            public void setEnd() {
                isEnd = true;
            }

            public boolean isEnd() {
                return isEnd;
            }
        }

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // time O(N) N word length
        // espace O(N) N word length
        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                if (!node.containsKey(currentChar)) {
                    node.put(currentChar, new TrieNode());
                }
                node = node.get(currentChar);
            }
            node.setEnd();
        }

        // time O(N) N word length
        // espace O(1)
        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }

        // time O(N) N word length
        // espace O(1)
        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }

        private TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                if (node.containsKey(currentChar)) {
                    node = node.get(currentChar);
                } else {
                    return null;
                }
            }
            return node;
        }
    }
}
