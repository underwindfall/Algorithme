package leetcode.algo.bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode-cn.com/problems/word-ladder/
public class LadderLength127 {
    // time O(n *m ^26)
    // space O(n + m)
    class BFS {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
            Set<String> wordSet = new HashSet<>(wordList);
            if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
                return 0;
            }
            wordSet.remove(beginWord);

            // 第 2 步：图的广度优先遍历，必须使用队列和表示是否访问过的 visited 哈希表
            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);
            Set<String> visited = new HashSet<>();
            visited.add(beginWord);

            // 第 3 步：开始广度优先遍历，包含起点，因此初始化的时候步数为 1
            int step = 1;
            while (!queue.isEmpty()) {
                int currentSize = queue.size();
                for (int i = 0; i < currentSize; i++) {
                    // 依次遍历当前队列中的单词
                    String currentWord = queue.poll();
                    // 如果 currentWord 能够修改 1 个字符与 endWord 相同，则返回 step + 1
                    if (changeWordEveryOneLetter(currentWord, endWord, queue, visited, wordSet)) {
                        return step + 1;
                    }
                }
                step++;
            }
            return 0;
        }

        /**
         * 尝试对 currentWord 修改每一个字符，看看是不是能与 endWord 匹配
         */
        private boolean changeWordEveryOneLetter(String currentWord, String endWord, Queue<String> queue,
                Set<String> visited, Set<String> wordSet) {
            char[] charArray = currentWord.toCharArray();
            for (int i = 0; i < endWord.length(); i++) {
                // 先保存，然后恢复
                char originChar = charArray[i];
                for (char k = 'a'; k <= 'z'; k++) {
                    if (k == originChar) {
                        continue;
                    }
                    charArray[i] = k;
                    String nextWord = String.valueOf(charArray);
                    if (wordSet.contains(nextWord)) {
                        if (nextWord.equals(endWord)) {
                            return true;
                        }
                        if (!visited.contains(nextWord)) {
                            queue.add(nextWord);
                            // 注意：添加到队列以后，必须马上标记为已经访问
                            visited.add(nextWord);
                        }
                    }
                }
                // 恢复
                charArray[i] = originChar;
            }
            return false;
        }

    }

    // time O(n ^m^2)
    // space O(n ^m^2)
    class DoubleBFS {
        class Solution {
            String s, e;
            Set<String> set = new HashSet<>();

            public int ladderLength(String _s, String _e, List<String> ws) {
                s = _s;
                e = _e;
                // 将所有 word 存入 set，如果目标单词不在 set 中，说明无解
                for (String w : ws)
                    set.add(w);
                if (!set.contains(e))
                    return 0;
                int ans = bfs();
                return ans == -1 ? 0 : ans + 1;
            }

            int bfs() {
                // d1 代表从起点 beginWord 开始搜索（正向）
                // d2 代表从结尾 endWord 开始搜索（反向）
                Queue<String> d1 = new LinkedList<>(), d2 = new LinkedList<>();

                /*
                 * m1 和 m2 分别记录两个方向出现的单词是经过多少次转换而来
                 * e.g.
                 * m1 = {"abc":1} 代表 abc 由 beginWord 替换 1 次字符而来
                 * m2 = {"xyz":3} 代表 xyz 由 endWord 替换 3 次字符而来
                 */
                Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
                d1.add(s);
                m1.put(s, 0);
                d2.add(e);
                m2.put(e, 0);

                /*
                 * 只有两个队列都不空，才有必要继续往下搜索
                 * 如果其中一个队列空了，说明从某个方向搜到底都搜不到该方向的目标节点
                 * e.g.
                 * 例如，如果 d1 为空了，说明从 beginWord 搜索到底都搜索不到 endWord，反向搜索也没必要进行了
                 */
                while (!d1.isEmpty() && !d2.isEmpty()) {
                    int t = -1;
                    // 为了让两个方向的搜索尽可能平均，优先拓展队列内元素少的方向
                    if (d1.size() <= d2.size()) {
                        t = update(d1, m1, m2);
                    } else {
                        t = update(d2, m2, m1);
                    }
                    if (t != -1)
                        return t;
                }
                return -1;
            }

            // 分别记录两个方向出现的单词是经过多少次转换而来
            // update 代表从 deque 中取出一个单词进行扩展，
            // cur 为当前方向的距离字典；other 为另外一个方向的距离字典
            int update(Queue<String> deque, Map<String, Integer> cur, Map<String, Integer> other) {
                // 获取当前需要扩展的原字符串
                String poll = deque.poll();
                int n = poll.length();

                // 枚举替换原字符串的哪个字符 i
                for (int i = 0; i < n; i++) {
                    // 枚举将 i 替换成哪个小写字母
                    for (int j = 0; j < 26; j++) {
                        // 替换后的字符串
                        String sub = poll.substring(0, i) + String.valueOf((char) ('a' + j)) + poll.substring(i + 1);
                        if (set.contains(sub)) {
                            // 如果该字符串在「当前方向」被记录过（拓展过），跳过即可
                            if (cur.containsKey(sub))
                                continue;

                            // 如果该字符串在「另一方向」出现过，说明找到了联通两个方向的最短路
                            if (other.containsKey(sub)) {
                                return cur.get(poll) + 1 + other.get(sub);
                            } else {
                                // 否则加入 deque 队列
                                deque.offer(sub);
                                cur.put(sub, cur.get(poll) + 1);
                            }
                        }
                    }
                }
                return -1;
            }
        }
    }
}
