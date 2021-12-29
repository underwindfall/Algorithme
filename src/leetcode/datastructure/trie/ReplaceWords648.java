package leetcode.datastructure.trie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode-cn.com/problems/replace-words/
public class ReplaceWords648 {

    // time O(n*m)
    // space O(n)
    class PrefixSet {
        public String replaceWords(List<String> dictionary, String sentence) {
            Set<String> dictorySet = new HashSet<>(dictionary);
            StringBuilder sb = new StringBuilder();
            for (String word : sentence.split("\\s+")) {
                String prefix = "";
                for (int i = 1; i <= word.length(); i++) {
                    prefix = word.substring(0, i);
                    if (dictorySet.contains(prefix)) {
                        break;
                    }
                    if (sb.length() > 0)
                        sb.append(" ");
                    sb.append(prefix);
                }
            }
            return sb.toString();
        }
    }

    //time O(n)
    //space O(n)
    class TrieSolution {
        TrieNode root = new TrieNode();

        public String replaceWords(List<String> dictionary, String sentence) {
            for (String d : dictionary) {
                insert(d);
            }
            String[] s = sentence.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String word : s) {
                String w = startWith(word);
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(w);
            }
            return sb.toString();
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
            p.word = word;
        }

        String startWith(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (p.children[c - 'a'] == null || p.word != null) {
                    break;
                }
                p = p.children[c - 'a'];
            }
            return p.word == null ? word : p.word;
        }

        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            String word;
        }
    }
}
