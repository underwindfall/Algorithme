package leetcode.algo.sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// https://leetcode.cn/problems/Jf1JuT/solution/wai-xing-wen-zi-dian-by-leetcode-solutio-to66/
public class AlienOrder {
    // 思路： 1、先标记所有出现过的字母，没出现过的字母不要在答案中出现；
    // 2、正向建图（存储某个字母的儿子）：用list存储，反向建图，用set存储；
    // 3、把所有出现过的点（字母）并且没有父节点的（入度为0）点先拿出来组成字母表前缀，这些是字母表中最可能的最靠前的字母们；
    // 4、BFS取出每一个字母，同时把它从相应儿子的set中删除，假如此时set已经空，这说明这个点的祖先你们全都遍历过了，而且也加到字母表里面了，那么这个字母也能够假如字母表；
    // 5、此时遍历所有set，假如还有不空的，说明拓扑排序存在环，则不存再答案；
    // 6、对于有些出现了的，但是还没有跟其他字母存在先后关系的字母，也要拼在字母表后边，既然出现了，也不可以落下呀
    // ，，，注意陷阱： 1、建图不要重复加入边，出错案例：["ac","ab","zc","zb"]
    // 2、后者不能是前者的真前缀，出错案例：["abc","ab"]
    @SuppressWarnings("unchecked")
    public String alienOrder(String[] words) {
        boolean has[] = new boolean[26];// 记录字母是否出现过
        char c[][] = new char[words.length][];
        for (int i = 0; i < c.length; i++) {
            c[i] = words[i].toCharArray();
            for (int j = 0; j < c[i].length; j++) {
                has[c[i][j] - 'a'] = true;
            }
        }
        List<Integer> son[] = new List[26];
        Set<Integer> father[] = new Set[26];
        for (int i = 0; i < 26; i++) {
            son[i] = new ArrayList<>();
            father[i] = new HashSet<>();
        }
        // 以下正反建图：
        for (int i = 1; i < c.length; i++) {
            int p = 0;
            while (p < Math.min(c[i - 1].length, c[i].length) && c[i - 1][p] == c[i][p]) {
                p++;
            }
            if (p == c[i].length && p < c[i - 1].length) {
                return "";
            } // 后者为前者的真前缀，报警了啊
            if (p < c[i].length && p < c[i - 1].length) {
                // 此时遇到的首个不同字母，有关字母表顺序，主注意去重
                if (father[c[i][p] - 'a'].add(c[i - 1][p] - 'a')) {
                    son[c[i - 1][p] - 'a'].add(c[i][p] - 'a');
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        StringBuilder ans = new StringBuilder();
        // 先把没有父节点的字母收集起来，这些是字母中的前排
        for (int i = 0; i < 26; i++) {
            if (has[i] && father[i].size() == 0) {
                ans.append((char) (i + 'a'));
                has[i] = false;
                q.add(i);
            }
        }
        // 下边开始BFS：
        while (q.size() > 0) {
            int a = q.poll();
            for (int i = 0; i < son[a].size(); i++) {
                int b = son[a].get(i);
                father[b].remove(a);
                if (father[b].size() == 0) {
                    ans.append((char) (b + 'a'));
                    has[b] = false;
                    q.add(b);
                }
            }
        }
        // 下边开始检查有无set不是空的，有的话说明存在环，顺便把无顺序关系的字母加进来
        for (int i = 0; i < 26; i++) {
            if (father[i].size() > 0) {
                return "";
            }
            if (has[i]) {
                ans.append((char) (i + 'a'));
            }
        }
        return ans.toString();
    }
}
