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
}
