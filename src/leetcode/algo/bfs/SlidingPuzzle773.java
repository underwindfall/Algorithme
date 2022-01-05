package leetcode.algo.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// https://leetcode-cn.com/problems/sliding-puzzle/
public class SlidingPuzzle773 {
    //time O((mn)! * m * n) 
    //space O((mn)! * m * n) 
    int m = 2, n = 3;

    public int slidingPuzzle(int[][] board) {
        // target bfs
        // double dfs

        String s = "";
        String target = "123450";
        int x = 0, y = 0;
        // idx=x∗3+y
        // x=idx/3,y=idx%3
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                s += board[i][j];
                if (board[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }
        Map<String, Integer> freq = new HashMap<>();

        Queue<Node> q = new LinkedList<>();
        Node root = new Node(s, x, y);
        q.offer(root);

        freq.put(s, 0);

        int[][] dirs = new int[][] { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };

        while (!q.isEmpty()) {
            Node node = q.poll();
            int step = freq.get(node.str);
            if (node.str.equals(target)) {
                return freq.get(node.str);
            }

            int dx = node.x, dy = node.y;

            for (int[] dir : dirs) {
                int nx = dir[0] + dx;
                int ny = dir[1] + dy;
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                String newStr = update(node.str, dx, dy, nx, ny);
                if (freq.containsKey(newStr))
                    continue;
                Node next = new Node(newStr, nx, ny);
                q.offer(next);
                freq.put(newStr, step + 1);
            }
        }
        return -1;
    }

    String update(String curr, int i, int j, int p, int q) {
        char[] cs = curr.toCharArray();
        char tmp = cs[i * n + j];
        cs[i * n + j] = cs[p * n + q];
        cs[p * n + q] = tmp;
        return new String(cs);
    }

    class Node {
        String str;
        int x, y;

        Node(String str, int x, int y) {
            this.str = str;
            this.x = x;
            this.y = y;
        }
    }
}
