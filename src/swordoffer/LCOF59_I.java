package swordoffer;

import java.util.ArrayList;
import java.util.PriorityQueue;

//https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
public class LCOF59_I {
    // time O(N*logN)
    // space O(N)
    class Heap {
        public int[] maxSlidingWindow(int[] nums, int k) {
            ArrayList<Integer> ret = new ArrayList<>();
            if (k > nums.length || k < 1)
                return new int[]{};
            PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);  /* 大顶堆 */
            for (int i = 0; i < k; i++)
                heap.add(nums[i]);
            ret.add(heap.peek());
            for (int i = 0, j = i + k; j < nums.length; i++, j++) {            /* 维护一个大小为 size 的大顶堆 */
                heap.remove(nums[i]);
                heap.add(nums[j]);
                ret.add(heap.peek());
            }
    
            int[] ans = new int[ret.size()];
            for (int i = 0; i < ret.size(); i++) {
                ans[i] = ret.get(i);
            }
    
            return ans;
        }
    }
}
