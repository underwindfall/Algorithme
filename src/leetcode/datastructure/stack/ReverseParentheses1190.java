package leetcode.datastructure.stack;

import java.util.Deque;
import java.util.LinkedList;

//https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/
public class ReverseParentheses1190 {
    // time O(N^2)
    // espace O(N)
    class StackSolution {
        public String reverseParentheses(String s) {
            Deque<String> stack = new LinkedList<String>();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '(') {
                    stack.push(sb.toString());
                    sb.setLength(0);
                } else if (ch == ')') {
                    sb.reverse();
                    sb.insert(0, stack.pop());
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
    }

    //time O(N^2)
    //espace O(N)
    class Dfs {
        public String reverseParentheses(String s) {
            return dfs(s, 0)[0];
        }

        String[] dfs(String s, int i) {
            StringBuilder sb = new StringBuilder();
            while (i < s.length()) {
                if (s.charAt(i) == '(') {
                    String[] tmp = dfs(s, i + 1);
                    i = Integer.parseInt(tmp[1]);
                    sb.append(tmp[0]);
                } else if (s.charAt(i) == ')') {
                    return new String[] { sb.reverse().toString(), String.valueOf(i) };
                } else {
                    sb.append(s.charAt(i));
                }
                i++;
            }
            return new String[] { sb.toString() };
        }
    }

    // time O(N^2)
    // espace O(N)
    // 这个想法无异于死循环
    class DoubleIndex {
        public String reverseParentheses(String s) {
            StringBuilder sb = new StringBuilder(s);
            while (true) {
                int end = sb.indexOf(")");
                int prev = sb.lastIndexOf("(");
                if (prev == -1 || end == -1) {
                    break;
                }

                StringBuilder temp = new StringBuilder(sb.substring(prev + 1, end));
                temp.reverse();
                sb.replace(prev, end + 1, temp.toString());
            }
            return sb.toString();
        }
    }
}
