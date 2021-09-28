package leetcode.algo.dp.state;

import java.util.Arrays;

//https://leetcode-cn.com/problems/decode-ways-ii/
public class NumDecodings697 {

    // 看下// https://leetcode-cn.com/problems/decode-ways/
    /**
     * dp(i) 对应的是 前i个数字的组合
     * 
     * 1. cs[i] == '*'
     * -------------a). i 单独作为一个组合 dp[i] += 9 * dp[i - 1]
     * -------------b). i 组合 i - 1
     * -------------------------- i - 1 == '*'  dp[i] = (i >= 2 ? dp[i - 2] : 1) * (9 + 6)
     * -------------------------- i - 1 != '*'
     * -------------------------------- i - 1 == '1' dp[i] = (i >= 2 ? dp[i - 2] : 1) * 9
     * -------------------------------- i - 1 == '2' dp[i] = (i >= 2 ? dp[i - 2] : 1) * 6
     * 
     * 
     * 2. cs[i] != '*'
     * -------------a). i 单独作为一个组合 
     * ---------------------------- i -  1 = '0' return 0
     * ---------------------------- i -  1 = '1-9' dp[i] = dp[i - 1]
     * -------------b). i 组合 i - 1
     * -------------------------- i - 1 == '*'  &&  i == '0' dp[i] = dp[i - 2] * 2;
     * --------------------------i - 1 == '*' &&  i != '0'( 1 <= i <= 6)  dp[i] = dp[i - 2] * 2;
     * --------------------------i - 1 == '*' &&  i != '0'( 7 <= i <= 9)  dp[i] = dp[i - 2];
     * 
     * 
     * --------------------------i - 1 != '*' &&  i == '0' dp[i] = dp[i - 2]
     * --------------------------i - 1 != '*' (i - 1 == '1') &&  i != '0' (1 <= i <= 9) dp[i] = dp[i - 2]
     * --------------------------i - 1 != '*' (i - 1 == '2') &&  i != '0' (1 <= i <= 6) dp[i] = dp[i - 2]
     */
    // time O(n)
    // space O(1)
    class DP {
        int mod = (int) 1e9 + 7;

        public int numDecodings(String s) {
            char[] cs = s.toCharArray();
            int n = cs.length;
            long[] f = new long[n];
            f[0] = cs[0] == '*' ? 9 : (cs[0] != '0' ? 1 : 0);
            for (int i = 1; i < n; i++) {
                char c = cs[i], prev = cs[i - 1];
                if (c == '*') {
                    // cs[i] 单独作为一个 item
                    f[i] += f[i - 1] * 9;
                    // cs[i] 与前一个字符共同作为一个 item
                    if (prev == '*') {
                        // 11 - 19 & 21 - 26
                        f[i] += (i - 2 >= 0 ? f[i - 2] : 1) * 15;
                    } else {
                        int u = (int) (prev - '0');
                        if (u == 1) {
                            f[i] += (i - 2 >= 0 ? f[i - 2] : 1) * 9;
                        } else if (u == 2) {
                            f[i] += (i - 2 >= 0 ? f[i - 2] : 1) * 6;
                        }
                    }
                } else {
                    int t = (int) (c - '0');
                    if (prev == '*') {
                        if (t == 0) {
                            f[i] += (i - 2 >= 0 ? f[i - 2] : 1) * 2;
                        } else {
                            // cs[i] 单独作为一个 item
                            f[i] += f[i - 1];
                            // cs[i] 与前一个字符共同作为一个 item
                            if (t <= 6) {
                                f[i] += (i - 2 >= 0 ? f[i - 2] : 1) * 2;
                            } else {
                                f[i] += i - 2 >= 0 ? f[i - 2] : 1;
                            }
                        }
                    } else {
                        int u = (int) (prev - '0');
                        if (t == 0) {
                            if (u == 1 || u == 2) {
                                f[i] += i - 2 >= 0 ? f[i - 2] : 1;
                            }
                        } else {
                            // cs[i] 单独作为一个 item
                            f[i] += (f[i - 1]);
                            // cs[i] 与前一个字符共同作为一个 item
                            if (u == 1) {
                                f[i] += i - 2 >= 0 ? f[i - 2] : 1;
                            } else if (u == 2 && t <= 6) {
                                f[i] += i - 2 >= 0 ? f[i - 2] : 1;
                            }
                        }
                    }
                }
                f[i] %= mod;
            }
            return (int) (f[n - 1]);
        }
    }

    class DFSMEMO {
        class Solution {
            int MOD = 1000000007;

            public int numDecodings(String s) {
                // string
                long[] memo = new long[s.length()];
                Arrays.fill(memo, Long.MAX_VALUE);
                return (int) (dfs(s, s.length() - 1, memo));
            }

            long dfs(String s, int i, long[] memo) {
                if (i < 0)
                    return 1;

                if (memo[i] != Long.MAX_VALUE) {
                    return memo[i];
                }

                long res;
                if (s.charAt(i) == '*') {
                    res = 9 * dfs(s, i - 1, memo) % MOD;
                    if (i > 0 && s.charAt(i - 1) == '1') {
                        // 1* -> //11, 12, 13, 14, 15 , 16, 17, 18, 19
                        res = (res + 9 * dfs(s, i - 2, memo)) % MOD;
                    }
                    // 2* -> // 21,22,23,24,25,26
                    else if (i > 0 && s.charAt(i - 1) == '2') {
                        res = (res + 6 * dfs(s, i - 2, memo)) % MOD;
                    } else if (i > 0 && s.charAt(i - 1) == '*') {
                        res = (res + 15 * dfs(s, i - 2, memo)) % MOD;
                    }

                } else {
                    res = s.charAt(i) != '0' ? dfs(s, i - 1, memo) : 0;

                    if (i > 0 && s.charAt(i - 1) == '1') {
                        res = (res + dfs(s, i - 2, memo)) % MOD;
                    } else if (i > 0 && s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                        res = (res + dfs(s, i - 2, memo)) % MOD;
                    } else if (i > 0 && s.charAt(i - 1) == '*') {
                        res = (res + (s.charAt(i) <= '6' ? 2 : 1) * dfs(s, i - 2, memo)) % MOD;
                    }
                }
                memo[i] = res;
                return memo[i];
            }
        }
    }

}
