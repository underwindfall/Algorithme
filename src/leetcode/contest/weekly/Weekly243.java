package leetcode.contest.weekly;

import java.util.Arrays;

// https://leetcode-cn.com/contest/weekly-contest-243/
public class Weekly243 {
    // https://leetcode-cn.com/problems/check-if-word-equals-summation-of-two-words/
    // time O(N+M)
    // espace (1)
    class LC_5772 {
        public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
            return convertStringToNum(firstWord) + convertStringToNum(secondWord) == convertStringToNum(targetWord);
        }

        int convertStringToNum(String s) {
            int result = 0;
            for (int i = 0; i < s.length(); i++) {
                result = result * 10 + (s.charAt(i) - 'a');
            }

            return result;
        }
    }

    // https://leetcode-cn.com/problems/maximum-value-after-insertion/
    // time O(N)
    // espace O(1)
    class LC_5773 {
        public String maxValue(String n, int x) {
            if (n.charAt(0) == '-') {
                int i = 1;
                while (i < n.length() && n.charAt(i) - '0' <= x) {
                    i++;
                }
                return insert(n, i, x);
            } else {
                int i = 0;
                while (i < n.length() && n.charAt(i) - '0' >= x) {
                    i++;
                }
                return insert(n, i, x);
            }
        }

        private String insert(String n, int i, int x) {
            return n.substring(0, i) + x + n.substring(i);
        }
    }

    // https://leetcode-cn.com/problems/process-tasks-using-servers/
    // 这题目需要用优先队列 堆
    // TODO
    // class LC_5774 {
    // public int[] assignTasks(int[] servers, int[] tasks) {

    // }
    // }

    // 我们用 f[i][j] 表示经过了dist[0] 到 dist[i−1] 的 i 段道路，并且跳过了 j 次的最短用时
    // dp (i, j) = min(dp(i- 1,j)+dist[i-1]/speed, dp(i-1)(j-1)+dist[i-1]/speed)
    class LC_5775 {
        final static double EPS = 1e-7;
        // 极大值
        final static double INFTY = 1e20;

        public int minSkips(int[] dist, int speed, int hoursBefore) {
            int n = dist.length;
            double f[][] = new double[n + 1][n + 1];
            for (double[] p : f) {
                Arrays.fill(p, INFTY);
            }

            f[0][0] = 0.;
            for (int i = 1; i <= n; ++i) {
                for (int j = 0; j <= i; ++j) {
                    if (j != i) {
                        f[i][j] = Math.min(f[i][j], Math.ceil(f[i - 1][j] + (double) dist[i - 1] / speed - EPS));
                    }
                    if (j != 0) {
                        f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + (double) dist[i - 1] / speed);
                    }
                }
            }
            for (int j = 0; j <= n; ++j) {
                if (f[n][j] < hoursBefore + EPS) {
                    return j;
                }
            }
            return -1;

        }
    }
}
