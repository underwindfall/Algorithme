package leetcode.algo.divideandconquer;

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
