package leetcode.algo.bfs;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/push-dominoes/
public class PushDominoes838 {
    // time O(n)
    // space O(1)
    class Doubleindex {
        /**
         * 多米诺骨牌的四种情况
         * 'R......R' => 'RRRRRRRR'
         * 'R......L' => 'RRRRLLLL' or 'RRRR.LLLL'
         * 'L......R' => 'L......R'
         * 'L......L' => 'LLLLLLLL'
         * 两个相邻的被推倒的牌互不影响。
         * 一张站立的牌（"."）的最终状态与离其两侧最近的 "L" 或 "R" 有关。
         */
        public String pushDominoes(String dominoes) {
            // 给牌两端添加虚拟端点
            dominoes = "L" + dominoes + "R";
            // 定义截取区间的左边界
            int l = 0;
            StringBuilder res = new StringBuilder();
            for (int r = 1; r < dominoes.length(); ++r) {
                // 截取的区间两端必须是L或者R,所以跳过.
                if (dominoes.charAt(r) == '.')
                    continue;
                // 0的位置是虚拟的牌，不放进结果
                if (l != 0) {
                    // 因为左边的端点下一次会移动到当前的右端点位置
                    // 所以这里只添加左端点，就可以将每次区间的端点加进去
                    res.append(dominoes.charAt(l));
                }
                // 当前选取的区间中.的个数
                int mid = r - l - 1;

                // 判断选取的区间是四种情形中哪一种
                if (dominoes.charAt(l) == dominoes.charAt(r)) {
                    // 'R......R' => 'RRRRRRRR'
                    // 'L......L' => 'LLLLLLLL'
                    for (int i = 0; i < mid; ++i)
                        res.append(dominoes.charAt(l));
                } else if (dominoes.charAt(l) == 'L' && dominoes.charAt(r) == 'R') {
                    // L......R' => 'L......R'
                    for (int i = 0; i < mid; ++i)
                        res.append('.');
                } else {
                    // 'R......L' => 'RRRRLLLL' or 'RRRR.LLLL'

                    // 添加前半部分的牌
                    for (int i = 0; i < mid / 2; ++i)
                        res.append('R');
                    // 中间的.的个数为偶数时，为'R......L' => 'RRRRLLLL，不需要在中间位置添加.
                    // 中间的.的个数为奇数时，为'R.......L' => 'RRRR.LLLL'，需要在中间位置添加.
                    if (mid % 2 == 1)
                        res.append('.');
                    // 添加后半部分的牌
                    for (int i = 0; i < mid / 2; ++i)
                        res.append('L');
                }
                // 将左端点的位置移动到右端点，下次的区间从当前区间的右端点开始
                l = r;
            }
            return res.toString();
        }
    }

    // time O(n)
    // space O(n)
    class BFS {
        public String pushDominoes(String dominoes) {
            char[] ch = dominoes.toCharArray();
            int n = ch.length;
            // pos, time, direction
            Queue<int[]> q = new LinkedList<>();
            int[] status = new int[n];
            for (int i = 0; i < n; i++) {
                if (ch[i] != '.') {
                    status[i] = 1;
                    q.offer(new int[] { i, 1, ch[i] == 'L' ? -1 : 1 });
                }
            }

            while (!q.isEmpty()) {
                int[] info = q.poll();
                int pos = info[0], time = info[1], direction = info[2];
                int next = pos + direction;
                if (ch[pos] == '.' || next < 0 || next >= n) {
                    continue;
                }
                if (status[next] == 0) {
                    q.offer(new int[] { next, time + 1, direction });
                    ch[next] = direction == -1 ? 'L' : 'R';
                    status[next] = time + 1;
                } else if (status[next] == time + 1) {
                    ch[next] = '.';
                }
            }
            return new String(ch);
        }
    }
}
