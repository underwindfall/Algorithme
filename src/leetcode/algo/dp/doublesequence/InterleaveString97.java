package leetcode.algo.dp.doublesequence;

// https://leetcode-cn.com/problems/interleaving-string/
public class InterleaveString97 {
    // time O(M N)
    // space O(M N)
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3)
            return false;
        // 定义dp[i][j] s1的前i位，s2的前j位，能否组成s3的前i+j位
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        // s1 前 0 位 s2前 0 位 一定也是为空
        dp[0][0] = true;
        for (int i = 1; i < len1 + 1; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 1; j < len2 + 1; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                // s1 的前 ii 个字符和 s2s2 的前 j-1j−1 个字符能否构成 s3s3 的前 i+j-1i+j−1 位，且 s2s2 的第 jj 位
                dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                        || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[len1][len2];
    }

    // time O(NM)
    // space O(NM)
    class Dfs {
        int l1, l2, l3;
        String s1, s2, s3;
        boolean[][] visited;

        public boolean isInterleave(String s1, String s2, String s3) {
            l1 = s1.length();
            l2 = s2.length();
            l3 = s3.length();
            if (l1 + l2 != l3)
                return false;
            visited = new boolean[l1 + 1][l2 + 1];
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;

            return dfs(0, 0, 0);
        }

        private boolean dfs(int i, int j, int k) {
            if (k == l3)
                return true;
            if (visited[i][j])
                return false;
            visited[i][j] = true;
            if (i < l1 && s1.charAt(i) == s3.charAt(k) && dfs(i + 1, j, k + 1))
                return true;
            if (j < l2 && s2.charAt(j) == s3.charAt(k) && dfs(i, j + 1, k + 1))
                return true;
            return false;
        }
    }
}
