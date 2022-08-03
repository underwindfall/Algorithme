package leetcode.datastructure.tree.bst;

import java.util.TreeMap;

// https://leetcode.cn/problems/my-calendar-i/
public class MyCalendar729 {
    // 利用treemap的二叉树属性进行快速的迭代排序
    class Calendar {
        TreeMap<Integer, Integer> calendar;

        Calendar() {
            calendar = new TreeMap<>();
        }

        // time O(n logN)
        // space O(N)
        public boolean book(int start, int end) {
            Integer prev = calendar.floorKey(start), next = calendar.ceilingKey(start);
            if ((prev == null || calendar.get(prev) <= start) && (next == null || end <= next)) {
                calendar.put(start, end);
                return true;
            }
            return false;
        }
    }
}
