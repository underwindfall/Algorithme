package leetcode.datastructure.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem/
//time O(NM)
public class RemoveSubfolders1233 {
    TrieNode root;

    public List<String> removeSubfolders(String[] folder) {
        root = new TrieNode();
        List<String> ans = new ArrayList<>();
        Arrays.sort(folder);//time O(N LogNz)
        for (String word : folder) {
            if (startsWith(word))
                continue;
            insert(word);
            ans.add(word);
        }
        return ans;
    }

    void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (word.charAt(i) == '/')
                index = 26;
            if (p.child[index] == null) {
                p.child[index] = new TrieNode();
            }
            p = p.child[index];
        }
        p.isEnd = true;
    }

    boolean startsWith(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (word.charAt(i) == '/')
                index = 26;
            if (p.child[index] == null) {
                return false;
            }
            p = p.child[index];
            if (p.isEnd == true && word.charAt(i + 1) == '/') {
                return true;
            }
        }
        return false;
    }

    class TrieNode {
        TrieNode[] child = new TrieNode[27];
        boolean isEnd = false;
    }
}
