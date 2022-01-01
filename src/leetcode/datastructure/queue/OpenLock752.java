package leetcode.datastructure.queue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// https://leetcode-cn.com/problems/open-the-lock/
// 思路通过BFS处理问题
// 已知初识测试的code 为0000 + target目的地 0202 每次只能移动一步 求最短路径
public class OpenLock752 {

    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }

    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }

    // time O(N^2 * A^N +D) //A数子个数 N位数 D deadend大小
    // espace O(A^N +D)
    // BFS
    public int openLock(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>();
        for (String s : deadends) {
            deads.add(s);
        }
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        // 从起点开始启动广度优先搜索
        Queue<String> q = new LinkedList<>();
        int step = 0;
        q.offer("0000");
        visited.add("0000");
        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                /* 判断是否到达终点 */
                if (deads.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }

                /* 将一个节点的相邻节点加入队列 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.add(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.add(down);
                        visited.add(down);
                    }
                }
            }
            /* 在这里增加步数 */
            step++;
        }
        return -1;

    }

    // time
    // space
    class DoubleBFS {
        String t, s;
        Set<String> set = new HashSet<>();

        public int openLock(String[] _ds, String _t) {
            s = "0000";
            t = _t;
            if (s.equals(t))
                return 0;
            for (String d : _ds)
                set.add(d);
            if (set.contains(s))
                return -1;
            int ans = bfs();
            return ans;
        }

        int bfs() {
            // d1 代表从起点 s 开始搜索（正向）
            // d2 代表从结尾 t 开始搜索（反向）
            Queue<String> d1 = new LinkedList<>(), d2 = new LinkedList<>();
            /*
             * m1 和 m2 分别记录两个方向出现的状态是经过多少次转换而来
             * e.g.
             * m1 = {"1000":1} 代表 "1000" 由 s="0000" 旋转 1 次而来
             * m2 = {"9999":3} 代表 "9999" 由 t="9996" 旋转 3 次而来
             */

            Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
            d1.offer(s);
            m1.put(s, 0);
            d2.offer(t);
            m2.put(t, 0);
            /*
             * 只有两个队列都不空，才有必要继续往下搜索
             * 如果其中一个队列空了，说明从某个方向搜到底都搜不到该方向的目标节点
             * e.g.
             * 例如，如果 d1 为空了，说明从 s 搜索到底都搜索不到 t，反向搜索也没必要进行了
             */
            while (!d1.isEmpty() && !d2.isEmpty()) {
                int t = -1;
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

        int update(Queue<String> q, Map<String, Integer> cur, Map<String, Integer> other) {
            String currWord = q.poll();
            char[] pcs = currWord.toCharArray();
            int step = cur.get(currWord);
            // 枚举替换哪个字符
            for (int i = 0; i < 4; i++) {
                // 能「正向转」也能「反向转」，这里直接枚举偏移量 [-1,1] 然后跳过 0
                for (int j = -1; j <= 1; j++) {
                    if (j == 0)
                        continue;
                    // 求得替换字符串 str
                    int origin = pcs[i] - '0';
                    int next = (origin + j) % 10;
                    if (next == -1)
                        next = 9;
                    char[] clone = pcs.clone();
                    clone[i] = (char) (next + '0');
                    String nextWord = String.valueOf(clone);

                    if (set.contains(nextWord))
                        continue;
                    if (cur.containsKey(nextWord))
                        continue;

                    // 如果在「另一方向」找到过，说明找到了最短路，否则加入队列
                    if (other.containsKey(nextWord)) {
                        return step + 1 + other.get(nextWord);
                    } else {
                        q.offer(nextWord);
                        cur.put(nextWord, step + 1);
                    }

                }
            }
            return -1;
        }
    }

    // 这里有一种取巧法，就是双向取这个target 即 从开始找到结尾，也可以从结尾找到开始
    // 双向BFS
    int doubleBFSopenLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String s : deadends)
            deads.add(s);
        // 用集合不用队列，可以快速判断元素是否存在
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        int step = 0;
        q1.add("0000");
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            // 哈希集合在遍历的过程中不能修改，用 temp 存储扩散结果
            Set<String> temp = new HashSet<>();

            /* 将 q1 中的所有节点向周围扩散 */
            for (String cur : q1) {
                /* 判断是否到达终点 */
                if (deads.contains(cur))
                    continue;
                if (q2.contains(cur))
                    return step;
                visited.add(cur);

                /* 将一个节点的未遍历相邻节点加入集合 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up))
                        temp.add(up);
                    String down = minusOne(cur, j);
                    if (!visited.contains(down))
                        temp.add(down);
                }
            }
            /* 在这里增加步数 */
            step++;
            // temp 相当于 q1
            // 这里交换 q1 q2，下一轮 while 就是扩散 q2
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }

}
