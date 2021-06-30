package leetcode.datastructure.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
public class LeastKSmallest40 {
    // 维护一个 最大堆 最大堆的数量是K
    // 依次将元素添加到「最大堆」中；
    // 当「最大堆」的元素个数达到 K 时，将当前元素与堆顶元素进行对比
    //如果当前元素大于堆顶元素，则放弃当前元素，继续进行下一个元素；
    //如果当前元素小于堆顶元素，则删除堆顶元素，将当前元素加入到「最小堆」中。

    class Heap {
        public int[] getLeastNumbers(int[] arr, int k) {
            int[] result = new int[k];
            if (k == 0) {
                return result;
            }
            PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            for (int i = 0; i < k; i++) {
                queue.offer(arr[i]);
            }            
            for (int i = k; i < arr.length; i++) {
                if (queue.peek() > arr[i]) {
                    queue.poll();
                    queue.offer(arr[i]);
                }
            }
            for (int i = 0; i < k; i++) {
                result[i] = queue.poll();
            }
            return result;
        }
    }
}
