package leetcode.algo.dp.doublesequence.interval;

//https://leetcode-cn.com/problems/zuma-game/
public class ZumaGame488 {
    public class Solution {
        private int result = Integer.MAX_VALUE;

        private int[] map = new int[26];

        private char[] colors = { 'R', 'Y', 'B', 'G', 'W' };

        public int findMinStep(String board, String hand) {
            for (int i = 0; i < hand.length(); i++) {
                map[hand.charAt(i) - 'A']++;
            }
            dfs(new StringBuilder(board), 0);
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        private void dfs(StringBuilder board, int step) {
            if (step >= result) {
                return;
            }
            if (board.length() == 0) {
                result = Math.min(step, result);
                return;
            }
            for (int i = 0; i < board.length(); i++) {
                char c = board.charAt(i);
                int j = i;
                while (j + 1 < board.length() && board.charAt(j + 1) == c) {
                    j++;
                }
                if (j == i && map[c - 'A'] >= 2) { // 只有单个球
                    StringBuilder tmp = new StringBuilder(board);
                    tmp.insert(i, c + "" + c);
                    map[c - 'A'] -= 2;
                    dfs(eliminate(tmp), step + 2);
                    map[c - 'A'] += 2;
                } else if (j == i + 1) { // 存在两个颜色相同且相邻的球
                    if (map[c - 'A'] >= 1) {
                        StringBuilder tmp = new StringBuilder(board);
                        tmp.insert(i, c);
                        map[c - 'A']--;
                        dfs(eliminate(tmp), step + 1);
                        map[c - 'A']++;
                    }
                    for (char color : colors) {
                        if (color == c) {
                            continue;
                        }
                        if (map[color - 'A'] >= 1) {
                            StringBuilder tmp = new StringBuilder(board);
                            tmp.insert(i + 1, color); // 尝试往这两个颜色相同且相邻的球中间插入一个颜色不同的球
                            map[color - 'A']--;
                            dfs(eliminate(tmp), step + 1);
                            map[color - 'A']++;
                        }
                    }
                }
            }
        }

        private StringBuilder eliminate(StringBuilder sb) {
            boolean flag = true;
            while (flag) {
                flag = false;
                for (int i = 0; i < sb.length(); i++) {
                    int j = i + 1;
                    while (j < sb.length() && sb.charAt(j) == sb.charAt(i)) {
                        j++;
                    }
                    if (j - i >= 3) {
                        sb.delete(i, j);
                        flag = true;
                    }
                }
            }
            return sb;
        }
    }

    // public int findMinStep(String board, String hand) {
    // int[] map = new int[128];
    // for (char color : hand.toCharArray()) {
    // map[color]++;

    // }
    // return dfs(board, map);
    // }

    // int dfs(String board, int[] map) {
    // if (board.isEmpty())
    // return 0;

    // int ans = Integer.MAX_VALUE;

    // int i = 0, j = 0;
    // while (i < board.length()) {
    // while (j < board.length() && board.charAt(i) == board.charAt(j)) {
    // j++;
    // }
    // // board[i, j - 1] 中间都是相同的颜色 color
    // char color = board.charAt(i);
    // // 同样的 board[i, j - 1] 所需要用手中中和的球数
    // int b = 3 - (j - i);
    // // 如果手中有足够的求可以消除
    // if (map[color] >= b) {
    // // 消除board[i, j - 1] 的球
    // // System.out.println("==="+i);
    // String nb = eliminate(board.substring(0, i)) + board.substring(j);
    // // String nb = eliminate(board.substring(0, i) + board.substring(j));
    // // 更新手中的球数
    // map[color] -= b;

    // int r = dfs(nb, map);
    // // System.out.println("="+r);
    // if (r >= 0) {
    // ans = Math.min(ans, r + b);
    // }
    // // 回复手中的球
    // map[color] += b;
    // }
    // i = j;
    // }
    // return ans == Integer.MAX_VALUE ? -1 : ans;
    // }

    // // Update the board by removing all consecutive 3+ balls.
    // // "YWWRRRWWYY" -> "YWWWWYY" -> "YYY" -> ""
    // String eliminate(String board) {
    // // System.out.println("input ===="+board);
    // int i = 0;
    // while (i < board.length()) {
    // int j = i;
    // while (j < board.length() && board.charAt(i) == board.charAt(j)) {
    // j++;
    // }
    // if (j - i >= 3) {
    // // [i,j]都被消除
    // board = board.substring(0, i) + board.substring(j);
    // i = 0;
    // } else {
    // i++;
    // }
    // }
    // // System.out.println("output ===="+board);
    // return board;
    // }
}
