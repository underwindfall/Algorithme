package leetcode.algo.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode-cn.com/problems/word-squares/
public class WordSquares425 {
    /**
     * 目的是为了保存prefix 的word 从而快速找到 下一个word
     */
    // time
    // space
    class Backtracking {
        public List<List<String>> wordSquares(String[] words) {
            List<List<String>> res = new ArrayList<>();
            if (words.length == 0 || words[0].length() == 0)
                return res;
            Map<String, Set<String>> prefix = new HashMap<>();
            // create all prefix;
            for (String word : words) {
                for (int i = 1; i <= word.length(); i++) {
                    String str = word.substring(0, i);
                    prefix.putIfAbsent(str, new HashSet<>());
                    prefix.get(str).add(word);
                }
            }

            for (String word : words) {
                List<String> candidate = new ArrayList<>();
                candidate.add(word);
                dfs(candidate, 1, words[0].length(), prefix, res);
            }
            return res;
        }

        void dfs(List<String> candidate, int pos, int len, Map<String, Set<String>> prefix, List<List<String>> res) {
            if (pos == len) {
                res.add(new ArrayList<>(candidate));
                return;
            }
            // Get the next prefix;
            StringBuilder sb = new StringBuilder();
            for (String cand : candidate) {
                sb.append(cand.charAt(pos));
            }

            if (!prefix.containsKey(sb.toString())) {
                return;
            }

            for (String next : prefix.get(sb.toString())) {
                candidate.add(next);
                dfs(candidate, pos + 1, len, prefix, res);
                candidate.remove(candidate.size() - 1);
            }
        }
    }

    class TrieSolution {

        class TrieNode {
            List<String> startWith;
            TrieNode[] children;

            TrieNode() {
                startWith = new ArrayList<>();
                children = new TrieNode[26];
            }
        }

        class Trie {
            TrieNode root;

            Trie(String[] words) {
                root = new TrieNode();
                for (String w : words) {
                    TrieNode cur = root;
                    for (char ch : w.toCharArray()) {
                        int index = ch - 'a';
                        if (cur.children[index] == null) {
                            cur.children[index] = new TrieNode();
                        }
                        cur.children[index].startWith.add(w);
                        cur = cur.children[index];
                    }
                }
            }

            List<String> findByPrefix(String prefix) {
                List<String> ans = new ArrayList<>();
                TrieNode cur = root;
                for (char ch : prefix.toCharArray()) {
                    int idx = ch - 'a';
                    if (cur.children[idx] == null) {
                        return ans;
                    }
                    cur = cur.children[idx];
                }
                ans.addAll(cur.startWith);
                return ans;
            }
        }

        public List<List<String>> wordSquares(String[] words) {
            List<List<String>> ans = new ArrayList<>();
            if (words == null || words.length == 0)
                return ans;
            int len = words[0].length();
            Trie trie = new Trie(words);
            List<String> ansBuilder = new ArrayList<>();
            for (String w : words) {
                ansBuilder.add(w);
                search(len, trie, ans, ansBuilder);
                ansBuilder.remove(ansBuilder.size() - 1);
            }

            return ans;
        }

        private void search(int len, Trie tr, List<List<String>> ans, List<String> ansBuilder) {
            if (ansBuilder.size() == len) {
                ans.add(new ArrayList<>(ansBuilder));
                return;
            }
            int index = ansBuilder.size();
            StringBuilder prefixBuilder = new StringBuilder();
            for (String s : ansBuilder) {
                prefixBuilder.append(s.charAt(index));
            }
            List<String> startWith = tr.findByPrefix(prefixBuilder.toString());
            for (String sw : startWith) {
                ansBuilder.add(sw);
                search(len, tr, ans, ansBuilder);
                ansBuilder.remove(ansBuilder.size() - 1);
            }
        }
    }
}
