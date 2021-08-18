package leetcode.algo.dp;

// https://leetcode-cn.com/problems/largest-1-bordered-square/
public class Largest1BorderedSquare1139 {
    class DP {
        public int largest1BorderedSquare(int[][] grid) {
            if (grid.length == 0)
                return 0;
            int[][] dpr = new int[grid.length + 1][grid[0].length + 1];
            int[][] dpc = new int[grid.length + 1][grid[0].length + 1];
            int dist, max = 0;
            for (int r = 1; r <= grid.length; r++) {
                for (int c = 1; c <= grid[0].length; c++) {
                    if (grid[r - 1][c - 1] == 0)
                        continue;
                    dpr[r][c] = dpr[r - 1][c] + 1;
                    dpc[r][c] = dpc[r][c - 1] + 1;
                    dist = Math.min(dpr[r][c], dpc[r][c]);
                    for (int i = dist; i >= 1; i--) {
                        if (dpr[r][c - i + 1] >= i && dpc[r - i + 1][c] >= i) {
                            max = Math.max(max, i * i);
                            break;
                        }
                    }
                }
            }
            return max;
        }
    }

    class BruteForce {
        public int largest1BorderedSquare(int[][] grid) {
            int maxLen = 0;
            int height = grid.length;
            int width = grid[0].length;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (grid[i][j] == 1) {
                        int currLen = maxLen;
                        while (i + currLen < height && j + currLen < width) {
                            boolean flag1 = true;
                            for (int tmp_i = i, tmp_j = j; tmp_i < i + currLen + 1
                                    && tmp_j < j + currLen + 1; tmp_i++, tmp_j++) {
                                if (grid[i][tmp_j] == 0 || grid[tmp_i][j] == 0) {
                                    flag1 = false;
                                    break;
                                }
                            }

                            if (flag1 == false)
                                break;

                            boolean flag2 = true;

                            for (int tmp_i = i + currLen, tmp_j = j + currLen, _i = tmp_i, _j = tmp_j; tmp_i > i
                                    && tmp_j > j; tmp_i--, tmp_j--) {
                                if (grid[tmp_i][_j] == 0 || grid[_i][tmp_j] == 0) {
                                    currLen++;
                                    flag2 = false;
                                    break;
                                }
                            }

                            if (flag2 == false)
                                continue;
                            maxLen = ++currLen;
                        }
                    }
                }
            }
            return maxLen * maxLen;
        }
    }
}
