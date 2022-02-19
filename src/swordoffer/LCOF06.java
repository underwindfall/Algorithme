package swordoffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
public class LCOF06 {
    // time O(n)
    // space O(n)
    class Interative {
        public int[] reversePrint(ListNode head) {
            List<Integer> res = new ArrayList<>();
            while (head != null) {
                res.add(0, head.val);
                head = head.next;
            }
            int[] ans = new int[res.size()];
            int i = 0;
            while (i < res.size()) {
                ans[i] = res.get(i);
                i++;
            }
            return ans;
        }
    }

    // time O(1)
    // space O(n)
    class Recursive {
        List<Integer> list = new ArrayList<>();

        public int[] reversePrint(ListNode head) {
            recursive(head);
            int[] ans = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }

        void recursive(ListNode head) {
            if (head == null) {
                return;
            }
            recursive(head.next);
            list.add(head.val);
        }
    }

    // time O(n)
    // space O(n)
    class StackSolution {
        public int[] reversePrint(ListNode head) {
            Stack<ListNode> stack = new Stack<ListNode>();
            ListNode tmp = head;
            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.next;
            }
            int size = stack.size();
            int[] print = new int[size];
            for (int i = 0; i < size; i++) {
                print[i] = stack.pop().val;
            }
            return print;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
