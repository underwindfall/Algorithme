package leetcode.algo.dp.subsquence;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// https://leetcode-cn.com/problems/word-break/
public class WordBreak139 {

    //前缀树有点DFS的味道
    //如果wordDict含有当前的文字
    //如果如果wordDict的node是最后一个字符那就还一个字符比较
    class PrefixTree {
        class TrieNode {
            boolean isEnd = false;
            Character content = null;
            HashMap<Character, TrieNode> child = new HashMap<>();

            TrieNode(Character content) {
                this.content = content;
            }
        }

        public boolean wordBreak(String s, List<String> wordDict) {
            // 字典树根，S作为值，当然可以随便。
            TrieNode root = new TrieNode('S');
            for (String s1 : wordDict) {
                // 把单词加入字典
                addWord(s1.toCharArray(), root);
            }

            char[] arr = s.toCharArray();

            // FIFO 队列, 队列放入下标索引
            LinkedList<Integer> queue = new LinkedList<>();
            // 是否已经遍历过，防止重复遍历。
            boolean[] visit = new boolean[arr.length + 1];

            // 把第一个字母加入队列
            visit[0] = true;
            queue.add(0);

            while (!queue.isEmpty()) {
                int i = queue.pollFirst();
                TrieNode node = root;
                // 找到以该字母为起点的所有单词,对应下标加入队列中
                for (; i < arr.length; i++) {
                    node = node.child.get(arr[i]);
                    if (node == null) {
                        break;
                    }
                    // 该字母是单词的结束，同时这个位置没有被处理过。把i+1作为下次遍历的起点
                    if (node.isEnd && !visit[i + 1]) {
                        queue.add(i + 1);
                        visit[i + 1] = true;
                    }
                }
                if (i == arr.length && node.isEnd) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 字典树的建树
         * 
         * @param word
         * @param root
         */
        public void addWord(char[] word, TrieNode root) {
            TrieNode dic = root;
            for (int i = 0; i < word.length; i++) {
                TrieNode node = dic.child.get(word[i]);
                if (node == null) {
                    node = new TrieNode(word[i]);
                    dic.child.put(word[i], node);
                }
                dic = node;
            }
            dic.isEnd = true;
        }
    }

    // 可以看作wordDict 都是可选的单词选项
    // 然后每个字符逐个遍历
    class DP {
        public boolean wordBreak(String s, List<String> wordDict) {
            int N = s.length();
            boolean[] dp = new boolean[N + 1];
            dp[0] = true;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < i; j++) {
                    // j = 0 dp[0] = true 永远是true，如果匹配到wordDict就会也将下一次的点变换为true
                    // 但其实每次匹配都是leet ok -> leetcode -> ok
                    if (dp[j] && wordDict.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[N];
        }
    }

    @SuppressWarnings("unused")
    class BFS {
        public boolean wordBreak(String s, List<String> wordDict) {
            if (s == null || wordDict == null || wordDict.size() == 0) {
                return false;
            }
            // 已访问集合
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.add(s);
            visited.add(s);
            while (!queue.isEmpty()) {
                String curr = queue.poll();
                int currLen = curr.length();
                for (String word : wordDict) {
                    if (curr.indexOf(word) == 0) {
                        String str = curr.substring(word.length());
                        // 如果剩下的子串是空串，就可以返回结果了
                        if (str.equals(""))
                            return true;
                        if (visited.contains(str))
                            continue;
                        queue.add(str);
                        visited.add(str);
                    }
                }
            }
            return false;
        }
    }

    //time O(wordDict * s.length)
    //space O(wordDict * s.length)
    class DFSOpti {
        public boolean wordBreak(String s, List<String> wordDict) {
            Map<String,Boolean> map = new HashMap<>();
            for (String w : wordDict) {
                map.put(w, true);
            }
            int[] memo = new int[s.length()];
            Arrays.fill(memo, -1);
            return canBreak(0, s, map, memo);
        }

    boolean canBreak(int start, String s, Map<String,Boolean> map, int[] memo) {
            if (s.length() == start) {
                return true;
            }
            if (memo[start] != -1) {
                return memo[start] == 1 ? true : false;
            }
            for (int i = start ; i < s.length(); i++) {
                String prefix = s.substring(start, i + 1);
                if (map.containsKey(prefix) && canBreak(i + 1, s, map, memo)) {
                    memo[start] = 1; 
                    return true;
                }
            }
            memo[start] = 0;
            return false;
        }
    }

    class DFS {
        boolean[] mem;

        boolean backtrack(String s, int start, Set<String> set) {
            if (s.length() == 0)
                return true;
            if (mem[start])
                return false;
            for (int i = 0; i < s.length(); i++) {
                if (set.contains(s.substring(0, i + 1))) {
                    if (backtrack(s.substring(i + 1), start + i + 1, set))
                        return true;
                }
            }
            mem[start] = true;
            return false;
        }

        boolean wordBreak(String s, List<String> wordDict) {
            this.mem = new boolean[s.length()];
            Set<String> set = new HashSet<>(wordDict);
            return backtrack(s, 0, set);
        }
    }

    // 思路是如果 s length index中满足 s.substring(0, index) 可以在wordDict被找到
    // 那就只要看剩余的字符串是不是能被找到
    // DFS 这就是一种dfs
    class Recursive {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> set = new HashSet<>(wordDict);
            return judgeString(s, set);
        }

        boolean judgeString(String s, Set<String> option) {
            if (s.length() == 0) {
                return true;
            }
            for (int i = 0; i < s.length(); i++) {
                if (option.contains(s.substring(0, i + 1))) {
                    return judgeString(s.substring(i + 1), option);
                }
            }
            return false;
        }

        class Optimization {
            boolean[] mem;

            boolean backtrack(String s, int start, Set<String> set) {
                if (s.length() == 0)
                    return true;
                if (mem[start])
                    return false;
                for (int i = 0; i < s.length(); i++) {
                    if (set.contains(s.substring(0, i + 1))) {
                        if (backtrack(s.substring(i + 1), start + i + 1, set))
                            return true;
                    }
                }
                mem[start] = true;
                return false;
            }

            boolean wordBreak(String s, List<String> wordDict) {
                this.mem = new boolean[s.length()];
                Set<String> set = new HashSet<>(wordDict);
                return backtrack(s, 0, set);
            }

        }
    }

}