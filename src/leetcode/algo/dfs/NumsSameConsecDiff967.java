package leetcode.algo.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.cn/problems/numbers-with-same-consecutive-differences/
public class NumsSameConsecDiff967 {
    // time O(2^n)
    // space O(2^n)
    class DFS {
        public int[] numsSameConsecDiff(int n, int k) {
            if (n == 1) {
                return new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
            }

            List<Integer> list = new ArrayList<>();
            for (int i = 1; i < n; i++) {
                dfs(n - 1, i, k, list);
            }

            int[] ans = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }

        void dfs(int index, int num, int k, List<Integer> ans) {
            if (index == 0) {
                ans.add(num);
                return;
            }
            int tailDigit = num % 10;
            List<Integer> nextDigits = new ArrayList<>();
            if (k != 0) {
                nextDigits.add(tailDigit - k);
                nextDigits.add(tailDigit + k);
            } else {
                nextDigits.add(tailDigit + k);
            }
            for (int next : nextDigits) {
                if (next >= 0 && next < 10) {
                    int newNum = num * 10 + next;
                    dfs(index - 1, newNum, k, ans);
                }
            }
        }
    }

    class BFS {
        public int[] numsSameConsecDiff(int n, int k) {
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 1; i <= 9; i++) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                int len = queue.size(); // len长度 ，三位数还是两位数
                while (len < n) { // len长度==n，那么停止计算
                    int size = queue.size();
                    while (size > 0) { // 上一轮所有的数都进行运算，比如，n=3,k=1时，第一轮只有1，第二轮有10,12，到第三轮就有101,121,123
                        int poll = queue.poll();
                        int lastDigit = poll % 10;
                        if (lastDigit - k >= 0) {
                            int x1 = poll * 10 + lastDigit - k;
                            queue.offer(x1);
                        }
                        if (lastDigit + k >= 0 && lastDigit + k < 10 && k != 0) { // 如果k=0,那么只计算其中一个即可
                            int x2 = poll * 10 + k + lastDigit;
                            queue.offer(x2);
                            // System.out.println("poll:"+poll+",x2:"+x2);
                        }
                        size--;
                        // System.out.println(queue);
                    }
                    len++;
                }
                while (!queue.isEmpty()) { // 循环结束时，队列中剩余的101,121,123是我们要的答案，放入解中
                    list.add(queue.poll());
                }
            }

            int[] ans = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }
    }
}
