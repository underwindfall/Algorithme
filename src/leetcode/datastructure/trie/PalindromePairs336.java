package leetcode.datastructure.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.cn/problems/palindrome-pairs/
public class PalindromePairs336 {
    // time O(n * k ^ 2) n = list.length, k = word.length()

    TriNode root = new TriNode();

    private void insert(String word, int index) {
        TriNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i) - 'a';
            if (curr.childNode[pos] == null) {
                curr.childNode[pos] = new TriNode();
            }
            curr = curr.childNode[pos];
        }
        curr.index = index;
    }

    private Integer search(String word, int start, int end) {
        TriNode curr = root;
        for (int i = end; i >= start; i--) {
            int pos = word.charAt(i) - 'a';
            if (curr.childNode[pos] == null) {
                return null;
            }
            curr = curr.childNode[pos];
        }
        return curr.index;
    }

    private boolean isPalindrome(String s, int start, int end) {
        int i = start, j = end;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = word.length();
            for (int j = 0; j <= len; j++) {
                if (isPalindrome(word, j, len - 1)) {
                    Integer index = search(word, 0, j - 1);
                    if (index != null && index != i) {
                        res.add(Arrays.asList(i, index));
                    }
                }
                if (j != 0 && isPalindrome(word, 0, j - 1)) {
                    Integer index = search(word, j, len - 1);
                    if (index != null && index != i) {
                        res.add(Arrays.asList(index, i));
                    }
                }
            }
        }
        return res;
    }

    class TriNode {
        TriNode[] childNode = new TriNode[26];
        Integer index;
    }
}
