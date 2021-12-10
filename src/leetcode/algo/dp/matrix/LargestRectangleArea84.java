package leetcode.algo.dp.matrix;

import java.util.Stack;

// https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
public class LargestRectangleArea84 {
    // 单调站
    // 循环出当前柱子 左边left最高点 右边right最高点临界值（仅次于当前珠子）
    // 根据当前的 index判断出值
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;
        int n = heights.length;
        int[] left_i = new int[n];
        int[] right_i = new int[n];
        left_i[0] = -1;
        right_i[n - 1] = n;
        int res = 0;
        for (int i = 1; i < n; i++) {
            int tmp = i - 1;
            while (tmp >= 0 && heights[tmp] >= heights[i])
                tmp = left_i[tmp];
            left_i[i] = tmp;
        }
        for (int i = n - 2; i >= 0; i--) {
            int tmp = i + 1;
            while (tmp < n && heights[tmp] >= heights[i])
                tmp = right_i[tmp];
            right_i[i] = tmp;
        }
        for (int i = 0; i < n; i++)
            res = Math.max(res, (right_i[i] - left_i[i] - 1) * heights[i]);
        return res;
    }

    //time O(n)
    //space O(n)
    class IncreStack {
        public int largestRectangleArea(int[] heights) {
            int n = heights.length;
            //left左边小于height【i】 最靠近的位置
            int[] left = new int[n];
            //right右边小于height【i】 最靠近的位置
            int[] right = new int[n];

            Stack<Integer> mono_stack = new Stack<Integer>();
            for (int i = 0; i < n; ++i) {
                while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                    mono_stack.pop();
                }
                left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
                mono_stack.push(i);
            }

            mono_stack.clear();
            for (int i = n - 1; i >= 0; --i) {
                while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                    mono_stack.pop();
                }
                right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
                mono_stack.push(i);
            }

            int ans = 0;
            for (int i = 0; i < n; ++i) {
                ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
            }
            return ans;
        }
    }

    // time O(n^2)
    // space O(1)
    class BruteForce {
        public int largestRectangleArea(int[] heights) {
            int len = heights.length;
            if (len == 0)
                return 0;
            int res = 0;
            for (int i = 0; i < len; i++) {
                // 找左边最后 1 个大于等于 heights[i] 的下标
                int left = i;
                int currHeight = heights[i];
                while (left > 0 && heights[left - 1] >= currHeight) {
                    left--;
                }

                // 找右边最后 1 个大于等于 heights[i] 的索引
                int right = i;
                while (right < len - 1 && heights[right + 1] >= currHeight) {
                    right++;
                }
                int width = right - left + 1;
                res = Math.max(res, width * currHeight);
            }
            return res;
        }
    }
}
