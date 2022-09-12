package leetcode.algo.backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//https://leetcode-cn.com/problems/restore-ip-addresses
public class RestoreIpAddresses93 {
    // time O(n^4)
    // space O(n^4)
    class Backtrack {
        public List<String> restoreIpAddresses(String s) {
            int len = s.length();
            List<String> res = new ArrayList<>();
            // 如果长度不够，不搜索
            if (len < 4 || len > 12) {
                return res;
            }

            Deque<String> path = new ArrayDeque<>(4);
            int splitTimes = 0;
            dfs(s, len, splitTimes, 0, path, res);
            return res;
        }

        void dfs(String s, int len, int split, int start, Deque<String> path, List<String> res) {
            if (start == len) {
                if (split == 4) {
                    res.add(String.join(".", path));
                }
                return;
            }
            // 看到剩下的不够了，就退出（剪枝），len - begin 表示剩余的还未分割的字符串的位数
            if (len - start < (4 - split) || len - start > 3 * (4 - split)) {
                return;
            }

            for (int i = 0; i < 3; i++) {
                if (start + i >= len) {
                    break;
                }
                int ip = judgeIfIpSegment(s, start, start + i);
                if (ip != -1) {
                    path.addLast(String.valueOf(ip));
                    dfs(s, len, split + 1, start + i + 1, path, res);
                    path.removeLast();
                }
            }
        }

        int judgeIfIpSegment(String s, int left, int right) {
            int len = right - left + 1;
            // 大于 1 位的时候，不能以 0 开头
            if (len > 1 && s.charAt(left) == '0') {
                return -1;
            }

            // 转成 int 类型
            int res = 0;
            for (int i = left; i <= right; i++) {
                res = res * 10 + s.charAt(i) - '0';
            }

            if (res > 255) {
                return -1;
            }
            return res;
        }
    }
}
