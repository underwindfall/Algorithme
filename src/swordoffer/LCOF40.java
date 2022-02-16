package swordoffer;

import java.util.PriorityQueue;

//https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
public class LCOF40 {

    //time O(nlogk)
    //space O(n)
    class PQ {
        public int[] getLeastNumbers(int[] arr, int k) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
            for (int i = 0; i < arr.length; i++) {
                if (k == 0) {
                    break;
                }
                if (pq.size() < k) {
                    pq.offer(arr[i]);
                } else if (pq.peek() > arr[i]) {
                    if (pq.size() == k) {
                        pq.poll();
                    }
                    pq.offer(arr[i]);
                }
            }
            // System.out.println(pq);
            int[] res = new int[k];
            int index = 0;
            while (!pq.isEmpty()) {
                res[index++] = pq.poll();
            }
            return res;
        }
    }

    // time O(1)
    // space O(n)
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 统计每个数字出现的次数
        int[] counter = new int[10001];
        for (int num : arr) {
            counter[num]++;
        }
        // 根据counter数组从头找出k个数作为返回结果
        int[] res = new int[k];
        int idx = 0;
        for (int num = 0; num < counter.length; num++) {
            while (counter[num]-- > 0 && idx < k) {
                res[idx++] = num;
            }
            if (idx == k) {
                break;
            }
        }
        return res;
    }
}
