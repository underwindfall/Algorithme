package leetcode.algo.dp;

//https://leetcode-cn.com/problems/number-of-digit-one/
public class CountDigitOne233 {

    // 找规律
    // time O(logN)
    // espace O(logN)
    class Rule {

        // dp[i] = i * (int)Math.pow(10, i-1);
        int[] dp = new int[] { 0, 1, 20, 300, 4000, 50000, 600000, 7000000, 80000000, 900000000 };

        public int countDigitOne(int n) {
            if (n < 10)
                return n == 0 ? 0 : 1;
            String num = String.valueOf(n);
            int length = num.length() - 1, first = num.charAt(0) - '0';
            int firstNum = (int) Math.pow(10, length);
            int nxt = n - firstNum * first;
            return first > 1 ? countDigitOne(nxt) + dp[length] * first + firstNum
                    : countDigitOne(nxt) + dp[length] * first + nxt + 1;
        }

    }

    // time O(m)
    // espace O(n)
    public int countDigitOne(int n) {
        String s = String.valueOf(n);
        int m = s.length();
        if (m == 1) {
            return n > 0 ? 1 : 0;
        }
        // 计算第 i 位前缀代表的数值，和后缀代表的数值
        // 例如 abcde 则有 ps[2] = ab; ss[2] = de
        int[] ps = new int[m], ss = new int[m];
        ss[0] = Integer.parseInt(s.substring(1));
        for (int i = 1; i < m - 1; i++) {
            ps[i] = Integer.parseInt(s.substring(0, i));
            ss[i] = Integer.parseInt(s.substring(i + 1));
        }
        ps[m - 1] = Integer.parseInt(s.substring(0, m - 1));
        // 分情况讨论
        int ans = 0;
        for (int i = 0; i < m; i++) {
            int x = s.charAt(i) - '0', len = m - i - 1;
            int prefix = ps[i], suffix = ss[i];
            int tot = 0;
            tot += prefix * Math.pow(10, len);
            if (x == 0) {

            } else if (x == 1) {
                tot += suffix + 1;
            } else {
                tot += Math.pow(10, len);
            }
            ans += tot;
        }
        return ans;
    }
}
