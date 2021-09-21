package leetcode.datastructure.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// https://leetcode-cn.com/problems/basic-calculator/
public class BasicCaculatorI224 {
    class Common {
        public int calculate(String s) {
            // 存放所有数字
            Deque<Integer> nums = new ArrayDeque<>();
            // 为了防止第一个数为负数，先往 nums 加个 0
            nums.addLast(0);
            s = s.replace(" ", "");
            // 存放所有操作服
            Deque<Character> ops = new ArrayDeque<>();
            int n = s.length();
            char[] cs = s.toCharArray();

            for (int i = 0; i < n; i++) {
                char c = cs[i];
                if (c == '(') {
                    ops.addLast(c);
                } else if (c == ')') {
                    // 计算到最后一个左括号为止
                    while (!ops.isEmpty()) {
                        char op = ops.peekLast();
                        if (op != '(') {
                            calc(nums, ops);
                        } else {
                            ops.pollLast();
                            break;
                        }
                    }
                } else {
                    if (isNum(c)) {
                        int u = 0;
                        int j = i;
                        // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                        while (j < n && isNum(cs[j])) {
                            u = u * 10 + (int) (cs[j++] - '0');
                        }
                        nums.addLast(u);
                        i = j - 1;
                    } else {
                        if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                            nums.addLast(0);
                        }
                        // 有一个新操作要入栈时，先把栈内可以算的都算了
                        while (!ops.isEmpty() && ops.peekLast() != '(') {
                            calc(nums, ops);
                        }
                        ops.addLast(c);
                    }
                }
            }
            while (!ops.isEmpty())
                calc(nums, ops);
            return nums.peekLast();
        }

        void calc(Deque<Integer> nums, Deque<Character> ops) {
            if (nums.isEmpty() || nums.size() < 2)
                return;
            if (ops.isEmpty())
                return;
            int b = nums.pollLast(), a = nums.pollLast();
            char op = ops.pollLast();
            nums.addLast(op == '+' ? a + b : a - b);
        }

        boolean isNum(char c) {
            return Character.isDigit(c);
        }

    }

    // time o(n)
    // space O(n)
    public static int calculate(String s) {
        // 计算结果，部分计算结果，括号中计算结果
        int res = 0;
        // 当前的数字，例如：1+23中的1或者23
        int num = 0;
        // 符号，加号(+1)或者减号(-1)
        int sign = 1;
        // 当右括号时，用于存储计算结果
        Stack<Integer> stack = new Stack<>();

        char[] chars = s.toCharArray();
        int len = chars.length;

        for (int i = 0; i < len; i++) {
            char c = chars[i];
            // 如果当前字符为' '，则直接continue
            if (c == ' ') {
                continue;
            }
            // 如果当前字符是一个数字，则用num进行记录
            // 当前有可能是一个>9的数字，所以需要num = num * 10 + c - '0'
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
                // 判断当前数字是否已经取完
                // 例如：123+4，只有当取到+时，才能确定123为当前的num
                if (i < len - 1 && '0' <= chars[i + 1] && chars[i + 1] <= '9') {
                    continue;
                }
                // 如果当前字符为'+'或者'-'
            } else if (c == '+' || c == '-') {
                // 将num置为0，用来存放当前符号(+/-)之后的数字
                num = 0;
                // 如果当前符号为+，则sign为+1
                // 如果当前符号为-，则sign为-1
                sign = c == '+' ? 1 : -1;
                // 如果当前符号为'('
            } else if (c == '(') {
                // 例如当前表达式为：'123+(...)'
                // 则将res:123，入栈
                stack.push(res);
                // 则将sign:+，入栈
                stack.push(sign);
                // 同时res置为0，用来保存()中的计算结果
                res = 0;
                // sign置为初始状态，为1
                sign = 1;
                // 如果当前符号为')'
            } else if (c == ')') {
                // '('前边的符号出栈
                sign = stack.pop();
                // 将num替换为括号中的计算结果
                num = res;
                // 将res替换为括号前边的计算结果
                res = stack.pop();
            }
            // 每遍历一次，得到一个res
            res += sign * num;
        }
        return res;
    }
}
