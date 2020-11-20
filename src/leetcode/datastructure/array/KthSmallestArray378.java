package leetcode.datastructure.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KthSmallestArray378 {
    // time O(N*log(r-l))
    // espace O(1)
    class BinarySearch {
        // 算法的核心在于如何决定能够使用二分法
        // 首先对于一个matrix来说，任何一个mid的数字都会在min-> matrix[0][0] ~ max->matrix[n-1][n-1]之间徘徊
        // mid将matrix分成了左边部分和右边部分
        // 左边部分的所有数字之和sum就是当前小于mid的数字，也就是正序排列的k
        // 当sum<k时候，意味着mid数字小，所以left=mid+1,提高搜索范围
        // sum>k mid数字过大，right = mid
        public int kthSmallest(int[][] matrix, int k) {
            int N = matrix.length;
            int left = matrix[0][0];
            int right = matrix[N - 1][N - 1];
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (checkLeftMatrixWithK(matrix, mid, k, N)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        private boolean checkLeftMatrixWithK(int[][] matrix, int mid, int k, int n) {
            int i = n - 1;
            int j = 0;
            int sum = 0;
            while (i >= 0 && j < n) {
                if (matrix[i][j] <= mid) {
                    sum += i + 1;
                    j++;
                } else {
                    i--;
                }
            }
            return sum >= k;
        }
    }

    // https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/shi-yong-dui-heapde-si-lu-xiang-jie-ling-fu-python/
    // 利用PQ 的特性来寻找第K个最小的值 队列->
    // step1. 先以第一列为最小的候选最小值
    // step2. 然后弹出相应的第一个数字 因为此时他是最小的
    // step3. 插入她之后的同行下一列数字
    // 循环
    // time O(k*logN)
    // espace O(N)
    class MergedSortedSolution {
        public int kthSmallest(int[][] matrix, int k) {
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            int N = matrix.length;
            for (int i = 0; i < N - 1; i++) {
                pq.offer(new int[] { matrix[i][0], i, 0 });
            }
            for (int i = 0; i < k - 1; i++) {
                int[] smallest = pq.poll();
                int rang = smallest[1];
                int col = smallest[2] + 1;
                if (smallest[2] != N - 1) {
                    pq.offer(new int[] { matrix[rang][col], rang, col });
                }
            }
            return pq.poll()[0];
        }
    }

    // 暴力解法 毫无美学
    // time O(N^2*logN)
    // espace O(N^2)
    class WorstSolution {
        public int kthSmallest(int[][] matrix, int k) {
            int N = matrix.length;
            List<Integer> data = new ArrayList<>(N * N);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    data.add(matrix[i][j]);
                }
            }
            Collections.sort(data);
            return data.get(k - 1);
        }
    }
}
