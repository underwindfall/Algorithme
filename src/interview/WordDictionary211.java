package interview;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/
public class WordDictionary211 {
    //time O(1)
    //space O(word.length * character26)
    class WordDictionary {
        class TrieNode {
            TrieNode[] children;
            boolean flag;

            public TrieNode() {
                children = new TrieNode[26];
                flag = false;
                for (int i = 0; i < 26; i++) {
                    children[i] = null;
                }
            }

        }

        TrieNode root;

        /** Initialize your data structure here. */
        public WordDictionary() {
            root = new TrieNode();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            char[] array = word.toCharArray();
            TrieNode cur = root;
            for (int i = 0; i < array.length; i++) {
                // 当前孩子是否存在
                if (cur.children[array[i] - 'a'] == null) {
                    cur.children[array[i] - 'a'] = new TrieNode();
                }
                cur = cur.children[array[i] - 'a'];
            }
            // 当前节点代表结束
            cur.flag = true;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot
         * character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return searchHelp(word, root);
        }

        private boolean searchHelp(String word, TrieNode root) {
            char[] array = word.toCharArray();
            TrieNode cur = root;
            for (int i = 0; i < array.length; i++) {
                // 对于 . , 递归的判断所有不为空的孩子
                if (array[i] == '.') {
                    for (int j = 0; j < 26; j++) {
                        if (cur.children[j] != null) {
                            if (searchHelp(word.substring(i + 1), cur.children[j])) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
                // 不含有当前节点
                if (cur.children[array[i] - 'a'] == null) {
                    return false;
                }
                cur = cur.children[array[i] - 'a'];
            }
            // 当前节点是否为是某个单词的结束
            return cur.flag;
        }
    }

    class HashMapSolution {
        class WordDictionary {
            Set<String> set;

            /** Initialize your data structure here. */
            public WordDictionary() {
                set = new HashSet<>();
            }

            /** Adds a word into the data structure. */
            public void addWord(String word) {
                set.add(word);
            }

            /**
             * Returns if the word is in the data structure. A word could contain the dot
             * character '.' to represent any one letter.
             */
            public boolean search(String word) {
                if (set.contains(word)) {
                    return true;
                }
                for (String s : set) {
                    if (equal(s, word)) {
                        return true;
                    }
                }
                return false;
            }

            private boolean equal(String s, String word) {
                char[] c1 = s.toCharArray();
                char[] c2 = word.toCharArray();
                int n1 = s.length();
                int n2 = word.length();
                if (n1 != n2) {
                    return false;
                }
                for (int i = 0; i < n1; i++) {
                    // . 代表任意字符，跳过
                    if (c1[i] != c2[i] && c2[i] != '.') {
                        return false;
                    }
                }
                return true;
            }
        }
    }

}
