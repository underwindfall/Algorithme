package leetcode.datastructure.stack;

import java.util.Stack;

// https://leetcode-cn.com/problems/daily-temperatures/
public class DailyTemperatures739 {

    // 用单调栈的方式解决问题
    // 栈元素问递增排列，栈顶最小，栈地最大
    // 刚开始为空放入遍历元素A
    // 之后比较新加入的遍历元素B
    // 若 B > A, A 出战，并回收对应的index值 和B index做比较就是对应的解
    // 若 B < A, B 入栈 遍历下一元素 C
    // 若 C > A , BA都出栈，并在相对应的位置方解
    // time :O(N)
    // epsace: O(N)
    class StackSolution {
        public int[] dailyTemperatures(int[] T) {
            Stack<Integer> stack = new Stack<>();
            int length = T.length;
            int[] ans = new int[length];
            for (int i = 0; i < length; i++) {
                int curr = T[i];
                while (!stack.isEmpty() && curr > T[stack.peek()]) {
                    int index = stack.pop();
                    ans[index] = i - index;
                }
                stack.push(i);
            }
            return ans;
        }
    }

    // 思路
    // 遍历数组
    // 在遍历的同时，从数组的第二位起开始遍历，当且仅当后面的温度大鱼当前温度时，将数组的差值填入
    // time :O(M*N)
    // espace: O(M)
    class Array {
        public int[] dailyTemperatures(int[] T) {
            int length = T.length;
            int[] result = new int[length];
            for (int i = 0; i < length; i++) {
                int curr = T[i];
                if (curr < 100) {
                    for (int j = i + 1; j < length; j++) {
                        if (T[j] > curr) {
                            result[i] = j - i;
                            break;
                        }
                    }
                }
            }
            return result;
        }
    }
}
