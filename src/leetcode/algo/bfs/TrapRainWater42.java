package leetcode.algo.bfs;

import java.util.Stack;

// https://leetcode-cn.com/problems/trapping-rain-water/
public class TrapRainWater42 {
    // time O(n^2)
    // space O(1)
    class BrutForce {
        public int trap(int[] height) {
            int res = 0;
            for (int i = 0; i < height.length; i++) {
                int maxLeft = 0;
                for (int j = 0; j < i; j++) {
                    maxLeft = Math.max(maxLeft, height[j]);
                }
                int maxRight = 0;
                for (int j = i + 1; j < height.length; j++) {
                    maxRight = Math.max(maxRight, height[j]);
                }
                int max = Math.min(maxLeft, maxRight);
                res += max > height[i] ? max - height[i] : 0;
            }
            return res;
        }
    }

    // time O(n)
    // space O(n)
    class DP {
        public int trap(int[] height) {
            int res = 0;
            int[] left = new int[height.length];
            int[] right = new int[height.length];
            for (int i = 0; i < left.length; i++) {
                left[i] = Math.max(height[i], i - 1 >= 0 ? left[i - 1] : height[0]);
            }
            for (int i = right.length - 1; i >= 0; i--) {
                right[i] = Math.max(height[i], i + 1 < height.length ? right[i + 1] : 0);
            }
            for (int i = 0; i < height.length; i++) {
                if (height[i] < left[i] && height[i] < right[i]) {
                    res += Math.min(right[i], left[i]) - height[i];
                }
            }
            return res;
        }
    }

    // https://leetcode-cn.com/problems/trapping-rain-water/solution/trapping-rain-water-by-ikaruga/
    // time O(n)
    // space O(n)
    class DecrementStack {
        public int trap(int[] height) {
            int res = 0;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < height.length; i++) {
                while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                    int curr = stack.peek();
                    stack.pop();
                    if (stack.isEmpty()) {
                        break;
                    }
                    int left = stack.peek();
                    int right = i;
                    int h = Math.min(height[left], height[right]) - height[curr];
                    res += (right - left - 1) * h;
                }
                stack.push(i);
            }
            return res;
        }
    }

    // time O(n)
    // space O(1)
    class DoubleIndex {
        public int trap(int[] height) {
            int ans = 0;
            int left = 0, right = height.length - 1;
            int leftMax = 0, rightMax = 0;
            while (left < right) {
                leftMax = Math.max(leftMax, height[left]);
                rightMax = Math.max(rightMax, height[right]);
                if (height[left] < height[right]) {
                    ans += leftMax - height[left];
                    ++left;
                } else {
                    ans += rightMax - height[right];
                    --right;
                }
            }
            return ans;
        }
    }
}
