package leetcode.datastructure.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode-cn.com/problems/k-closest-points-to-origin/
public class KClosest973 {
    // time O(n * logn)
    // space O(logn)
    class Sort {
        public int[][] kClosest(int[][] points, int k) {
            Arrays.sort(points, new Comparator<int[]>() {

                @Override
                public int compare(int[] point1, int[] point2) {
                    return (point1[0] * point1[0] + point1[1] * point1[1])
                            - (point2[0] * point2[0] + point2[1] * point2[1]);
                }

            });

            return Arrays.copyOfRange(points, 0, k);
        }
    }

    // time O(n)
    // space O(n)
    class QuickSort {
        public int[][] kClosest(int[][] points, int K) {
            // 使用快速选择（快排变形） 获取 points 数组中距离最小的 K 个点（第 4 个参数是下标，因此是 K - 1）
            return quickSelect(points, 0, points.length - 1, K - 1);
        }

        private int[][] quickSelect(int[][] points, int lo, int hi, int idx) {
            if (lo > hi) {
                return new int[0][0];
            }
            // 快排切分后，j 左边的点距离都小于等于 points[j], j 右边的点的距离都大于等于 points[j]。
            int j = partition(points, lo, hi);
            // 如果 j 正好等于目标idx，说明当前points数组中的[0, idx]就是距离最小的 K 个元素
            if (j == idx) {
                return Arrays.copyOf(points, idx + 1);
            }
            // 否则根据 j 与 idx 的大小关系判断找左段还是右段
            return j < idx ? quickSelect(points, j + 1, hi, idx) : quickSelect(points, lo, j - 1, idx);
        }

        private int partition(int[][] points, int lo, int hi) {
            int pivot = lo;
            int[] pivotV = points[pivot];
            int pivotDist = pivotV[0] * pivotV[0] + pivotV[1] * pivotV[1];
            int i = lo, j = hi + 1;
            while (true) {
                while (++i <= hi && points[i][0] * points[i][0] + points[i][1] * points[i][1] < pivotDist);
                while (--j >= lo && points[j][0] * points[j][0] + points[j][1] * points[j][1] > pivotDist);
                if (i >= j) {
                    break;
                }
                int[] tmp = points[i];
                points[i] = points[j];
                points[j] = tmp;
            }
            points[lo] = points[j];
            points[j] = pivotV;
            return j;
        }
    }

    // time o(n logk)
    // space O(k)
    class Heap {
        public int[][] kClosest(int[][] points, int k) {
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                public int compare(int[] array1, int[] array2) {
                    return array2[0] - array1[0];
                }
            });
            for (int i = 0; i < k; ++i) {
                pq.offer(new int[] { points[i][0] * points[i][0] + points[i][1] * points[i][1], i });
            }
            int n = points.length;
            for (int i = k; i < n; ++i) {
                int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
                if (dist < pq.peek()[0]) {
                    pq.poll();
                    pq.offer(new int[] { dist, i });
                }
            }
            int[][] ans = new int[k][2];
            for (int i = 0; i < k; ++i) {
                ans[i] = points[pq.poll()[1]];
            }
            return ans;
        }
    }
}
