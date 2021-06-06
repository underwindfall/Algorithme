package leetcode.datastructure.backtrack;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/generate-parentheses
public class GenerateParenthesis22 {

    // 找规律 递归
    // 剩余左括号总数 要小于或等于右括号  
    // time O(N)
    // espace O(N)
    class DFS {
        List<String> res = new ArrayList<>();

        public List<String> generateParenthesis(int n) {
            if (n <= 0) {
                return res;
            }
            getParenthesis("", n, n);
            return res;
        }

        void getParenthesis(String str, int left, int right) {
            if (left == 0 && right == 0) {
                res.add(str);
                return;
            }
            if (left == right) {
                // 剩余左右括号数相等，下一个只能用左括号
                getParenthesis(str + "(", left - 1, right);
            } else if (left < right) {
                // 剩余左括号小于右括号，下一个可以用左括号也可以用右括号
                if (left > 0) {
                    getParenthesis(str + "(", left - 1, right);
                }
                getParenthesis(str + ")", left, right - 1);
            }
        }
    }

    // 回溯算法
    class DSF1 {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            if (n == 0) {
                return res;
            }
            StringBuilder path = new StringBuilder();
            dfs(path, n, n, res);
            return res;
        }

        /**
         * @param path  从根结点到任意结点的路径，全程只使用一份
         * @param left  左括号还有几个可以使用
         * @param right 右括号还有几个可以使用
         * @param res
         */
        void dfs(StringBuilder path, int left, int right, List<String> res) {
            if (left == 0 && right == 0) {
                res.add(path.toString());
                return;
            }

            if (left > right) {
                return;
            }

            if (left > 0) {
                path.append("(");
                dfs(path, left - 1, right, res);
                path.deleteCharAt(path.length() - 1);
            }

            if (right > 0) {
                path.append(")");
                dfs(path, left, right - 1, res);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

}
