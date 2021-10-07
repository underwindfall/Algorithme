package leetcode.datastructure.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

// https://leetcode-cn.com/problems/reverse-words-in-a-string
public class ReverseWords151 {

    class APISolution {

        // 借助已经存在的的collections reverse实现
        // time O(N)
        // espace O(N)
        public String reverseWords(String s) {
            s = s.trim();
            List<String> list = Arrays.asList(s.split(" "));
            Collections.reverse(list);
            return String.join(" ", list);
        }
    }

    class Manul {

        // time O(N)
        // espace O(1)
        public String reverseWords(String s) {
            StringBuilder sBuilder = trimSpaces(s);
            // 翻转整个句子顺序
            reversePharse(sBuilder, 0, sBuilder.length() - 1);
            // 翻转每个单词顺序
            reverseEachWord(sBuilder);
            return sBuilder.toString();
        }

        // time O(m*N/2) m 空格的个数
        private void reverseEachWord(StringBuilder sBuilder) {
            int n = sBuilder.length();
            int start = 0, end = 0;
            while (start < n) {
                while (end < n && sBuilder.charAt(start) != ' ') {
                    ++end;
                }
                reversePharse(sBuilder, start, end - 1);
                start = end + 1;
                end++;
            }
        }

        // time O(N/2) stringBuilder长度
        private void reversePharse(StringBuilder stringBuilder, int start, int end) {
            while (start < end) {
                char tmp = stringBuilder.charAt(start);
                stringBuilder.setCharAt(start++, stringBuilder.charAt(end));
                stringBuilder.setCharAt(end--, tmp);
            }
        }

        // time :O(N/2) N->文字长度
        private StringBuilder trimSpaces(String s) {
            int left = 0, right = s.length() - 1;
            // 去除头部
            while (left < right && s.charAt(left) == ' ') {
                left++;
            }
            // 去除尾部
            while (left < right && s.charAt(right) == ' ') {
                right--;
            }

            // 去除头部
            StringBuilder stringBuilder = new StringBuilder();
            while (left < right) {
                char c = s.charAt(left);
                if (c != ' ') {
                    stringBuilder.append(c);
                } else if (stringBuilder.charAt(stringBuilder.length() - 1) != ' ') {
                    stringBuilder.append(c);
                }
                left++;
            }
            return stringBuilder;
        }

    }

    class StackQueue {
        // time O(N)单词数量
        // espace O(N) 单词数量
        public String reverseWords(String s) {
            int left = 0, right = s.length() - 1;
            // 去除头部
            while (left < right && s.charAt(left) == ' ') {
                left++;
            }
            // 去除尾部
            while (left < right && s.charAt(right) == ' ') {
                right--;
            }

            // 去除头部
            StringBuilder stringBuilder = new StringBuilder();
            Stack<String> tmp = new Stack<>();
            while (left <= right) {
                char c = s.charAt(left);
                // 需要将单词推入stack
                if ((stringBuilder.length() != 0) && (c == ' ')) {
                    tmp.push(stringBuilder.toString());
                    stringBuilder.setLength(0);
                } else if (c != ' ') {
                    stringBuilder.append(c);
                }
                left++;
            }
            tmp.push(stringBuilder.toString());
            StringBuilder result = new StringBuilder();
            while (tmp.size() > 0) {
                String a = tmp.pop();
                if (tmp.size() > 0) {
                    result.append(a + " ");
                } else {
                    result.append(a);
                }
            }
            return result.toString();
        }
    }

    class DoubleIndex {
        public String reverseWords(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            StringBuilder stringBuilder = new StringBuilder();
            char[] charArray = s.toCharArray();
            for (int right = charArray.length - 1; right >= 0; right--) {
                if (charArray[right] != ' ') {
                    int left = right - 1;
                    while (left >= 0 && charArray[left] != ' ') {
                        left--;
                    }
                    for (int i = left + 1; i <= right; i++) {
                        stringBuilder.append(charArray[i]);
                    }
                    stringBuilder.append(' ');
                    right = left;
                }
            }
            return stringBuilder.toString().trim();
        }
    }
}
