package leetcode.algo.dp.subsquence;

//https://leetcode-cn.com/problems/vowels-of-all-substrings/
//time O(n)
//space O(1)
// xxa
// dp(i) 是i作为结尾的dp个数
// if (i) == vowel dp(i) = dp(i - 1) + i + 1
//            else dp(i) = dp(i - 1)
public class CountVowels2063 {
    public long countVowels(String word) {
        long count = 0;
        long[] dp = new long[word.length()];

        for (int i = 0; i < word.length(); i++) {
            if (i == 0) {
                dp[i] = isVowel(word.charAt(i)) ? 1 : 0;
            } else {
                if (isVowel(word.charAt(i))) {
                    dp[i] = dp[i - 1] + i + 1;
                } else {
                    dp[i] = dp[i - 1];
                }
            }
            count += dp[i];
        }

        return count;
    }

    boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
