package leetcode.datastructure.queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode-cn.com/problems/walls-and-gates/
public class WallsAndGates286 {
     // 每个格子 都有4中方向移动
    // BFS 明显的算法是先遍历其中node的邻居节点 然后把邻居节点加入进去 处理的时候因为是队列的处理方式 所以没差
    // time O(M*N*N)
    // espace O(mn)
     class BFS {
         private static final int EMPTY = Integer.MAX_VALUE;
         private static final int GATE = 0;
         private  final List<int[]> DIRECTIONS = Arrays.asList(
                new int[] { 1,  0},
                new int[] {-1,  0},
                new int[] { 0,  1},
                new int[] { 0, -1}
         );

         public void wallsAndGates(int[][] rooms) {
        
            Queue<int[]> q = new LinkedList<>();

             for (int i = 0; i < rooms.length; i++) {
                 for (int j = 0; j < rooms[0].length; j++) {
                     if (rooms[i][j] == GATE) { // 从发现是门的地方开始遍历
                         q.add(new int[] { i, j});
                     }
                 }
             }

             while (!q.isEmpty()) {
                 int[] point = q.poll();
                 int row = point[0], col = point[1];

                 for (int[] direction : DIRECTIONS) {
                     int r = row + direction[0];
                     int c = col + direction[1];
                     if (r < 0 || r >= rooms.length || c < 0 || c >= rooms[0].length || rooms[r][c] != EMPTY) {
                         continue;
                     }

                     rooms[r][c] = rooms[row][col] + 1;
                     q.add(new int[] { r, c });
                 }
             }
         }

    
    }

    /**
     * 先调一个node 遍历 然后直接一直到找到结果为止
     * time O(M*M*N*N)
     * espace O(mn)
     */
    class DFS {
        public void wallsAndGates(int[][] rooms) {
            for (int i = 0; i < rooms.length; i++) {
                for (int j = 0; j < rooms[0].length; j++) {
                    if (rooms[i][j] == 0) { //从发现是门的地方开始遍历
                        dfs(rooms, i, j, 0);
                    }
                }
            }
        }

        void dfs(int[][] rooms, int row, int col, int min) {
            if (row < 0 || row >= rooms.length || col < 0 || col >= rooms[0].length || rooms[row][col] < min) {
                return;
            }

            rooms[row][col] = min;
            //上
            dfs(rooms, row - 1, col, min + 1);
            //下
            dfs(rooms, row + 1, col, min + 1);
            //左
            dfs(rooms, row, col - 1, min + 1);
            //右
            dfs(rooms, row, col + 1, min + 1);
        }
    }


    // 每个格子 都有4中方向移动
    // BFS 明显的算法是先遍历其中node的邻居节点 然后把邻居节点加入进去 处理的时候因为是队列的处理方式 所以没差
    // time O(M*M*N*N)
    // espace O(mn)
    class BFSBAD {
        private static final int EMPTY = Integer.MAX_VALUE;
        private static final int GATE = 0;
        private static final int WALL = -1;
        private final List<int[]> DIRECTIONS = Arrays.asList(
                new int[] { 1, 0 }, // 上
                new int[] { -1, 0 }, // 下
                new int[] { 0, 1 }, // 右
                new int[] { 0, -1 }// 左
        );

        public void wallsAndGates(int[][] rooms) {
            if (rooms.length == 0)
                return;
            for (int row = 0; row < rooms.length; row++) {
                for (int col = 0; col < rooms[0].length; col++) {
                    if (rooms[row][col] == EMPTY) {
                        rooms[row][col] = distanceToNearestGate(rooms, row, col);
                    }
                }
            }
        }

        int distanceToNearestGate(int[][] rooms, int startRow, int startCol) {
            int m = rooms.length;
            int n = rooms[0].length;
            int[][] distance = new int[m][n];

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[] { startRow, startCol });

            while (!q.isEmpty()) {
                int[] point = q.poll();

                int row = point[0];
                int col = point[1];

                for (int[] directions : DIRECTIONS) {
                    int r = row + directions[0]; //新的r
                    int c = col + directions[1]; //新的c

                    if (r < 0 || c < 0 || r >= m || c>= n || rooms[r][c] == WALL || distance[r][c] != 0) {
                        continue;
                    }

                    distance[r][c] = distance[row][col] + 1;

                    if (rooms[r][c] == GATE) {
                        return distance[r][c];
                    }

                    q.add(new int[] { r, c });
                }
            }
            return Integer.MAX_VALUE;
        }
    }

}
