package leetcode.algo.dp;

// https://leetcode-cn.com/problems/decode-ways/
public class Decode91 {
    // 思路
    // 可以看作是青蛙爬楼梯的典型
    // 不同的是 0 被当作了特殊情况处理
    public int numDecodings(String s) {
        if (s.charAt(0) == '0')
            return 0;
        int[] dp = new int[s.length()];
        dp[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            // 如果说 目前的 字符是0开头，那就需要判断前面的字符
            // 如果也是0 那直接返回0 不会存在这样的搭配
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) != '1' && s.charAt(i - 1) != '2')
                    return 0;
                // 否则就意味着 dp(i) == dp(i-2) 110 思考下，因为0不能单独组队，就是10是一对 那就 说明110 10可以判断
                if (i == 1) {
                    dp[i] = 1;
                } else {
                    dp[i] = dp[i - 2];
                }
            }
            // 如果当前i 不是0
            // 需要判断下 i - 1 是不是 1x 或者 21-26 组成的数字 以确保不会超过字符串的限制 121
            else if (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i - 1) >= '1' && s.charAt(i - 1) <= '6')) {
                if (i == 1) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = dp[i - 1] + dp[i - 2];
                }
            } 
            //102 如果是这样的那就 10，2 这两种方法
            else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[s.length() - 1];
    }
}
