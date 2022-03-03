package swordoffer;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
public class LCOF12 {
    // time O(n * m * 4^L)
    // space O(n * m * 4^L)
    class DFS {
        public boolean exist(char[][] board, String word) {
            int m = board.length, n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (dfs(board, i, j, 0, word)) {
                        return true;
                    }
                }
            }
            return false;
        }

        boolean dfs(char[][] board, int i, int j, int index, String word) {
            int m = board.length, n = board[0].length;
            if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == '#') {
                return false;
            }

            char c = board[i][j];
            if (c != word.charAt(index)) {
                return false;
            }
            if (index == word.length() - 1 && word.charAt(index) == board[i][j]) {
                return true;
            }
            board[i][j] = '#';
            boolean res = dfs(board, i + 1, j, index + 1, word)
                    || dfs(board, i - 1, j, index + 1, word)
                    || dfs(board, i, j + 1, index + 1, word)
                    || dfs(board, i, j - 1, index + 1, word);
            board[i][j] = c;
            return res;
        }
    }

    class Trie {
        TrieNode root = new TrieNode();

        public boolean exist(char[][] board, String word) {
            int m = board.length, n = board[0].length;
            insert(word);

            TrieNode node = root;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (dfs(board, i, j, node, word))
                        return true;
                }
            }
            return false;
        }

        boolean dfs(char[][] board, int i, int j, TrieNode curr, String word) {
            int m = board.length, n = board[0].length;
            if (curr.isEnd) {
                return true;
            }
            if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == '#') {
                return false;
            }
            char c = board[i][j];
            TrieNode child = curr.children.get(c);
            if (child == null)
                return false;
            board[i][j] = '#';
            boolean res = dfs(board, i + 1, j, child, word)
                    || dfs(board, i - 1, j, child, word)
                    || dfs(board, i, j + 1, child, word)
                    || dfs(board, i, j - 1, child, word);
            board[i][j] = c;
            return res;
        }

        void insert(String word) {
            TrieNode p = root;
            for (Character c : word.toCharArray()) {
                if (!p.children.containsKey(c)) {
                    p.children.put(c, new TrieNode());
                }
                p = p.children.get(c);
            }
            p.isEnd = true;
        }

        class TrieNode {
            Map<Character, TrieNode> children = new HashMap<>();
            boolean isEnd = false;
        }
    }
}
