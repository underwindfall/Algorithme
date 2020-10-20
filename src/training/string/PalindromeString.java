package training.string;

//https://leetcode-cn.com/problems/longest-palindromic-substring/
/* 最长回文子串
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb" */

public class PalindromeString {

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private static boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 中心扩散法
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        // 初始化最大回文子串的起点和终点
        int start = 0;
        int end = 0;
        // 遍历每个位置当作中心位置
        for (int i = 0; i < s.length(); i++) {
            int len_odd = expandCenter(s, i, i);
            int len_even = expandCenter(s, i, i + 1);
            // 获取最大的回文串长度
            int len = Math.max(len_odd, len_even);
            // 计算对应的最大回文串的起点和终点
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
            // System.out.println("start= " + start + " " + "end= " + end);
        }
        System.out.println("start= " + start + " " + "end= " + end);
        return s.substring(start, end + 1);
    }

    /**
     * ♻️循环遍历当前能够互为回文串的数字
     * 
     * @param s
     * @param left
     * @param right
     * @return
     */
    private static int expandCenter(String s, int left, int right) {
        // left = right 的时候，此时回文中心是一个字符，回文串的长度是奇数
        // right = left + 1 的时候， 此时回文中心是一个空隙，回文串长度是偶数
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        System.out.println("left= " + left + " " + "right= " + right);
        return right - left - 1;
    }

    // 动态规划
    public static String longestPalindrome3(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // 1. 状态定义
        // dp[i][j] 表示s[i...j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            // 2. 初始化
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        // 3. 状态转移
        // 注意：先填左下角
        // 填表规则：先一列一列的填写，再一行一行的填，保证左下方的单元格先进行计算
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // 头尾字符不相等，不是回文串
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    // 相等的情况下
                    // 考虑头尾去掉以后没有字符剩余，或者剩下一个字符的时候，肯定是回文串
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 状态转移
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 只要dp[i][j] == true 成立，表示s[i...j] 是否是回文串
                // 此时更新记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome3("abba"));
    }
}
