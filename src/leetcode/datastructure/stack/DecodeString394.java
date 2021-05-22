package leetcode.datastructure.stack;

import java.util.Stack;

// https://leetcode-cn.com/problems/decode-string/
public class DecodeString394 {

    // time O(N)
    // espace O(N)
    // 思路是用两个stack处理这个问题
    // 一个存放重复的数字
    // 一个存放【 之前的字符
    // 【 遇上这个意味着要把当前的res放进stack_res，multi 放进 stack_num， 因为开始了一个新的block
    // 】 意味着之前的block结束 需要回收相对应的重复次数和当前这个block的重复字符 组合
    // 即 res = last_res + cur_multi * res
    public String decodeString(String s) {
        Stack<String> stack_res = new Stack<>();
        Stack<Integer> stack_num = new Stack<>();
        int multi = 0;
        StringBuilder res = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                stack_num.push(multi);
                multi = 0;
                stack_res.push(res.toString());
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder();
                int curr_multi = stack_num.pop();
                for (int i = 0; i < curr_multi; i++) {
                    temp.append(res);
                }
                res = new StringBuilder(stack_res.pop() + temp);
            } else if (c >= '0' && c <= '9') {
                // 针对的是100[leetcode]
                multi = multi * 10 + Integer.parseInt(c.toString());
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    class DFS {
        class Solution {
            public String decodeString(String s) {
                return dfs(s, 0)[0];
            }

            private String[] dfs(String s, int i) {
                StringBuilder res = new StringBuilder();
                int multi = 0;
                while (i < s.length()) {
                    if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                        multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                    else if (s.charAt(i) == '[') {
                        String[] tmp = dfs(s, i + 1);
                        i = Integer.parseInt(tmp[0]);
                        while (multi > 0) {
                            res.append(tmp[1]);
                            multi--;
                        }
                    } else if (s.charAt(i) == ']')
                        return new String[] { String.valueOf(i), res.toString() };
                    else
                        res.append(String.valueOf(s.charAt(i)));
                    i++;
                }
                return new String[] { res.toString() };
            }
        }

    }

    // time O(N)
    // espace O(N)
    class Dfs {

        private int numIndex = 0;// 记录递归中 for循环中i的位置

        public String decodeString(String s) {
            if (s.length() == 0)
                return "";
            char[] chars = s.toCharArray();
            return invoke(chars, 0);
        }

        public String invoke(char[] s, int index) {
            StringBuilder sb = new StringBuilder();// 记录每次递归中得到的字符串
            StringBuilder num = new StringBuilder();// 记录每次递归前的字符串重复次数
            for (int i = index; i < s.length; i++) {
                // 判断时字母就加入结果中
                if ((s[i] >= 'a' && s[i] <= 'z') || (s[i] >= 'A' && s[i] <= 'Z')) {
                    sb.append(s[i]);
                    // 判断是数字就加入数字字符串中
                } else if (s[i] >= '0' && s[i] <= '9') {
                    num.append(s[i]);
                    // 如果是 "[" 这个符号说明，数字已经记录完整，要开始递归进入了
                } else if (s[i] == '[') {
                    // 如果是 "[" 这个符号说明 要进入递归了
                    String invoke = invoke(s, i + 1);
                    // 递归出来之后，因为是需要循环字符串才会进入递归，
                    // 所以接下来的操作就是拼接字符串，之前保存的数字就是要循环的次数
                    for (int j = 0; j < Integer.parseInt(num.toString()); j++) {
                        sb.append(invoke);
                    }
                    // 清楚之前保存的数字
                    num.setLength(0);
                    // 设置i的值，因为递归，所以numIndex之前的字符都分析过了，下次循环就直接跳过就行
                    i = numIndex;
                    // 如果是 "]" 这个符号说明要结束递归了，因为子结构分析完了，要返回数据
                } else if (s[i] == ']') {
                    numIndex = i;// 记录这次递归 i到达的位置
                    return sb.toString();
                }
            }
            return sb.toString();
        }

    }
}
