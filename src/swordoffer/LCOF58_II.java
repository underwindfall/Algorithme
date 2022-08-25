package swordoffer;

//https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
public class LCOF58_II {
    // 双指针
    // time O(n)
    // space O(1)
    public String reverseLeftWords(String s, int n) {
        StringBuilder res = new StringBuilder();
        for (int right = n; right < s.length(); right++) {
            res.append(s.charAt(right));
        }
        for (int left = 0; left < n; left++) {
            res.append(s.charAt(left));
        }
        return res.toString();
    }

    // time O(1)
    // space O(1)
    // substring java time O(1)
    class API {
        public String reverseLeftWords(String s, int n) {
            StringBuilder res = new StringBuilder();
            res.append(s.substring(n));
            res.append(s.substring(0, n));
            return res.toString();
        }
    }

    // time O(n)
    // space O(n)
    class ReverseMultiple {
        public String reverseLeftWords(String s, int n) {
            int len = s.length();
            StringBuilder sb = new StringBuilder(s);
            reverse(sb, 0, n - 1);
            reverse(sb, n, len - 1);
            reverse(sb, 0, len - 1);
            return sb.toString();
        }

        void reverse(StringBuilder word, int left, int right) {
            while (left < right) {
                char c = word.charAt(left);
                word.setCharAt(left, word.charAt(right));
                word.setCharAt(right, c);
                left++;
                right--;
            }
        }
    }
}
