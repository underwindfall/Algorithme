package leetcode.algo.dp.doublesequence.interval;

import java.util.Arrays;

//https://leetcode-cn.com/problems/encode-string-with-shortest-length/
public class Encode471 {

    class DP {
        String[][] dp;

        public String encode(String s) {
            int n = s.length();
            dp = new String[n][n];
            for (int len = 1; len <= n; len++) {
                for (int i = 0; i + len - 1 < n; i++) {
                    int j = i + len - 1;
                    dp[i][j] = lc459(s, i, j);
                    if (len > 4) {
                        for (int k = i; k < j; k++) {
                            String spilt = dp[i][k] + dp[k+1][j];
                            if (dp[i][j].length() > spilt.length()) dp[i][j] = spilt;
                        }
                    }
                }
            }
            return dp[0][n - 1];
        }

        /**
         * 另 t = s + s, 从下标 1 的字符开始查找字符串s， 找到下标p， 
         * 如果p != n, 存在连续重复的子字符串ps = s.substring(0, p), 个数为 n / p 
         * 否则， 不存在连续重复子字符串， 无法进行编码
         */
        String lc459(String s, int i, int j) {
            s = s.substring(i, j + 1);
            if (s.length() < 5)
                return s;
            int p = (s + s).indexOf(s, 1);
            if (p != s.length()) {
                int cnt = s.length() / p;
                return cnt + "[" + dp[i][i + p - 1] + "]";
            }
            // 否则， 无法进行编码
            return s;
        }
    }

    // 超时了
    class RecurisveMEMO {
        public String encode(String s) {
            int n = s.length();
            String[][] dp = new String[n][n];
            for (String[] row : dp)
                Arrays.fill(row, "");
            return dfs(s, 0, n - 1, dp);
        }

        String dfs(String s, int i, int j, String[][] dp) {
            if (i > j)
                return "";
            String ans = dp[i][j];
            if (ans.length() > 0)
                return ans;
            int len = j - i + 1;
            ans = s.substring(i, i + len);
            if (len < 5)
                return ans;
            int p = (ans + ans).indexOf(ans, 1);
            if (p < len) {
                ans = (len / p) + "[" + dfs(s, i, i + p - 1, dp) + "]";
            }
            for (int k = i; k < j; k++) {
                String c = dfs(s, i, k, dp);
                String e = dfs(s, k + 1, j, dp);
                if (c.length() + e.length() < ans.length()) {
                    ans = c + e;
                }
            }
            return ans;
        }
    }
}
