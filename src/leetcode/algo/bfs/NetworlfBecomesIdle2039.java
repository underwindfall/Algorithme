package leetcode.algo.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode-cn.com/problems/the-time-when-the-network-becomes-idle/
public class NetworlfBecomesIdle2039 {
    // time
    // space
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < patience.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] adj : edges) {
            graph.get(adj[0]).add(adj[1]);
            graph.get(adj[1]).add(adj[0]);
        }

        int[] distance = new int[patience.length];
        Arrays.fill(distance, -1);
        Queue<Integer> q = new LinkedList<>();
        distance[0] = 0;
        q.add(0);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int adj : graph.get(u)) {
                if (distance[adj] != -1) {
                    continue;
                }
                distance[adj] = distance[u] + 1;
                q.add(adj);
            }
        }

        // System.out.println(Arrays.toString(distance));

        /**
         * 一个数据服务器 i 往主服务器发送消息所消耗的时间为两节点之间的最短路径 dist[i]，而从发送消息到收到回复所需的时间为 di = 2 * dist[i]
         * 同时每个数据服务器还存在时间间隔为 t = patience[i] 的重发动作，并且动作只有在第一次收到主服务的回复后才会停止。
         * 
         * 因此如果 di <= t，那么数据服务器不会发生重发动作，该节点活动停止时间点为 di；当 di > t，数据服务器将会发生重发动作，
         * 
         * 且最后一个数据包的发送时间为 (di - 1) / t * t，只有当最后一个数据包收到回复，该节点的所有活动才算停止，停止时间点为 (di - 1) / t * t + di。
         * 所有节点的活动停止时间点的最大值即是答案。
         * 
         */
        int ans = 0;
        for (int i = 1; i < patience.length; i++) {
            int stop = distance[i] * 2;
            // 那么消息往返一遍的时间就是di = 2 * dist[i]
            // 假设t = patience[i]，即等待时间
            // 如果t >= di 这个请求被处理的用时就是di
            // 若t < di 那么会从t + 1时刻开始重复发包
            // 我们会发送 1 + (di - 1 - t) / t = (di - 1) / t个包
            // 那么这些包的最后发送时间是 (di - 1) / t * t
            // 它的返回时间是 (di - 1) / t * t + di
            // 遍历所有的结点 取处理时间的最大值返回即可
            int lastSend = (stop - 1) / patience[i] * patience[i];
            ans = Math.max(ans, lastSend + stop);
        }

        return ans + 1;

    }
}
