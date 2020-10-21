package training.string;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

// https://leetcode-cn.com/problems/reverse-words-in-a-string/
/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 
 * 说明：
 * 
 * 无空格字符构成一个 单词 。 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。  
 * 
 * 示例 1：
 * 
 * 输入："the sky is blue" 输出："blue is sky the" 示例 2：
 * 
 * 输入："  hello world!  " 输出："world! hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 示例 3：
 * 
 * 输入："a good   example" 输出："example good a" 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 4：
 * 
 * 输入：s = " Bob Loves Alice " 输出："Alice Loves Bob" 示例 5：
 * 
 * 输入：s = "Alice does not even like bob" 输出："bob like even not does Alice"
 */
public class ReverseWord {
    public static String reverseWords(String s) {
        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    // 手动写反转字符串
    public static String reverseWords1(String s) {
        // 删除首部和尾部还有多余部位空格
        StringBuilder sb = trimSpaces(s);
        // 反转字符串
        reverse(sb, 0, sb.length() - 1);
        // 反转每个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    private static void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;
        while (start < n) {
            // 循环到单词的结尾
            while (end < n && sb.charAt(end) != ' ') {
                ++end;
            }
            // 翻转单词
            reverse(sb, start, end - 1);
            start = end + 1;
            ++end;
        }
    }

    private static void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char tmp = sb.charAt(start);
            sb.setCharAt(start++, sb.charAt(end));
            sb.setCharAt(end--, tmp);
        }
    }

    private static StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去除字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }
        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }
        // 去掉字符串多余的空白字符串去除
        StringBuilder stringBuilder = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            // 如果不是空格就加入
            if (c != ' ') {
                stringBuilder.append(c);
            }
            // 如果字符串末尾是空格加入一次
            else if (stringBuilder.charAt(stringBuilder.length() - 1) != ' ') {
                stringBuilder.append(c);
            }
            ++left;
        }
        return stringBuilder;
    }

    // 翻转 意味着就是先进后出 -》 stack
    static String reverseWords2(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }
        Deque<String> d = new ArrayDeque<String>();
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (word.length() != 0 && c == ' ') {
                // 将单词 push 到队列的头部
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());
        return String.join(" ", d);
    }

    // 双指针
    static String reverseWords3(String s) {
        s = s.trim();
        int j = s.length() - 1, i = j;
        StringBuilder word = new StringBuilder();
        while (i >= 0) {
            // 搜索首个空格
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            // 添加单词
            word.append(s.substring(i + 1, j + 1) + " ");
            // 跳过单词间过多的空格
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            j = i;
        }

        return word.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("  hello world!  "));
        System.out.println(reverseWords1("  hello world!  "));
        System.out.println(reverseWords2("  hello world!  "));
        System.out.println(reverseWords3("  hello world!  "));
    }
}
