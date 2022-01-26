package interview.datadog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode-cn.com/problems/word-search-ii/
public class WordSearchII212 {

    class TrieTree {
        class Trie {
            public Trie[] children;
            public String word;

            public Trie() {
                children = new Trie[26];
                word = null;
            }

            public void insert(String word) {
                Trie cur = this;
                for (int i = 0; i < word.length(); i++) {
                    int idx = word.charAt(i) - 'a';
                    if (cur.children[idx] == null)
                        cur.children[idx] = new Trie();
                    cur = cur.children[idx];
                }
                cur.word = word;
            }
        }

        public List<String> findWords(char[][] board, String[] words) {
            Trie root = new Trie();
            for (String word : words)
                root.insert(word);

            int m = board.length, n = board[0].length;
            List<String> res = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dfs(board, i, j, root, res);
                }
            }
            return res;
        }

        private void dfs(char[][] board, int i, int j, Trie curr, List<String> res) {
            int m = board.length, n = board[0].length;
            if (curr.word != null) {
                res.add(curr.word);
                curr.word = null;
            }
            if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == '#') {
                return;
            }
            char ch = board[i][j];
            Trie child = curr.children[ch - 'a'];
            if (child == null) {
                return;
            }
            board[i][j] = '#';
            dfs(board, i + 1, j, child, res);
            dfs(board, i - 1, j, child, res);
            dfs(board, i, j + 1, child, res);
            dfs(board, i, j - 1, child, res);
            board[i][j] = ch;
        }
    }

    // 思路是 每个节点都是可能是word开头的一个字符，
    // 那就是可以进行深度搜索，递归调用一个每个节点开头的单词tree，如果出现高度小于word的节点，那就意味着不存在，
    // 否则若每个单词相应的节点存在就匹配。
    // 坏处是数组里面的每个字符都要进行遍历，所以最好的方式可以记住相同prefix的东西
    // time O(sum(m*n*4^L))
    // espace O(1)
    class Dfs {
        public List<String> findWords(char[][] board, String[] words) {
            int m = board.length;
            int n = board[0].length;
            Set<String> res = new HashSet<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (String word : words) {
                        if (dfs(board, i, j, word, 0))
                            res.add(word);
                    }
                }
            }
            return new ArrayList<>(res);
        }

        private boolean dfs(char[][] board, int i, int j, String word, int k) {
            int m = board.length;
            int n = board[0].length;
            if (k == word.length()) {
                return true;
            }
            if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == '#') {
                return false;
            }
            char ch = board[i][j];
            if (ch != word.charAt(k)) {
                return false;
            }
            board[i][j] = '#';
            boolean res =
                    // 行+1
                    dfs(board, i + 1, j, word, k + 1)
                            // 行-1
                            || dfs(board, i - 1, j, word, k + 1)
                            // 列+1
                            || dfs(board, i, j + 1, word, k + 1)
                            // 列-1
                            || dfs(board, i, j - 1, word, k + 1);
            // 恢复原油数组
            board[i][j] = ch;
            return res;
        }
    }
}
