package leetcode.datastructure.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/search-suggestions-system/
public class SuggestedProducts1268 {
    // time O(sum L + S)O(∑L+S)，
    // space O(sum L)
    List<List<String>> ans = new ArrayList<>();
    TrieNode root;
    private static final int size = 3;

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        root = new TrieNode();
        for (String s : products) {
            insert(s);
        }
        startWith(searchWord);
        return ans;
    }

    void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new TrieNode();
            }
            node = node.next[c - 'a'];
            node.pq.offer(word);
            if (node.pq.size() > size) {
                node.pq.poll();
            }
        }
        node.isEnd = true;
    }

    void startWith(String word) {
        TrieNode node = root;
        boolean exist = true;
        for (char c : word.toCharArray()) {
            if (!exist || node.next[c - 'a'] == null) {
                exist = false;
                ans.add(new ArrayList<>());
                continue;
            }
            node = node.next[c - 'a'];
            List<String> tmp = new ArrayList<>();
            while (!node.pq.isEmpty()) {
                tmp.add(node.pq.poll());
            }
            Collections.reverse(tmp);
            ans.add(tmp);
        }
    }

    class TrieNode {
        public static final int num = 26;
        TrieNode[] next;
        boolean isEnd;
        PriorityQueue<String> pq;

        TrieNode() {
            next = new TrieNode[num];
            pq = new PriorityQueue<>((a, b) -> b.compareTo(a));
        }
    }
}
