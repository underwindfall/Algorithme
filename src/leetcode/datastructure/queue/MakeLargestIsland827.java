package leetcode.datastructure.queue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode-cn.com/problems/making-a-large-island/
// 思路 https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
// 1. 计算陆地面积，并分别用相对应的number记录下来
// 2. 计算海洋格子，找到相应的邻居是陆地的海洋格子
// 3. 然后通过相邻的格子的合并就是最大的填海造陆面积
public class MakeLargestIsland827 {

    //time O(M*N*4)
    //espace O(M*N)
    public int largestIsland(int[][] grid) {
        int res = 0;

        int number = 2;// 0是海洋1是陆地，为了避免冲突，从2开始
        if (grid == null || grid.length == 0) {
            return 1;
        }

        Map<Integer, Integer> areaMap = new HashMap<>();// 序号对应面积的一个映射，因为面积要最后结束才能计算出来，所以遍历过程中用index代替，放在映射中
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    int area = getArea(grid, r, c, number);
                    areaMap.put(number, area);
                    number++;
                    res = Math.max(res, area);
                }
            }
        }

        if (res == 0) {
            return 1;// 如果没有陆地，那么就造1块
        }

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) {// 遍历海洋格子
                    Set<Integer> neighbours = getNeighbour(grid, r, c);// 把上下左右四邻放入set里，set的目的是去重
                    if (neighbours.size() < 1)
                        continue;
                    int twoIslands = 1;
                    // 通过序号找到面积
                    for (Integer key : neighbours) {
                        twoIslands += areaMap.get(key);
                        res = Math.max(res, twoIslands);
                    }
                }
            }
        }

        return res;
    }

    Set<Integer> getNeighbour(int[][] grid, int r, int c) {
        Set<Integer> set = new HashSet<>();
        if (inArea(grid, r - 1, c) && grid[r - 1][c] != 0) {
            set.add(grid[r - 1][c]);
        }
        if (inArea(grid, r + 1, c) && grid[r + 1][c] != 0) {
            set.add(grid[r + 1][c]);
        }
        if (inArea(grid, r, c - 1) && grid[r][c - 1] != 0) {
            set.add(grid[r][c - 1]);
        }
        if (inArea(grid, r, c + 1) && grid[r][c + 1] != 0) {
            set.add(grid[r][c + 1]);
        }
        return set;
    }

    int getArea(int[][] grid, int r, int c, int number) {
        if (!inArea(grid, r, c)) {
            return 0;
        }
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = number;
        return 1 + getArea(grid, r + 1, c, number) + getArea(grid, r - 1, c, number) + getArea(grid, r, c + 1, number)
                + getArea(grid, r, c - 1, number);
    }

    private boolean inArea(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }
}
