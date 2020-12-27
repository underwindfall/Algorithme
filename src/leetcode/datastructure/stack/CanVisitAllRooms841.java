package leetcode.datastructure.stack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// https://leetcode-cn.com/problems/keys-and-rooms/
public class CanVisitAllRooms841 {
    class BFS {
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            // room size
            int n = rooms.size(), num = 0;
            boolean[] vis = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0);
            while (!queue.isEmpty()) {
                int sz = queue.size();
                for (int i = 0; i < sz; i++) {
                    int x = queue.poll();
                    for (int room : rooms.get(x)) {
                        if (!vis[room]) {
                            vis[room] = true;
                            queue.offer(room);
                        }
                    }
                }
                num++;
            }
            return num == n;
        }
    }

    class DFS {

        Set<Integer> visited = new HashSet<>();

        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            int n = rooms.size();
            dfs(rooms, 0);
            return visited.size() == n;
        }

        private void dfs(List<List<Integer>> rooms, int currentRoom) {
            visited.add(currentRoom);
            for (Integer room : rooms.get(currentRoom)) {
                if (!visited.contains(room)) {
                    dfs(rooms, room);
                }
            }
        }
    }
}
