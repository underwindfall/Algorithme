package leetcode.algo.prefixsum;

// https://leetcode.cn/problems/maximum-score-after-splitting-a-string/
public class MaxScore1422 {
    // time O(N)
    // space O(1)
    public int maxScore(String s) {
        int score = 0;
        int n = s.length();
        if (s.charAt(0) == '0') {
            score++;
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '1') {
                score++;
            }
        }
        int ans = score;
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == '0') {
                score++;
            } else {
                score--;
            }
            ans = Math.max(ans, score);
        }
        return ans;

    }
}
