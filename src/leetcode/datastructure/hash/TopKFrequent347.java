package leetcode.datastructure.hash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@SuppressWarnings("unchecked")
public class TopKFrequent347 {
    class Hash {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> occurrences = new HashMap<>();
            for (int num : nums) {
                occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
            }

            // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
            PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
                public int compare(int[] m, int[] n) {
                    return m[1] - n[1];
                }
            });
            for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
                int num = entry.getKey(), count = entry.getValue();
                if (queue.size() == k) {
                    if (queue.peek()[1] < count) {
                        queue.poll();
                        queue.offer(new int[] { num, count });
                    }
                } else {
                    queue.offer(new int[] { num, count });
                }
            }
            int[] ret = new int[k];
            for (int i = 0; i < k; ++i) {
                ret[i] = queue.poll()[0];
            }
            return ret;
        }
    }

    class Bucket {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i : nums) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }

            List<Integer>[] list = new List[nums.length + 1];
            for (int key : map.keySet()) {
                // 获取出现的次数作为下标
                int i = map.get(key);
                if (list[i] == null) {
                    list[i] = new ArrayList<>();
                }
                System.out.println("key " + key + "i" + i);
                list[i].add(key);
            }
            int[] result = new int[k];
            int index = 0;
            for (int i = list.length - 1; i > 0 && index < k; i--) {
                if (list[i] == null) {
                    continue;
                } else {
                    List<Integer> tmp = list[i];

                    for (int j = 0; j < tmp.size(); j++) {
                        result[index++] = tmp.get(j);
                    }
                }
            }
            return result;
        }
    }
}
