package leetcode.algo.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode-cn.com/problems/second-minimum-time-to-reach-destination/
public class SecondMinimum2045 {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        int flag = 0; // 记录最短路径出现的次数
        int step = 0; // 记录最短路径对应的步长
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] arr : edges) {
            map.putIfAbsent(arr[0], new ArrayList<>());
            map.putIfAbsent(arr[1], new ArrayList<>());
            map.get(arr[0]).add(arr[1]);
            map.get(arr[1]).add(arr[0]);
        }
        Queue<Set<Integer>> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        int[] arr = new int[n]; // 记录每个节点访问的次数
        set.add(1);
        queue.offer(set);
        int distance = 0; // 记录当前走的距离
        while (!queue.isEmpty()) {
            ++distance;
            set = new HashSet<>();
            for (int cur : queue.poll()) {
                for (int num : map.get(cur)) {
                    if (num == n && distance > step) {
                        // 判断是否移动到终点，是否比上次步长大
                        ++flag;
                        step = distance;
                    }
                    // 找到第二小的步长，返回
                    if (flag == 2)
                        return getTime(step, time, change);
                    arr[num - 1] += 1; // 访问的节点次数加一
                    if (arr[num - 1] <= 2) // 访问次数大于2的节点不再访问
                        set.add(num);
                }
            }
            queue.offer(set);
        }
        return -1;
    }

    public int getTime(int step, int time, int change) {
        // 计算走step步的耗时
        int t = 0;
        for (int i = 1; i <= step; i++) {
            // 奇数，不能走
            if ((t / change) % 2 == 1)
                t += change - (t % change);
            t += time;
        }
        return t;
    }
}
