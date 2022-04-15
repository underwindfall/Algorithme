package leetcode.algo.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode-cn.com/problems/mini-parser
public class MiniParser385 {

    // time O(n)
    // space O(n)

    class DFS {
        // 递归函数通过字符数组和cur下标确定要处理的位置
        char[] chars;
        int cur = 0;

        public NestedInteger deserialize(String s) {
            chars = s.toCharArray();
            // 如果本身不是一个集合而是一个整数的情况
            if (chars[0] != '[') {
                return new NestedInteger(Integer.valueOf(s));
            }
            // 调用递归函数返回根基和
            return dfs();
        }

        NestedInteger dfs() {
            NestedInteger nest = new NestedInteger();
            int num = 0;// num用于缓存用逗号分割的整数类型的值
            boolean negative = false;// 当前记录的整数是不是负数
            while (cur != chars.length - 1) {
                cur++;
                if (chars[cur] == ',')
                    continue;
                if (chars[cur] == '[')
                    nest.add(dfs());// 遇到[递归获取子集合
                else if (chars[cur] == ']')
                    return nest;
                else if (chars[cur] == '-')
                    negative = true;
                else { // 数字的情况
                    if (negative)
                        num = num * 10 - (chars[cur] - '0');
                    else
                        num = num * 10 + (chars[cur] - '0');
                    // 如果下一个字符是,或者]说明当前数字已经记录完了，需要加入集合中
                    if (chars[cur + 1] == ',' || chars[cur + 1] == ']') {
                        nest.add(new NestedInteger(num));
                        num = 0;
                        negative = false;
                    }
                }
            }
            return null;
        }
    }

    // time O(n)
    // space O(n)
    class StackSolution {
        public NestedInteger deserialize(String s) {
            if (s.charAt(0) != '[')
                return new NestedInteger(Integer.valueOf(s));
            int n = s.length();

            Stack<NestedInteger> stack = new Stack<>();

            NestedInteger parent = new NestedInteger();

            stack.add(parent);

            StringBuilder sb = new StringBuilder();

            for (int i = 1; i < n; i++) {
                char c = s.charAt(i);
                if (c != '[' && c != ']' && c != ',') {
                    sb.append(c);
                } else {
                    if (c == '[') {
                        NestedInteger sub = new NestedInteger();
                        stack.peek().add(sub);
                        stack.add(sub);
                    } else if (c == ']') {
                        addNested(sb, stack.peek());
                        stack.pop();
                    } else if (c == ',') {
                        addNested(sb, stack.peek());
                    }

                    sb = new StringBuilder();
                }
            }

            return parent;
        }

        void addNested(StringBuilder sb, NestedInteger n) {
            if (sb.length() == 0)
                return;
            int val = Integer.parseInt(sb.toString());
            n.add(new NestedInteger(val));
        }
    }

    class NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger() {
        };

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
        };

        // @return true if this NestedInteger holds a single integer, rather than a
        // nested list.
        public boolean isInteger() {
            return false;
        };

        // @return the single integer that this NestedInteger holds, if it holds a
        // single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return 0;
        };

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
        };

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
        };

        // @return the nested list that this NestedInteger holds, if it holds a nested
        // list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return new ArrayList<>();
        };
    }
}
