package leetcode.algo.dp.doublesequence;

//https://leetcode-cn.com/problems/longest-chunked-palindrome-decomposition/
public class LlongestDecomposition1147 {

    class DP {
        public int longestDecomposition(String text) {
            int N = text.length();
            // instantiate dp array 1 longer than half the text, since we need dp[0] = 0 for
            // base case, every i is stored with "+1" offset
            int[] dp = new int[N / 2 + 1];
            int max = 0;
            for (int j = 0; j < N / 2; j++) {
                for (int i = 0; i <= j; i++) {
                    // s[i:j] (inclusive both ends) has a matching palindrome
                    // and we need to make sure that i covers the entire string or it has matching
                    // pairs to its left
                    if (text.substring(i, j + 1).equals(text.substring(N - 1 - j, N - i)) && (i == 0 || dp[i] != 0)) {
                        dp[j + 1] = Math.max(dp[j + 1], dp[i] + 2);
                        max = Math.max(max, dp[i] + 2);
                    }
                }
            }
            // I feel like this part is not as straight-forward and took me a while
            // if string is even length and has dp[n/2] != 0 then we just want to return
            // that, otherwise, we need to add 1 to the max(dp) (odd length string always
            // has one unmatched segment in the middle)
            return ((N % 2 == 0) && dp[N / 2] != 0) ? dp[N / 2] : max + 1;
        }
    }

    int[][] memo;

    public int longestDecomposition(String text) {
        int n = text.length();
        memo = new int[n][n];
        return dfs(text, 0, n - 1);
    }

    int dfs(String text, int l, int r) {
        // APA -> P 最开始的值就是1
        if (l == r)
            return 1;
        if (l > r)
            return 0;
        if (memo[l][r] > 0)
            return memo[l][r];
        int x = (r - l + 1) / 2;
        // 计算子串的长度
        int m = 0;
        int intial = 1;
        for (int i = 1; i <= x; i++) {
            // java substring include head not tail
            if (text.substring(l, l + i).equals(text.substring(r - i + 1, r + 1))) {
                m = Math.max(m, dfs(text, l + i, r - i));
                intial = 2;
                break; // 如果AAPAA，那么中间的APA可以组成新的P，所以可以找到最短的相等前缀字串和后缀字串后就直接break
            }
        }

        memo[l][r] = m + intial;
        return memo[l][r];
    }

}
