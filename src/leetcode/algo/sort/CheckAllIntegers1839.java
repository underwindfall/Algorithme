package leetcode.algo.sort;

import java.util.Arrays;

//https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered
public class CheckAllIntegers1839 {
    // 暴力比较两端直接的存在的数字
    // time O(N^2)
    // espace O(1)
    class BruteForce {
        public boolean isCovered(int[][] ranges, int left, int right) {
            boolean[] flag = new boolean[51];
            for (int[] range : ranges) {
                int L = range[0], R = range[1];
                for (int i = L; i <= R; i++) {
                    flag[i] = true;
                }
            }

            for (int i = left; i <= right; i++) {
                if (flag[i] == false) {
                    return false;
                }
            }
            return true;
        }
    }

    // time O(N * logN)
    // espace O(logN)
    class Sort {
        public boolean isCovered(int[][] ranges, int left, int right) {
            // 上升排序 l <= left <= r
            Arrays.sort(ranges, (a1, a2) -> a1[0] - a2[0]);
            for (int[] range : ranges) {
                int l = range[0];
                int r = range[1];
                if (l <= left && left <= r) {
                    left = r + 1;
                }
            }
            return left > right;
        }
    }

    // 取交集的办法 每次都从range取出一段最小的区域 left
    // time O(N)
    // espace O(1)
    class Intersection {
        public boolean isCovered(int[][] ranges, int left, int right) {
            boolean[] flag = new boolean[51];
            for (int[] range : ranges) {
                int L = Math.max(range[0], left);
                int R = Math.min(range[1], right);
                for (int i = L; i <= R; i++) {
                    flag[i] = true;
                }
            }
            for (int i = left; i <= right; i++) {
                if (flag[i] == false)
                    return false;
            }
            return true;
        }
    }

    // 差分数组思想
    // diff[i]++,代表在i位置上有新的覆盖
    // 若覆盖到j结束了呢？此时j依然是覆盖，但是j+1不在覆盖状态，所以在j+1处 -1；
    // 即diff[j+1]--;
    // time O(N)
    // espace O(1)
    class DiffArray {
        public boolean isCovered(int[][] ranges, int left, int right) {
            int[] diff = new int[52];
            // 对差分数组进行处理
            for (int i = 0; i < ranges.length; i++) {
                diff[ranges[i][0]]++;
                diff[ranges[i][1] + 1]--;
            }
            // 根据差分数组 处理 前缀和，为了理解方便单独定义sum
            int[] sum = new int[52];
            for (int i = 1; i <= 51; i++) {
                sum[i] = sum[i - 1] + diff[i];
            }
            // 从left到right的判断是否满足sum>0
            for (int i = left; i <= right; i++) {
                if (sum[i] <= 0)
                    return false;
            }
            return true;
        }
    }
}
