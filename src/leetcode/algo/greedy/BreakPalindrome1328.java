package leetcode.algo.greedy;

// https://leetcode-cn.com/problems/break-a-palindrome/
public class BreakPalindrome1328 {
    // time O(1)
    // espace O(1)
    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if (n == 1) {
            return "";
        }

        char[] ch = palindrome.toCharArray();

        int index = -1;

        for (int i = 0; i < n / 2; i++) {
            if (ch[i] != 'a') {
                index = i;
                ch[i] = 'a';
                break;
            }
        }

        if (index == -1) {
            ch[n - 1] = 'b';
        }
        return new String(ch);
    }
}
