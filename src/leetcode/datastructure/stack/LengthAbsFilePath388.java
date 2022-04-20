package leetcode.datastructure.stack;

import java.util.Stack;

// https://leetcode-cn.com/problems/longest-absolute-file-path/
public class LengthAbsFilePath388 {
    // time O(n)
    // space O(n)
    /**
     * 假设当前的路径为 x/y/z
     *            len lx / lx + ly + 1 lx+ly+lz+2
     * 
     */
    class Solution {
        public int lengthLongestPath(String input) {
            int n = input.length();
            int pos = 0;
            int ans = 0;
            Stack<Integer> stack = new Stack<Integer>();

            while (pos < n) {
                /* 检测当前文件的深度 */
                int depth = 1;
                while (pos < n && input.charAt(pos) == '\t') {
                    pos++;
                    depth++;
                }
                /* 统计当前文件名的长度 */
                boolean isFile = false;
                int len = 0;
                while (pos < n && input.charAt(pos) != '\n') {
                    if (input.charAt(pos) == '.') {
                        isFile = true;
                    }
                    len++;
                    pos++;
                }
                /* 跳过当前的换行符 */
                pos++;

                while (stack.size() >= depth) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    len += stack.peek() + 1;
                }
                if (isFile) {
                    ans = Math.max(ans, len);
                } else {
                    stack.push(len);
                }
            }
            return ans;
        }
    }

}
