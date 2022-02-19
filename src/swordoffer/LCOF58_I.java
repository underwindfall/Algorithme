package swordoffer;

import java.util.Stack;

//https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/
public class LCOF58_I {
    // 双指针
    // time O(n)
    // space O(n)
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();
        for (int left = 0; left < s.length();) {
            StringBuilder sb = new StringBuilder();
            int right = left;
            while (right < s.length() && s.charAt(right) != ' ') {
                sb.append(s.charAt(right));
                right++;
            }
            if (sb.length() != 0) {
                stack.push(sb.toString());
            }
            while (right < s.length() && s.charAt(right) == ' ') {
                right++;
            }
            left = right;
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop() + " ");
        }
        return result.toString().trim();
    }

    // time O(n)
    // space O(n)
    class DoubeIndex {
        public String reverseWords(String s) {
            s = s.trim(); // 删除首尾空格
            int j = s.length() - 1, i = j;
            StringBuilder res = new StringBuilder();
            while (i >= 0) {
                while (i >= 0 && s.charAt(i) != ' ') {
                    // 搜索首个空格
                    i--;
                }
                res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
                while (i >= 0 && s.charAt(i) == ' ') {
                    // 跳过单词间空格
                    i--;
                }
                j = i; // j 指向下个单词的尾字符
            }
            return res.toString().trim(); // 转化为字符串并返回
        }
    }

    //time O(n)
    //space O(n)
    class API {
        public String reverseWords(String s) {
            String[] words = s.trim().split("\\s+");

            StringBuilder sb = new StringBuilder();
            for (int i = words.length - 1; i >= 0; i--) {
                sb.append(words[i]);
                if (i != 0) sb.append(" ");
            }
            return sb.toString();
        }
    }
}
