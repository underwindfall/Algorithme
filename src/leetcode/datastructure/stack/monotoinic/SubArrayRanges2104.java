package leetcode.datastructure.stack.monotoinic;

import java.util.Arrays;
import java.util.Stack;

//https://leetcode-cn.com/problems/sum-of-subarray-ranges/
public class SubArrayRanges2104 {

    // time O(n)
    // space O(1)
    // https://github.com/wisdompeak/LeetCode/tree/master/Stack/2104.Sum-of-Subarray-Ranges
    // 单调递增序列 0 -> i维持nextSmallerElement
    //            i -> 0维持prevSmallerElement
    // 单调递减序列 0 -> i维持nextGreaterElement
    //            i -> 0维持prevGreaterElement
    class Monotoinic {

        public long subArrayRanges(int[] nums) {
            int n = nums.length;
            Stack<Integer> stack = new Stack<>();
            int[] nextSmaller = new int[n];
            Arrays.fill(nextSmaller, n);
            for (int i = 0; i < n; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    nextSmaller[stack.peek()] = i;
                    stack.pop();
                }
                stack.push(i);
            }

            stack.clear();

            int[] prevSmaller = new int[n];
            Arrays.fill(prevSmaller, -1);
            for (int i = n - 1; i >= 0; i--) {
                // 小技巧 index右边扩大到底
                while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                    prevSmaller[stack.peek()] = i;
                    stack.pop();
                }
                stack.push(i);
            }

            stack.clear();

            int[] nextGreater = new int[n];
            Arrays.fill(nextGreater, n);
            for (int i = 0; i < n; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    nextGreater[stack.peek()] = i;
                    stack.pop();
                }
                stack.push(i);
            }
            stack.clear();

            int[] prevGreater = new int[n];
            Arrays.fill(prevGreater, -1);
            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                    prevGreater[stack.peek()] = i;
                    stack.pop();
                }
                stack.push(i);
            }
            stack.clear();

            long ans = 0;

            for (int i = 0; i < n; i++) {
                int l = prevGreater[i];
                int r = nextGreater[i];
                ans += (long) nums[i] * (i - l) * (r - i);
            }

            for (int i = 0; i < n; i++) {
                int l = prevSmaller[i];
                int r = nextSmaller[i];
                ans -= (long) nums[i] * (i - l) * (r - i);
            }
            return ans;
        }

    }

    // time O(n^2)
    // space O(1)
    class Loop {
        public long subArrayRanges(int[] nums) {
            int n = nums.length;
            long ret = 0;
            for (int i = 0; i < n; i++) {
                int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
                for (int j = i; j < n; j++) {
                    minVal = Math.min(minVal, nums[j]);
                    maxVal = Math.max(maxVal, nums[j]);
                    ret += maxVal - minVal;
                }
            }
            return ret;
        }
    }
}